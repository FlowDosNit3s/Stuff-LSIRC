//6.1) Análise de temperaturas por distrito
db.getCollection('metereologia').aggregate(
  [
    {
      $match: {
        data: {
          $gte: '2024-02-02',
          $lte: '2024-05-01'
        }
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
        temp_media: {
          $avg: '$medicoes.temp_media'
        },
        temp_max: { $avg: '$medicoes.temp_max' },
        temp_min: { $avg: '$medicoes.temp_min' },
        dias: { $sum: 1 }
      }
    },
    { $sort: { temp_media: -1 } }
  ],
  { maxTimeMS: 60000, allowDiskUse: true }
);

//6.2) Análise de temperaturas por concelho
db.getCollection('metereologia').aggregate(
  [
    {
      $match: {
        data: {
          $gte: '2024-02-02',
          $lte: '2024-05-01'
        }
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
        _id: '$loc.concelho',
        temp_media: {
          $avg: '$medicoes.temp_media'
        },
        temp_max: { $avg: '$medicoes.temp_max' },
        temp_min: { $avg: '$medicoes.temp_min' },
        dias: { $sum: 1 }
      }
    },
    { $sort: { temp_media: -1 } }
  ],
  { maxTimeMS: 60000, allowDiskUse: true }
);