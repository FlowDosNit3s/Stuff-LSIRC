db.ocorrencias.createIndex({ codigo_incendio: 1 }, { unique: true });
db.ocorrencias.createIndex({ id_localizacao: 1 });
db.ocorrencias.createIndex({ data_inicio: 1 });
db.ocorrencias.createIndex({ month: 1, season: 1 });

db.raw_incendios.aggregate([
  {
    $project: {
      _id: 0,
      codigo_incendio: { $convert: { input: { $ifNull: ["$id_incendio", "$codigo_incendio"] }, to: "int", onError: null, onNull: null } },
      id_localizacao: { $convert: { input: "$id_localizacao", to: "int", onError: null, onNull: null } },
      id_causa: { $convert: { input: "$id_causa", to: "int", onError: null, onNull: null } },

      dataRaw: "$data",
      duracao_horas: { $convert: { input: { $ifNull: ["$duracao_horas", "$duracao_h"] }, to: "double", onError: null, onNull: null } },
      estado: { $ifNull: ["$estado", "Concluído"] },

      areas_total: { $convert: { input: { $ifNull: ["$areaTotal_ha", "$areaTotalHa", "$area_total", "$area_total_ha"] }, to: "double", onError: 0, onNull: 0 } },
      areas_povo:  { $convert: { input: { $ifNull: ["$areaPov_ha", "$areaPovHa", "$area_povoamento", "$area_povoamento_ha"] }, to: "double", onError: 0, onNull: 0 } },
      areas_mato:  { $convert: { input: { $ifNull: ["$areaMato_ha", "$areaMatoHa", "$area_mato", "$area_mato_ha"] }, to: "double", onError: 0, onNull: 0 } },
      areas_agri:  { $convert: { input: { $ifNull: ["$areaAgric_ha", "$areaAgricHa", "$area_agricola", "$area_agricola_ha"] }, to: "double", onError: 0, onNull: 0 } }
    }
  },
  {
    $addFields: {
      data_inicio: {
        $switch: {
          branches: [
            { case: { $eq: [{ $type: "$dataRaw" }, "date"] }, then: "$dataRaw" },
            {
              case: { $eq: [{ $type: "$dataRaw" }, "string"] },
              then: {
                $dateFromString: {
                  dateString: { $substrBytes: ["$dataRaw", 0, 10] },
                  format: "%Y-%m-%d",
                  onError: null,
                  onNull: null
                }
              }
            }
          ],
          default: null
        }
      }
    }
  },
  { $match: { codigo_incendio: { $ne: null }, data_inicio: { $ne: null }, id_localizacao: { $ne: null } } },
  {
    $addFields: {
      month: { $month: "$data_inicio" },
      season: {
        $cond: [
          { $in: [{ $month: "$data_inicio" }, [6, 7, 8]] },
          "Verao",
          "Inverno"
        ]
      }
    }
  },
  {
    $project: {
      codigo_incendio: 1,
      data_inicio: 1,
      duracao_horas: 1,
      estado: 1,
      id_localizacao: 1,
      id_causa: 1,
      month: 1,
      season: 1,
      areas: {
        total: "$areas_total",
        povoamento: "$areas_povo",
        mato: "$areas_mato",
        agricola: "$areas_agri"
      }
    }
  },
  {
    $merge: {
      into: "ocorrencias",
      on: "codigo_incendio",
      whenMatched: "replace",
      whenNotMatched: "insert"
    }
  }
], { allowDiskUse: true });
