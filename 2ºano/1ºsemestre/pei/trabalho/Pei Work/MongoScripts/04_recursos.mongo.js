db.recursos.createIndex({ ano: 1, concelho: 1 }, { unique: true });

db.raw_bombeiros.aggregate([
  {
    $project: {
      _id: 0,
      ano: { $convert: { input: { $ifNull: ["$ano", "$year"] }, to: "int", onError: null, onNull: null } },
      concelho: { $toUpper: { $trim: { input: { $ifNull: ["$concelho", "$municipio", ""] } } } },
      numero_bombeiros: { $convert: { input: { $ifNull: ["$numero_bombeiros", "$bombeiros"] }, to: "int", onError: null, onNull: null } }
    }
  },
  { $match: { ano: { $ne: null }, concelho: { $ne: "" }, numero_bombeiros: { $ne: null } } },
  {
    $group: {
      _id: { ano: "$ano", concelho: "$concelho" },
      numero_bombeiros: { $max: "$numero_bombeiros" }
    }
  },
  {
    $project: {
      _id: 0,
      ano: "$_id.ano",
      concelho: "$_id.concelho",
      numero_bombeiros: 1
    }
  },
  {
    $merge: {
      into: "recursos",
      on: ["ano", "concelho"],
      whenMatched: "replace",
      whenNotMatched: "insert"
    }
  }
], { allowDiskUse: true });
