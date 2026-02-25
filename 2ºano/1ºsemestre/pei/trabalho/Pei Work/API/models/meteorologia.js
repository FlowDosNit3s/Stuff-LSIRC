const mongoose = require('mongoose');

const MeteorologiaSchema = new mongoose.Schema({
    data: String,           // Ex: "2024-01-02"
    id_localizacao: Number, // Ex: 88719
    medicoes: {
        temp_max: Number,
        temp_min: Number,
        temp_media: Number,
        vento_max: Number,
        vento_rafada: Number,
        vento_direcao: Number,
        precipitacao: Number,
        radiacao: Number,
        insolacao: Number
    }
}, { timestamps: true });

// Evitar duplicados para o mesmo local na mesma data
MeteorologiaSchema.index({ data: 1, id_localizacao: 1 }, { unique: true });

module.exports = mongoose.model('Meteorologia', MeteorologiaSchema);