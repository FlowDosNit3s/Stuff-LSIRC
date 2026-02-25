const express = require("express");
const router = express.Router();

const validateAndParseXML = require("../middleware/validateXML");
const importController = require("../controllers/importController");

// 3 endpoints (um por schema)
router.post(
  "/incendios",
  validateAndParseXML("IncendiosSchema.xsd"),
  importController.importIncendios
);

router.post(
  "/meteorologia",
  validateAndParseXML("MeteorologiaSchema.xsd"),
  importController.importMeteorologia
);

router.post(
  "/recursos",
  validateAndParseXML("RecursosSchema.xsd"),
  importController.importRecursos
);

module.exports = router;
