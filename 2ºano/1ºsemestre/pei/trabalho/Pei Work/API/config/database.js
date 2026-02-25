const mongoose = require('mongoose');

const connectDB = async () => {
  try {
    const conn = await mongoose.connect(process.env.MONGODB_URI);

    console.log(`MongoDB ligado: ${conn.connection.host}`);
    console.log(`Base de dados: ${conn.connection.name}`);

    // Listeners para monitorização
    mongoose.connection.on('error', (err) => {
      console.error(' Erro na ligação ao MongoDB:', err);
    });

    mongoose.connection.on('disconnected', () => {
      console.log('MongoDB desligado');
    });

    process.on('SIGINT', async () => {
      await mongoose.connection.close();
      console.log('Ligação ao MongoDB fechada devido ao término da aplicação');
      process.exit(0);
    });

  } catch (error) {
    console.error('Erro ao ligar ao MongoDB:', error.message);
    process.exit(1);
  }
};

module.exports = connectDB;