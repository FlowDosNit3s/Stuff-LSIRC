const { XMLParser } = require('fast-xml-parser');
const validator = require('xsd-schema-validator');
const path = require('path');

// Configuração do parser XML
const parser = new XMLParser({
    explicitArray: false,
    ignoreAttributes: false,
    attributeNamePrefix: "a_",
    numberParseOptions: {
        hex: true,
        leadingZeros: false
    }
});

/**
 * Middleware Factory para validar XSD e converter XML para JSON
 * @param {string} schemaFilename
 */
const validateAndParseXML = (schemaFilename) => {
    return async (req, res, next) => {
        // Verifica se o Content-Type é XML
        if (
            req.get('Content-Type') &&
            (req.get('Content-Type').includes('xml')) &&
            typeof req.body === 'string'
        ) {
            console.log(`--> A validar XML com o schema: ${schemaFilename}...`);

            try {
                // 1. Definir o caminho do XSD
                const schemaPath = path.join(__dirname, '../schemas', schemaFilename);

                // 2. Validar o XML contra o XSD
                await validator.validateXML(req.body.trim(), schemaPath);

                // 3. Se passar na validação, faz o Parse para JSON
                const parsed = parser.parse(req.body);
                req.body = parsed;

                next();

            } catch (err) {
                return res.status(400).json({
                    success: false,
                    status: 'error',
                    message: "XML inválido segundo o Schema XSD.",
                    details: err.message,
                    validationErrors: err.messages
                });
            }
        } else {
            next();
        }
    };
};

module.exports = validateAndParseXML;