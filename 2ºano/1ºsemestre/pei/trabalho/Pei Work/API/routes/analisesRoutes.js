const express = require('express');
const router = express.Router();
const analisesController = require('../controllers/analisesController');

router.get('/media-area', analisesController.mediaArea);
router.get('/fogo-vento', analisesController.fogoVento);
router.get('/eficiencia-combate', analisesController.eficienciaCombate);
router.get('/top-regioes', analisesController.topRegioes);
router.get('/recursos', analisesController.analiseRecursos);
router.get('/temperaturas', analisesController.temperaturasPorRegiao);

module.exports = router;
