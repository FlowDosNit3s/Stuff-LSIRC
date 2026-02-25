const Incendio = require('../models/incendio');
const xml2js = require('xml2js');

// --- IMPORTAR (POST) ---
exports.criarIncendio = (req, res) => {
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
            // Verifica a raiz (WildfireStats ou direto)
            const root = result.WildfireStats || result;
            
            if (!root.Ocorrencia) {
                return res.status(400).json({ success: false, message: "XML sem dados de Ocorrências." });
            }

            const lista = Array.isArray(root.Ocorrencia) ? root.Ocorrencia : [root.Ocorrencia];

            const documentosParaSalvar = lista.map(item => ({
                codigo_incendio: parseInt(item.codigo_incendio),
                data_inicio: new Date(item.data_inicio),
                duracao_horas: parseFloat(item.duracao_horas),
                estado: item.estado,
                id_localizacao: parseInt(item.id_localizacao),
                id_causa: parseInt(item.id_causa),
                areas: {
                    total: parseFloat(item.areas?.total || 0),
                    povoamento: parseFloat(item.areas?.povoamento || 0),
                    mato: parseFloat(item.areas?.mato || 0),
                    agricola: parseFloat(item.areas?.agricola || 0)
                }
            }));

            await Incendio.insertMany(documentosParaSalvar);

            res.status(201).json({
                success: true,
                count: documentosParaSalvar.length,
                message: "Incêndios importados com a estrutura correta!"
            });

        } catch (mongoError) {
            if (mongoError.code === 11000) {
                 return res.status(409).json({ success: false, message: "Erro: Código de Incêndio duplicado." });
            }
            res.status(500).json({ success: false, error: mongoError.message });
        }
    });
};

// --- LISTAR (GET) ---
exports.listarIncendios = async (req, res) => {
    try {
        const lista = await Incendio.find().sort({ data_inicio: -1 });
        res.status(200).json({ success: true, count: lista.length, data: lista });
    } catch (error) {
        res.status(500).json({ success: false, error: error.message });
    }
};