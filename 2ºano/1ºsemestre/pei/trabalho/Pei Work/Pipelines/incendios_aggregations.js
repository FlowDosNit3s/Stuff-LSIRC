//1.1) Média de Área Ardida por distrito (mês específico)
db.getCollection('ocorrencias').aggregate(
  [
    { $match: { month: 1 } },
    {
      $lookup: {
        from: 'localizacoes',
        localField: 'id_localizacao',
        foreignField: 'id_localizacao',
        as: 'loc'
      }
    },
    { $unwind: '$loc' },
    {
      $group: {
        _id: '$loc.distrito',
        media_povoamento: {
          $avg: '$areas.povoamento'
        },
        media_mato: { $avg: '$areas.mato' },
        media_agricola: {
          $avg: '$areas.agricola'
        },
        media_total: { $avg: '$areas.total' },
        ocorrencias: { $sum: 1 }
      }
    },
    { $sort: { media_total: -1 } }
  ],
  { maxTimeMS: 60000, allowDiskUse: true }
);

//1.2) Média de Área Ardida por distrito (estação do ano)
db.getCollection('ocorrencias').aggregate(
  [
    { $match: { season: 'Verao' } },
    {
      $lookup: {
        from: 'localizacoes',
        localField: 'id_localizacao',
        foreignField: 'id_localizacao',
        as: 'loc'
      }
    },
    { $unwind: '$loc' },
    {
      $group: {
        _id: '$loc.distrito',
        media_povoamento: {
          $avg: '$areas.povoamento'
        },
        media_mato: { $avg: '$areas.mato' },
        media_agricola: {
          $avg: '$areas.agricola'
        },
        media_total: { $avg: '$areas.total' },
        ocorrencias: { $sum: 1 }
      }
    },
    { $sort: { media_total: -1 } }
  ],
  { maxTimeMS: 60000, allowDiskUse: true }
);

//2)Correlação Fogo / Vento
db.getCollection('ocorrencias').aggregate(
  [
    { $match: { 'areas.total': { $gt: 1 } } },
    {
      $lookup: {
        from: 'metereologia',
        let: {
          d: '$data',
          lid: '$id_localizacao'
        },
        pipeline: [
          {
            $match: {
              $expr: {
                $and: [
                  { $eq: ['$data', '$$d'] },
                  {
                    $eq: [
                      '$id_localizacao',
                      '$$lid'
                    ]
                  }
                ]
              }
            }
          },
          { $limit: 1 }
        ],
        as: 'met'
      }
    },
    { $addFields: { met: { $first: '$met' } } },
    {
      $match: {
        'met.medicoes.vento_max': { $ne: null }
      }
    },
    {
      $project: {
        _id: 0,
        codigo_incendio: 1,
        data: 1,
        id_localizacao: 1,
        area_total: '$areas.total',
        vento_max: '$met.medicoes.vento_max',
        vento_rafada:
          '$met.medicoes.vento_rafada',
        vento_media_aprox: {
          $cond: [
            {
              $and: [
                {
                  $ne: [
                    '$met.medicoes.vento_max',
                    null
                  ]
                },
                {
                  $ne: [
                    '$met.medicoes.vento_rafada',
                    null
                  ]
                }
              ]
            },
            {
              $divide: [
                {
                  $add: [
                    '$met.medicoes.vento_max',
                    '$met.medicoes.vento_rafada'
                  ]
                },
                2
              ]
            },
            null
          ]
        }
      }
    },
    { $sort: { area_total: -1 } }
  ],
  { maxTimeMS: 60000, allowDiskUse: true }
);

//3)Eficiência de Combate (Verão vs Inverno)
db.getCollection('ocorrencias').aggregate(
  [
    {
      $match: {
        season: { $in: ['Verao', 'Inverno'] },
        duracao_horas: { $ne: null }
      }
    },
    {
      $lookup: {
        from: 'localizacoes',
        localField: 'id_localizacao',
        foreignField: 'id_localizacao',
        as: 'loc'
      }
    },
    { $unwind: '$loc' },
    {
      $group: {
        _id: {
          distrito: '$loc.distrito',
          season: '$season'
        },
        duracao_media_horas: {
          $avg: '$duracao_horas'
        },
        ocorrencias: { $sum: 1 }
      }
    },
    {
      $sort: {
        '_id.distrito': 1,
        '_id.season': 1
      }
    }
  ],
  { maxTimeMS: 60000, allowDiskUse: true }
);

//4) Top 10 Distritos com mais Ocorrências (Depende da Temperatura Máxima)
db.getCollection('ocorrencias').aggregate(
  [
    {
      $lookup: {
        from: 'metereologia',
        let: {
          d: '$data',
          lid: '$id_localizacao'
        },
        pipeline: [
          {
            $match: {
              $expr: {
                $and: [
                  { $eq: ['$data', '$$d'] },
                  {
                    $eq: [
                      '$id_localizacao',
                      '$$lid'
                    ]
                  }
                ]
              }
            }
          },
          { $limit: 1 }
        ],
        as: 'met'
      }
    },
    { $addFields: { met: { $first: '$met' } } },
    {
      $match: {
        'met.medicoes.temp_max': { $gt: 30 }
      }
    },
    {
      $lookup: {
        from: 'localizacoes',
        localField: 'id_localizacao',
        foreignField: 'id_localizacao',
        as: 'loc'
      }
    },
    { $unwind: '$loc' },
    {
      $group: {
        _id: '$loc.distrito',
        ocorrencias: { $sum: 1 }
      }
    },
    { $sort: { ocorrencias: -1 } },
    { $limit: 10 }
  ],
  { maxTimeMS: 60000, allowDiskUse: true }
);