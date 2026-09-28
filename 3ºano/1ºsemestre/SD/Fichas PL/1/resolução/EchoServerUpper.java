import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerUpper {
    public static final int DEFAULT_PORT = 5050;

    public static void main(String[] args) {
        int port = (args.length > 0) ? Integer.parseInt(args[0]) : DEFAULT_PORT;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor Echo à escuta no porto " + port + "...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    System.out.println("Cliente ligado: " + clientSocket.getRemoteSocketAddress());
                    String line;

                    while ((line = in.readLine()) != null) {
                        System.out.println("Recebido: " + line);

                        if ("BYE".equalsIgnoreCase(line.trim())) {
                            out.println("BYE");
                            break; // Sai do ciclo e fecha a ligação com este cliente
                        }

                        // Devolve o texto recebido em maiúsculas
                        out.println(line.toUpperCase());
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
}