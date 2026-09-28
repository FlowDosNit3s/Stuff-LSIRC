import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CommandServer {
    public static final int DEFAULT_PORT = 5050;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) {
        int port = (args.length > 0) ? Integer.parseInt(args[0]) : DEFAULT_PORT;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor de Comandos à escuta no porto " + port + "...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    System.out.println("Cliente ligado: " + clientSocket.getRemoteSocketAddress());
                    String line;

                    while ((line = in.readLine()) != null) {
                        System.out.println("Recebido: " + line);
                        String response = processCommand(line.trim());
                        out.println(response);
                    }

                    System.out.println("Cliente desligou: " + clientSocket.getRemoteSocketAddress());
                } catch (IOException e) {
                    System.err.println("Erro na comunicação com o cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Não foi possível iniciar o servidor no porto " + port + ": " + e);
        }
    }

    private static String processCommand(String input) {
        if (input.isEmpty()) {
            return "ERRO";
        }

        // Divide a entrada por qualquer número de espaços em branco
        String[] parts = input.split("\\s+");
        String command = parts[0].toUpperCase();

        switch (command) {
            case "TIME":
                if (parts.length == 1) {
                    return LocalTime.now().format(TIME_FORMATTER);
                }
                return "ERRO";

            case "ADD":
                if (parts.length == 3) {
                    try {
                        long a = Long.parseLong(parts[1]);
                        long b = Long.parseLong(parts[2]);
                        return String.valueOf(a + b);
                    } catch (NumberFormatException e) {
                        return "ERRO";
                    }
                }
                return "ERRO";

            default:
                return "ERRO";
        }
    }
}