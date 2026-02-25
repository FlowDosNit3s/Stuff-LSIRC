const express = require('express');
const router = express.Router();
const incendiosController = require('../controllers/incendiosController');
const validateXML = require('../middleware/validateXML'); // <--- IMPORTAR

// POST com Validação (O "Segurança" está ativo!)
router.post('/', validateXML('incendiosSchema.xsd'), incendiosController.criarIncendio);

// GET (Listagem)
router.get('/', incendiosController.listarIncendios);

module.exports = router;