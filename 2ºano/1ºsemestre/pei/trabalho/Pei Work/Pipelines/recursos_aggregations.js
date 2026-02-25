//5) Análise de Recursos
db.getCollection('recursos').aggregate(
  [
    {
      $lookup: {
        from: 'localizacoes',
        let: { c: '$concelho' },
        pipeline: [
          {
            $match: {
              $expr: { $eq: ['$concelho', '$$c'] }
            }
          },
          {
            $group: {
              _id: '$concelho',
              ids: {
                $addToSet: '$id_localizacao'
              }
            }
          }
        ],
        as: 'locs'
      }
    },
    {
      $addFields: {
        ids: {
          $ifNull: [
            {
              $getField: {
                field: 'ids',
                input: { $first: '$locs' }
              }
            },
            []
          ]
        }
      }
    },
    {
      $lookup: {
        from: 'ocorrencias',
        let: { ids: '$ids' },
        pipeline: [
          {
            $match: {
              $expr: {
                $in: ['$id_localizacao', '$$ids']
              }
            }
          },
          { $count: 'n_incendios' }
        ],
        as: 'fires'
      }
    },
    {
      $addFields: {
        n_incendios: {
          $ifNull: [
            {
              $getField: {
                field: 'n_incendios',
                input: { $first: '$fires' }
              }
            },
            0
          ]
        }
      }
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
            {
              $multiply: [
                {
                  $divide: [
                    '$n_incendios',
                    '$numero_bombeiros'
                  ]
                },
                100
              ]
            },
            null
          ]
        }
      }
    },
    { $sort: { n_incendios: -1 } }
  ],
  { maxTimeMS: 60000, allowDiskUse: true }
);