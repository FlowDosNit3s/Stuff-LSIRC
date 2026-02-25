const Recursos = require('../models/recursos');
const xml2js = require('xml2js');

// --- IMPORTAR (POST) ---
exports.criarRecurso = (req, res) => {
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
            // O TEU XSD TEM RAIZ "RecursosHumanos"
            const root = result.RecursosHumanos || result;
            
            // O TEU XSD TEM LISTA CHAMADA "Alocacao"
            if (!root.Alocacao) {
                return res.status(400).json({ success: false, message: "XML sem dados de Alocação." });
            }

            const lista = Array.isArray(root.Alocacao) ? root.Alocacao : [root.Alocacao];

            // Mapear os campos do teu XSD
            const dadosParaSalvar = lista.map(item => ({
                Concelho: item.Concelho,
                Ano: parseInt(item.Ano),
                Distrito: item.Distrito,
                QuantidadeBombeiros: parseInt(item.QuantidadeBombeiros)
            }));

            await Recursos.insertMany(dadosParaSalvar);

            res.status(201).json({
                success: true,
                count: dadosParaSalvar.length,
                message: "Recursos Humanos importados com sucesso!"
            });

        } catch (error) {
            res.status(500).json({ success: false, error: error.message });
        }
    });
};

// --- LISTAR (GET) ---
exports.listarRecursos = async (req, res) => {
    try {
        const lista = await Recursos.find().sort({ Ano: -1 });
        res.status(200).json({ success: true, count: lista.length, data: lista });
    } catch (error) {
        res.status(500).json({ success: false, error: error.message });
    }
};
