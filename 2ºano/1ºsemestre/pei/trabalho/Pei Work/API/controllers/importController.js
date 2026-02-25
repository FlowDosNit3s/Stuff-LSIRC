const mongoose = require("mongoose");

const asArray = (v) => (Array.isArray(v) ? v : v ? [v] : []);
const num = (v) => Number(v);

exports.importIncendios = async (req, res) => {
  try {
    // Depois do middleware: req.body já é JSON parseado pelo fast-xml-parser
    const root = req.body.WildfireStats || req.body;

    const ocorrencias = asArray(root?.Ocorrencia);
    if (!ocorrencias.length) {
      return res.status(400).json({
        success: false,
        error: "XML válido no XSD, mas sem Ocorrencia(s) para importar.",
      });
    }

    const docs = ocorrencias.map((o) => ({
      codigo_incendio: num(o.codigo_incendio),
      data_inicio: o.data_inicio, // YYYY-MM-DD (conforme XSD)
      duracao_horas: num(o.duracao_horas),
      estado: String(o.estado),
      id_localizacao: num(o.id_localizacao),
      id_causa: num(o.id_causa),
      areas: {
        total: num(o.areas?.total),
        povoamento: num(o.areas?.povoamento),
        mato: num(o.areas?.mato),
        agricola: num(o.areas?.agricola),
      },
    }));

    await mongoose.connection.db.collection("raw_incendios").insertMany(docs, { ordered: false });

    return res.json({ success: true, inserted: docs.length, collection: "raw_incendios" });
  } catch (e) {
    return res.status(500).json({ success: false, error: e.message });
  }
};

exports.importMeteorologia = async (req, res) => {
  try {
    const root = req.body.MeteorologiaStats || req.body;

    const medicoes = asArray(root?.Medicao);
    if (!medicoes.length) {
      return res.status(400).json({
        success: false,
        error: "XML válido no XSD, mas sem Medicao(ões) para importar.",
      });
    }

    const docs = medicoes.map((m) => ({
      data: String(m.data), // YYYY-MM-DD
      id_localizacao: num(m.id_localizacao),
      medicoes: {
        temp_max: num(m.medicoes?.temp_max),
        temp_min: num(m.medicoes?.temp_min),
        temp_media: num(m.medicoes?.temp_media),
        vento_max: num(m.medicoes?.vento_max),
        vento_rafada: num(m.medicoes?.vento_rafada),
        vento_direcao: num(m.medicoes?.vento_direcao),
        precipitacao: num(m.medicoes?.precipitacao),
        radiacao: num(m.medicoes?.radiacao),
        insolacao: num(m.medicoes?.insolacao),
      },
    }));

    await mongoose.connection.db.collection("raw_metereologia").insertMany(docs, { ordered: false });

    return res.json({ success: true, inserted: docs.length, collection: "raw_metereologia" });
  } catch (e) {
    return res.status(500).json({ success: false, error: e.message });
  }
};

exports.importRecursos = async (req, res) => {
  try {
    const root = req.body.RecursosHumanos || req.body;

    const alocacoes = asArray(root?.Alocacao);
    if (!alocacoes.length) {
      return res.status(400).json({
        success: false,
        error: "XML válido no XSD, mas sem Alocacao(ões) para importar.",
      });
    }

    const docs = alocacoes.map((a) => ({
      concelho: String(a.Concelho),
      ano: num(a.Ano),
      distrito: String(a.Distrito),
      numero_bombeiros: num(a.QuantidadeBombeiros),
    }));

    // Nota: tu tens coleção final "recursos". Se preferires "raw_bombeiros", muda aqui.
    await mongoose.connection.db.collection("recursos").insertMany(docs, { ordered: false });

    return res.json({ success: true, inserted: docs.length, collection: "recursos" });
  } catch (e) {
    return res.status(500).json({ success: false, error: e.message });
  }
};
