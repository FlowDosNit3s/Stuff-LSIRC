const mongoose = require('mongoose');

const IncendioSchema = new mongoose.Schema({
    codigo_incendio: { type: Number, unique: true }, // Ex: 1942461
    data_inicio: Date,                               // Ex: 2024-01-05...
    duracao_horas: Number,                           // Ex: 0.63
    estado: String,                                  // Ex: "Concluído"
    id_localizacao: Number,                          // Ex: 87378
    id_causa: Number,                                // Ex: 376
    areas: {
        total: Number,
        povoamento: Number,
        mato: Number,
        agricola: Number
    }
}, { timestamps: true });

module.exports = mongoose.model('Incendio', IncendioSchema);