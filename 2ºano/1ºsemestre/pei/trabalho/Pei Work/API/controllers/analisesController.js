const mongoose = require('mongoose');

const col = (name) => mongoose.connection.db.collection(name);

const toInt = (v) => {
  const n = Number.parseInt(v, 10);
  return Number.isFinite(n) ? n : null;
};

const toFloat = (v) => {
  const n = Number.parseFloat(v);
  return Number.isFinite(n) ? n : null;
};

const badReq = (res, msg) => res.status(400).json({ success: false, error: msg });

/**
 * 1) Média de Área Ardida
 * Por distrito e por tipo (povoamento/mato/agricola), num determinado mês OU estação do ano.
 * GET /api/analises/media-area?month=8
 * GET /api/analises/media-area?season=Verao
 */
exports.mediaArea = async (req, res, next) => {
  try {
    const month = toInt(req.query.month);
    const season = req.query.season;

    if (month === null && !season) {
      return badReq(res, 'Indica ?month=1..12 OU ?season=Verao/Inverno');
    }

    const matchStage = month !== null ? { month } : { season };

    const pipeline = [
      { $match: matchStage },
      {
        $lookup: {
          from: 'localizacoes',
          localField: 'id_localizacao',
          foreignField: 'id_localizacao',
          as: 'loc',
        },
      },
      { $unwind: '$loc' },
      {
        $group: {
          _id: '$loc.distrito',
          media_povoamento: { $avg: '$areas.povoamento' },
          media_mato: { $avg: '$areas.mato' },
          media_agricola: { $avg: '$areas.agricola' },
          media_total: { $avg: '$areas.total' },
          ocorrencias: { $sum: 1 },
        },
      },
      { $sort: { media_total: -1 } },
      {
        $project: {
          _id: 0,
          distrito: '$_id',
          media_povoamento: 1,
          media_mato: 1,
          media_agricola: 1,
          media_total: 1,
          ocorrencias: 1,
        },
      },
    ];

    const results = await col('ocorrencias').aggregate(pipeline, { allowDiskUse: true }).toArray();
    res.json({ success: true, filter: matchStage, results });
  } catch (e) {
    next(e);
  }
};

/**
 * 2) Correlação Fogo/Vento
 * Listar incêndios com área > X e a velocidade do vento registada no dia (na estação/local associada).
 * GET /api/analises/fogo-vento?minArea=50
 */
exports.fogoVento = async (req, res, next) => {
  try {
    const minArea = toFloat(req.query.minArea);
    if (minArea === null) return badReq(res, 'Indica ?minArea=numero (hectares)');

    const pipeline = [
      // Garante que existe uma data ISO "YYYY-MM-DD" para fazer join com meteorologia
      {
        $addFields: {
          dataStr: {
            $ifNull: [
              '$data',
              { $dateToString: { date: '$data_inicio', format: '%Y-%m-%d' } },
            ],
          },
        },
      },
      { $match: { 'areas.total': { $gt: minArea } } },
      {
        $lookup: {
          from: 'metereologia',
          let: { d: '$dataStr', lid: '$id_localizacao' },
          pipeline: [
            {
              $match: {
                $expr: {
                  $and: [
                    { $eq: ['$data', '$$d'] },
                    { $eq: ['$id_localizacao', '$$lid'] },
                  ],
                },
              },
            },
            { $limit: 1 },
          ],
          as: 'met',
        },
      },
      { $addFields: { met: { $first: '$met' } } },
      { $match: { 'met.medicoes.vento_max': { $ne: null } } },
      {
        $project: {
          _id: 0,
          codigo_incendio: 1,
          data: '$dataStr',
          id_localizacao: 1,
          area_total: '$areas.total',
          vento_max: '$met.medicoes.vento_max',
          vento_rafada: '$met.medicoes.vento_rafada',
          // aproximação simples de "vento médio" caso não exista um campo específico
          vento_media_aprox: {
            $cond: [
              {
                $and: [
                  { $ne: ['$met.medicoes.vento_max', null] },
                  { $ne: ['$met.medicoes.vento_rafada', null] },
                ],
              },
              {
                $divide: [
                  { $add: ['$met.medicoes.vento_max', '$met.medicoes.vento_rafada'] },
                  2,
                ],
              },
              null,
            ],
          },
        },
      },
      { $sort: { area_total: -1 } },
    ];

    const results = await col('ocorrencias').aggregate(pipeline, { allowDiskUse: true }).toArray();
    res.json({ success: true, filter: { minArea }, results });
  } catch (e) {
    next(e);
  }
};

/**
 * 3) Eficiência de Combate
 * Duração média dos incêndios por região comparando Verão vs Inverno.
 * GET /api/analises/eficiencia-combate?region=distrito
 * GET /api/analises/eficiencia-combate?region=concelho
 */
exports.eficienciaCombate = async (req, res, next) => {
  try {
    const region = (req.query.region || 'distrito').toLowerCase();
    const groupField = region === 'concelho' ? '$loc.concelho' : '$loc.distrito';

    const pipeline = [
      { $match: { season: { $in: ['Verao', 'Inverno'] }, duracao_horas: { $ne: null } } },
      {
        $lookup: {
          from: 'localizacoes',
          localField: 'id_localizacao',
          foreignField: 'id_localizacao',
          as: 'loc',
        },
      },
      { $unwind: '$loc' },
      {
        $group: {
          _id: { regiao: groupField, season: '$season' },
          duracao_media_horas: { $avg: '$duracao_horas' },
          ocorrencias: { $sum: 1 },
        },
      },
      { $sort: { '_id.regiao': 1, '_id.season': 1 } },
      {
        $project: {
          _id: 0,
          regiao: '$_id.regiao',
          season: '$_id.season',
          duracao_media_horas: 1,
          ocorrencias: 1,
        },
      },
    ];

    const results = await col('ocorrencias').aggregate(pipeline, { allowDiskUse: true }).toArray();
    res.json({ success: true, filter: { region }, results });
  } catch (e) {
    next(e);
  }
};

/**
 * 4) Top N Regiões Críticas
 * Regiões com mais ocorrências em dias com temp_max > valor.
 * GET /api/analises/top-regioes?temp=30&n=10&region=distrito
 */
exports.topRegioes = async (req, res, next) => {
  try {
    const temp = toFloat(req.query.temp);
    const n = toInt(req.query.n) ?? 10;
    const region = (req.query.region || 'distrito').toLowerCase();

    if (temp === null) return badReq(res, 'Indica ?temp=numero (limiar)');

    const groupField = region === 'concelho' ? '$loc.concelho' : '$loc.distrito';

    const pipeline = [
      {
        $addFields: {
          dataStr: {
            $ifNull: [
              '$data',
              { $dateToString: { date: '$data_inicio', format: '%Y-%m-%d' } },
            ],
          },
        },
      },
      {
        $lookup: {
          from: 'metereologia',
          let: { d: '$dataStr', lid: '$id_localizacao' },
          pipeline: [
            {
              $match: {
                $expr: {
                  $and: [
                    { $eq: ['$data', '$$d'] },
                    { $eq: ['$id_localizacao', '$$lid'] },
                  ],
                },
              },
            },
            { $limit: 1 },
          ],
          as: 'met',
        },
      },
      { $addFields: { met: { $first: '$met' } } },
      { $match: { 'met.medicoes.temp_max': { $gt: temp } } },
      {
        $lookup: {
          from: 'localizacoes',
          localField: 'id_localizacao',
          foreignField: 'id_localizacao',
          as: 'loc',
        },
      },
      { $unwind: '$loc' },
      { $group: { _id: groupField, ocorrencias: { $sum: 1 } } },
      { $sort: { ocorrencias: -1 } },
      { $limit: n },
      { $project: { _id: 0, regiao: '$_id', ocorrencias: 1 } },
    ];

    const results = await col('ocorrencias').aggregate(pipeline, { allowDiskUse: true }).toArray();
    res.json({ success: true, filter: { temp, n, region }, results });
  } catch (e) {
    next(e);
  }
};

/**
 * 5) Análise de Recursos
 * Relacionar o número de incêndios com o número de bombeiros disponíveis no município (concelho).
 * GET /api/analises/recursos
 */
exports.analiseRecursos = async (req, res, next) => {
  try {
    const pipeline = [
      {
        $lookup: {
          from: 'localizacoes',
          let: { c: '$concelho' },
          pipeline: [
            { $match: { $expr: { $eq: ['$concelho', '$$c'] } } },
            { $group: { _id: '$concelho', ids: { $addToSet: '$id_localizacao' } } },
          ],
          as: 'locs',
        },
      },
      {
        $addFields: {
          ids: {
            $ifNull: [
              { $getField: { field: 'ids', input: { $first: '$locs' } } },
              [],
            ],
          },
        },
      },
      {
        $lookup: {
          from: 'ocorrencias',
          let: { ids: '$ids' },
          pipeline: [
            { $match: { $expr: { $in: ['$id_localizacao', '$$ids'] } } },
            { $count: 'n_incendios' },
          ],
          as: 'fires',
        },
      },
      {
        $addFields: {
          n_incendios: {
            $ifNull: [
              { $getField: { field: 'n_incendios', input: { $first: '$fires' } } },
              0,
            ],
          },
        },
      },
      {
        $project: {
          _id: 0,
          ano: 1,
          concelho: 1,
          numero_bombeiros: 1,
          n_incendios: 1,
          incendios_por_100_bombeiros: {
            $cond: [
              { $gt: ['$numero_bombeiros', 0] },
              { $multiply: [{ $divide: ['$n_incendios', '$numero_bombeiros'] }, 100] },
              null,
            ],
          },
        },
      },
      { $sort: { n_incendios: -1 } },
    ];

    const results = await col('recursos').aggregate(pipeline, { allowDiskUse: true }).toArray();
    res.json({ success: true, results });
  } catch (e) {
    next(e);
  }
};

/**
 * 6) Temperaturas por região
 * Calcular temp média/máx/mín por distrito ou concelho, num intervalo.
 * GET /api/analises/temperaturas?start=2024-02-02&end=2024-05-01&region=distrito
 */
exports.temperaturasPorRegiao = async (req, res, next) => {
  try {
    const start = req.query.start; // "YYYY-MM-DD"
    const end = req.query.end;     // "YYYY-MM-DD"
    const region = (req.query.region || 'distrito').toLowerCase();

    if (!start || !end) return badReq(res, 'Indica ?start=YYYY-MM-DD&end=YYYY-MM-DD');

    const groupField = region === 'concelho' ? '$loc.concelho' : '$loc.distrito';

    const pipeline = [
      { $match: { data: { $gte: start, $lte: end } } },
      {
        $lookup: {
          from: 'localizacoes',
          localField: 'id_localizacao',
          foreignField: 'id_localizacao',
          as: 'loc',
        },
      },
      { $unwind: '$loc' },
      {
        $group: {
          _id: groupField,
          temp_media: { $avg: '$medicoes.temp_media' },
          temp_max: { $avg: '$medicoes.temp_max' },
          temp_min: { $avg: '$medicoes.temp_min' },
          dias: { $sum: 1 },
        },
      },
      { $sort: { temp_media: -1 } },
      {
        $project: {
          _id: 0,
          regiao: '$_id',
          temp_media: 1,
          temp_max: 1,
          temp_min: 1,
          dias: 1,
        },
      },
    ];

    const results = await col('metereologia').aggregate(pipeline, { allowDiskUse: true }).toArray();
    res.json({ success: true, filter: { start, end, region }, results });
  } catch (e) {
    next(e);
  }
};
