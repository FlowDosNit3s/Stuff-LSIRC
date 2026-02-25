const swaggerJsdoc = require("swagger-jsdoc");

const swaggerDefinition = {
  openapi: "3.0.0",
  info: {
    title: "Wildfires API",
    version: "1.0.0",
    description:
      "API REST para análise de incêndios florestais, meteorologia e recursos.\n\n" +
      "Funcionalidades principais:\n" +
      "- Importação de dados externos em XML com validação por XSD\n" +
      "- Endpoints analíticos baseados no MongoDB Aggregation Framework\n\n" +
      "Nota: os testes funcionais da importação XML são realizados via Postman; " +
      "o Swagger serve como documentação interativa.",
  },
  servers: [
    {
      url: "http://localhost:3000/api",
      description: "Servidor local",
    },
  ],
  tags: [
    { name: "Importação XML", description: "Receção de dados externos em XML (validação XSD)" },
    { name: "Análises", description: "Endpoints analíticos do Trabalho Prático" },
  ],

  paths: {
    /* ============================================================
       IMPORTAÇÃO XML
       ============================================================ */

    "/import/incendios": {
      post: {
        tags: ["Importação XML"],
        summary: "Importação de Incêndios (XML)",
        description:
          "Recebe um documento XML validado contra o IncendiosSchema.xsd " +
          "e armazena os dados na coleção intermédia raw_incendios.",
        requestBody: {
          required: true,
          content: {
            "application/xml": {
              schema: { type: "string" },
              example: `<?xml version="1.0" encoding="UTF-8"?>
<WildfireStats xmlns="http://www.wildfirestats.pt/schema">
  <Ocorrencia>
    <codigo_incendio>1942461</codigo_incendio>
    <data_inicio>2024-01-05</data_inicio>
    <duracao_horas>1.25</duracao_horas>
    <estado>Concluído</estado>
    <id_localizacao>88719</id_localizacao>
    <id_causa>357</id_causa>
    <areas>
      <total>1.7</total>
      <povoamento>0.5</povoamento>
      <mato>1.2</mato>
      <agricola>0.0</agricola>
    </areas>
  </Ocorrencia>
</WildfireStats>`
            }
          }
        },
        responses: {
          200: { description: "XML importado com sucesso" },
          400: { description: "XML inválido segundo o Schema XSD" },
        },
      },
    },

    "/import/meteorologia": {
      post: {
        tags: ["Importação XML"],
        summary: "Importação de Meteorologia (XML)",
        description:
          "Recebe um documento XML validado contra o MeteorologiaSchema.xsd " +
          "e armazena os dados na coleção intermédia raw_metereologia.",
        requestBody: {
          required: true,
          content: {
            "application/xml": {
              schema: { type: "string" },
              example: `<?xml version="1.0" encoding="UTF-8"?>
<MeteorologiaStats xmlns="http://www.wildfirestats.pt/schema">
  <Medicao>
    <data>2024-01-02</data>
    <id_localizacao>88719</id_localizacao>
    <medicoes>
      <temp_max>13.4</temp_max>
      <temp_min>9.1</temp_min>
      <temp_media>11.25</temp_media>
      <vento_max>25.8</vento_max>
      <vento_rafada>70.2</vento_rafada>
      <vento_direcao>184</vento_direcao>
      <precipitacao>37.4</precipitacao>
      <radiacao>0.59</radiacao>
      <insolacao>0</insolacao>
    </medicoes>
  </Medicao>
</MeteorologiaStats>`
            }
          }
        },
        responses: {
          200: { description: "XML importado com sucesso" },
          400: { description: "XML inválido segundo o Schema XSD" },
        },
      },
    },

    "/import/recursos": {
      post: {
        tags: ["Importação XML"],
        summary: "Importação de Recursos Humanos (XML)",
        description:
          "Recebe um documento XML validado contra o RecursosSchema.xsd " +
          "e armazena os dados na coleção recursos.",
        requestBody: {
          required: true,
          content: {
            "application/xml": {
              schema: { type: "string" },
              example: `<?xml version="1.0" encoding="UTF-8"?>
<RecursosHumanos xmlns="http://www.wildfirestats.pt/schema">
  <Alocacao>
    <Concelho>ABRANTES</Concelho>
    <Ano>2024</Ano>
    <Distrito>SANTARÉM</Distrito>
    <QuantidadeBombeiros>77</QuantidadeBombeiros>
  </Alocacao>
</RecursosHumanos>`
            }
          }
        },
        responses: {
          200: { description: "XML importado com sucesso" },
          400: { description: "XML inválido segundo o Schema XSD" },
        },
      },
    },

    /* ============================================================
       ANÁLISES
       ============================================================ */

    "/analises/media-area": {
      get: {
        tags: ["Análises"],
        summary: "Média de área ardida",
        description:
          "Calcula a média da área ardida por região (distrito), por tipo de área, " +
          "num determinado mês ou estação do ano.",
        parameters: [
          {
            in: "query",
            name: "month",
            schema: { type: "integer", minimum: 1, maximum: 12 },
            description: "Mês do ano (1–12)",
          },
          {
            in: "query",
            name: "season",
            schema: { type: "string", enum: ["Verao", "Inverno"] },
            description: "Estação do ano",
          },
        ],
        responses: {
          200: { description: "Resultado da análise" },
        },
      },
    },

    "/analises/fogo-vento": {
      get: {
        tags: ["Análises"],
        summary: "Correlação Fogo / Vento",
        description:
          "Lista incêndios com área ardida superior a um valor mínimo e apresenta " +
          "a velocidade do vento registada no dia do incêndio.",
        parameters: [
          {
            in: "query",
            name: "minArea",
            required: true,
            schema: { type: "number" },
            description: "Área mínima ardida (hectares)",
          },
        ],
        responses: {
          200: { description: "Lista de incêndios com dados de vento" },
        },
      },
    },

    "/analises/eficiencia-combate": {
      get: {
        tags: ["Análises"],
        summary: "Eficiência de combate",
        description:
          "Calcula a duração média dos incêndios por região, comparando Verão e Inverno.",
        parameters: [
          {
            in: "query",
            name: "region",
            schema: { type: "string", enum: ["distrito", "concelho"] },
            description: "Nível geográfico da análise",
          },
        ],
        responses: {
          200: { description: "Resultado da análise" },
        },
      },
    },

    "/analises/top-regioes": {
      get: {
        tags: ["Análises"],
        summary: "Top N regiões críticas",
        description:
          "Identifica regiões com maior número de incêndios em dias onde a " +
          "temperatura máxima ultrapassa um determinado valor.",
        parameters: [
          {
            in: "query",
            name: "temp",
            required: true,
            schema: { type: "number" },
            description: "Temperatura máxima mínima (limiar)",
          },
          {
            in: "query",
            name: "n",
            schema: { type: "integer", default: 10 },
            description: "Número de regiões a listar (Top N)",
          },
          {
            in: "query",
            name: "region",
            schema: { type: "string", enum: ["distrito", "concelho"] },
            description: "Nível geográfico da análise",
          },
        ],
        responses: {
          200: { description: "Lista das regiões críticas" },
        },
      },
    },

    "/analises/recursos": {
      get: {
        tags: ["Análises"],
        summary: "Análise de recursos",
        description:
          "Relaciona o número de incêndios com o número de bombeiros disponíveis " +
          "no município afetado.",
        responses: {
          200: { description: "Resultado da análise de recursos" },
        },
      },
    },

    "/analises/temperaturas": {
      get: {
        tags: ["Análises"],
        summary: "Análise de temperaturas por região",
        description:
          "Calcula a temperatura média, máxima e mínima por região, num intervalo temporal.",
        parameters: [
          {
            in: "query",
            name: "start",
            required: true,
            schema: { type: "string", example: "2024-02-02" },
            description: "Data de início (YYYY-MM-DD)",
          },
          {
            in: "query",
            name: "end",
            required: true,
            schema: { type: "string", example: "2024-05-01" },
            description: "Data de fim (YYYY-MM-DD)",
          },
          {
            in: "query",
            name: "region",
            schema: { type: "string", enum: ["distrito", "concelho"] },
            description: "Nível geográfico da análise",
          },
        ],
        responses: {
          200: { description: "Resultado da análise de temperaturas" },
        },
      },
    },
  },
};

module.exports = swaggerJsdoc({
  swaggerDefinition,
  apis: [],
});
