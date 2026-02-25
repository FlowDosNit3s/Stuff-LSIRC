const express = require('express');
const router = express.Router();
const recursosController = require('../controllers/recursosController');
const validateXML = require('../middleware/validateXML'); // <--- Importar

// POST com Validação ATIVA
router.post('/', validateXML('recursosSchema.xsd'), recursosController.criarRecurso);

// GET
router.get('/', recursosController.listarRecursos);

module.exports = router;