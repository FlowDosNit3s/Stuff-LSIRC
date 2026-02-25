const Meteorologia = require('../models/meteorologia');
const xml2js = require('xml2js');

// --- IMPORTAR (POST) ---
exports.criarMeteorologia = (req, res) => {
    const xmlData = req.body;

    const parser = new xml2js.Parser({ 
        explicitArray: false, 
        ignoreAttrs: true, 
        tagNameProcessors: [xml2js.processors.stripPrefix] 
    });

    parser.parseString(xmlData, async (err, result) => {
        if (err) {
            return res.status(500).json({ success: false, message: "Erro ao converter XML." });
        }

        try {
            const root = result.MeteorologiaStats || result;
            
            if (!root.Medicao) {
                return res.status(400).json({ success: false, message: "XML sem dados de Medição." });
            }

            const lista = Array.isArray(root.Medicao) ? root.Medicao : [root.Medicao];

            const dadosParaSalvar = lista.map(item => ({
                data: item.data,
                id_localizacao: parseInt(item.id_localizacao),
                medicoes: {
                    temp_max: parseFloat(item.medicoes.temp_max),
                    temp_min: parseFloat(item.medicoes.temp_min),
                    temp_media: parseFloat(item.medicoes.temp_media),
                    vento_max: parseFloat(item.medicoes.vento_max),
                    vento_rafada: parseFloat(item.medicoes.vento_rafada),
                    vento_direcao: parseInt(item.medicoes.vento_direcao),
                    precipitacao: parseFloat(item.medicoes.precipitacao),
                    radiacao: parseFloat(item.medicoes.radiacao),
                    insolacao: parseFloat(item.medicoes.insolacao)
                }
            }));

            await Meteorologia.insertMany(dadosParaSalvar);

            res.status(201).json({
                success: true,
                count: dadosParaSalvar.length,
                message: "Dados meteorológicos importados com estrutura correta!"
            });

        } catch (error) {
            if (error.code === 11000) {
                return res.status(409).json({ success: false, message: "Dados duplicados (Mesma data e local)." });
            }
            res.status(500).json({ success: false, error: error.message });
        }
    });
};

// --- LISTAR (GET) ---
exports.listarMeteorologia = async (req, res) => {
    try {
        const lista = await Meteorologia.find().sort({ data: -1 });
        res.status(200).json({ success: true, count: lista.length, data: lista });
    } catch (error) {
        res.status(500).json({ success: false, error: error.message });
    }
};