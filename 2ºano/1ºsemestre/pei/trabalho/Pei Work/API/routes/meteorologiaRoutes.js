const express = require('express');
const router = express.Router();
const meteorologiaController = require('../controllers/meteorologiaController');
const validateXML = require('../middleware/validateXML'); // <--- Importar o segurança

// POST com Validação (Ativa a proteção!)
router.post('/', validateXML('meteorologiaSchema.xsd'), meteorologiaController.criarMeteorologia);

// GET
router.get('/', meteorologiaController.listarMeteorologia);

module.exports = router;