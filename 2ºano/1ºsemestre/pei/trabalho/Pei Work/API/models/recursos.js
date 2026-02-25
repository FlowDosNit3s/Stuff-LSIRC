const mongoose = require('mongoose');

/**
 * Modelo usado apenas para IMPORTAÇÃO XML (estrutura do XSD).
 * Para as análises do TP, utiliza-se a coleção final "recursos" (concelho/ano/numero_bombeiros),
 * criada a partir dos dados raw no MongoDB Compass.
 */
const RecursosXMLSchema = new mongoose.Schema(
  {
    Concelho: String,
    Ano: Number,
    Distrito: String,
    QuantidadeBombeiros: Number
  },
  { timestamps: true, collection: 'xml_recursos' }
);

module.exports = mongoose.model('RecursosXML', RecursosXMLSchema);
