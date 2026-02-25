db.localizacoes.createIndex({ id_localizacao: 1 }, { unique: true });
db.localizacoes.createIndex({ distrito: 1, concelho: 1, freguesia: 1 }, { unique: true });

db.raw_localizacao.aggregate([
  {
    $project: {
      _id: 0,
      id_localizacao: { $convert: { input: "$id_localizacao", to: "int", onError: null, onNull: null } },
      distrito: { $toUpper: { $trim: { input: { $ifNull: ["$distrito", ""] } } } },
      concelho: { $toUpper: { $trim: { input: { $ifNull: ["$concelho", ""] } } } },
      freguesia: { $toUpper: { $trim: { input: { $ifNull: ["$freguesia", ""] } } } }
    }
  },
  { $match: { id_localizacao: { $ne: null } } },
  {
    $merge: {
      into: "localizacoes",
      on: "id_localizacao",
      whenMatched: "replace",
      whenNotMatched: "insert"
    }
  }
], { allowDiskUse: true });
