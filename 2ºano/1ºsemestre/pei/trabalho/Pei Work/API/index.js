const express = require('express');
const bodyParser = require('body-parser');
const morgan = require('morgan');
const connectDB = require('./config/database');
const { errorHandler, notFound } = require('./middleware/errorHandler');
require('dotenv').config();

const swaggerUi = require('swagger-ui-express');
const swaggerSpec = require('./docs/swagger');

// Rotas (importação XML + coleções finais do TP)
const incendiosRoutes = require('./routes/incendiosRoutes');
const meteorologiaRoutes = require('./routes/meteorologiaRoutes');
const recursosRoutes = require('./routes/recursosRoutes');
const analisesRoutes = require('./routes/analisesRoutes');
const importRoutes = require('./routes/importRoutes');

// 1) Ligar à Base de Dados
connectDB();

const app = express();
const PORT = process.env.PORT || 3000;

// Logs HTTP (útil para debug)
app.use(morgan('dev'));

// Body parsers:
// - XML como texto (para validação XSD + parse)
// - JSON para endpoints analíticos
app.use(bodyParser.text({ type: ['application/xml', 'text/xml', '+xml'], limit: '10mb' }));
app.use(express.json({ limit: '1mb' }));

// 2) Registar Rotas
app.use('/api/incendios', incendiosRoutes);
app.use('/api/meteorologia', meteorologiaRoutes);
app.use('/api/recursos', recursosRoutes);
app.use('/api/analises', analisesRoutes);

app.use('/api/import', importRoutes);
app.use('/api/docs', swaggerUi.serve, swaggerUi.setup(swaggerSpec));

// Rota Base
app.get('/', (req, res) => {
  res.json({ status: 'API Online', db: 'Connected' });
});

// 3) Middlewares finais
app.use(notFound);
app.use(errorHandler);

app.listen(PORT, () => {
  console.log(`Servidor a correr na porta ${PORT}`);
});
