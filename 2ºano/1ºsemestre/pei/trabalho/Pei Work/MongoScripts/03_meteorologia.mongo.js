db.meteorologia.createIndex({ id_localizacao: 1, data: 1 }, { unique: true });
db.meteorologia.createIndex({ data: 1 });

db.raw_meteorologia.aggregate([
  {
    $project: {
      _id: 0,
      id_localizacao: { $convert: { input: "$id_localizacao", to: "int", onError: null, onNull: null } },

      data: {
        $switch: {
          branches: [
            { case: { $eq: [{ $type: "$data" }, "string"] }, then: { $substrBytes: ["$data", 0, 10] } },
            {
              case: { $eq: [{ $type: "$obsDate" }, "date"] },
              then: { $dateToString: { date: "$obsDate", format: "%Y-%m-%d" } }
            },
            {
              case: { $ne: ["$dateKey", null] },
              then: {
                $concat: [
                  { $substrBytes: [{ $toString: "$dateKey" }, 0, 4] }, "-",
                  { $substrBytes: [{ $toString: "$dateKey" }, 4, 2] }, "-",
                  { $substrBytes: [{ $toString: "$dateKey" }, 6, 2] }
                ]
              }
            }
          ],
          default: null
        }
      },

      temp_max: { $convert: { input: { $ifNull: ["$temp_max", "$tmax"] }, to: "double", onError: null, onNull: null } },
      temp_min: { $convert: { input: { $ifNull: ["$temp_min", "$tmin"] }, to: "double", onError: null, onNull: null } },
      temp_media: { $convert: { input: { $ifNull: ["$temp_mean", "$temp_media", "$tmean"] }, to: "double", onError: null, onNull: null } },

      vento_max: { $convert: { input: { $ifNull: ["$wind_max", "$vento_max"] }, to: "double", onError: null, onNull: null } },
      vento_rafada: { $convert: { input: { $ifNull: ["$gust_max", "$vento_rafada"] }, to: "double", onError: null, onNull: null } },
      vento_direcao: { $convert: { input: { $ifNull: ["$wind_dir", "$vento_dir", "$vento_direcao"] }, to: "int", onError: null, onNull: null } },

      precipitacao: { $convert: { input: { $ifNull: ["$precip_sum", "$precipitacao"] }, to: "double", onError: null, onNull: null } },
      radiacao: { $convert: { input: { $ifNull: ["$radiation", "$radiacao"] }, to: "double", onError: null, onNull: null } },
      insolacao: { $convert: { input: { $ifNull: ["$sunshine", "$insolacao"] }, to: "double", onError: null, onNull: null } }
    }
  },
  { $match: { data: { $ne: null }, id_localizacao: { $ne: null } } },
  {
    $project: {
      data: 1,
      id_localizacao: 1,
      medicoes: {
        temp_max: "$temp_max",
        temp_min: "$temp_min",
        temp_media: "$temp_media",
        vento_max: "$vento_max",
        vento_rafada: "$vento_rafada",
        vento_direcao: "$vento_direcao",
        precipitacao: "$precipitacao",
        radiacao: "$radiacao",
        insolacao: "$insolacao"
      }
    }
  },
  {
    $merge: {
      into: "meteorologia",
      on: ["id_localizacao", "data"],
      whenMatched: "replace",
      whenNotMatched: "insert"
    }
  }
], { allowDiskUse: true });
