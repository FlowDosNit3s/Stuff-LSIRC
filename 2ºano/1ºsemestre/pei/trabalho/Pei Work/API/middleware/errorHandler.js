/**
 * Middleware para tratamento de erros
 * Segue as boas práticas de APIs REST
 */

const errorHandler = (err, req, res, next) => {
  console.error('❌ Erro:', err);

  // Erro de validação do Mongoose
  if (err.name === 'ValidationError') {
    const errors = Object.values(err.errors).map(e => e.message);
    return res.status(400).json({
      success: false,
      error: 'Erro de validação',
      details: errors
    });
  }

  // Erro de cast do Mongoose (ID inválido)
  if (err.name === 'CastError') {
    return res.status(400).json({
      success: false,
      error: 'ID inválido',
      details: err.message
    });
  }

  // Erro de duplicação (unique constraint)
  if (err.code === 11000) {
    return res.status(400).json({
      success: false,
      error: 'Recurso já existe',
      details: 'Já existe um registo com esses dados'
    });
  }

  // Erro de sintaxe JSON
  if (err instanceof SyntaxError && err.status === 400 && 'body' in err) {
    return res.status(400).json({
      success: false,
      error: 'JSON inválido',
      details: err.message
    });
  }

  // Erro genérico
  const statusCode = err.statusCode || 500;
  res.status(statusCode).json({
    success: false,
    error: err.message || 'Erro interno do servidor',
    ...(process.env.NODE_ENV === 'development' && { stack: err.stack })
  });
};

// Middleware para rotas não encontradas
const notFound = (req, res, next) => {
  res.status(404).json({
    success: false,
    error: 'Rota não encontrada',
    path: req.originalUrl
  });
};

module.exports = { errorHandler, notFound };
