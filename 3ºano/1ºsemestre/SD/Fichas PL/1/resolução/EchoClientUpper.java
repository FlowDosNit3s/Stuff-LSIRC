import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClientUpper {
    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 5050;

    public static void main(String[] args) {
        String host = (args.length > 0) ? args[0] : DEFAULT_HOST;
        int port = (args.length > 1) ? Integer.parseInt(args[1]) : DEFAULT_PORT;

        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Ligado a " + host + ":" + port + ". Sair: escreva BYE ou Ctrl+D/Ctrl+Z.");

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                String response = in.readLine();

                if (response == null) {
                    System.out.println("O servidor fechou a ligação.");
                    break;
                }

                System.out.println("eco: " + response);

                if ("BYE".equalsIgnoreCase(userInput.trim())) {
                    break; // Sai do ciclo limpo sem erros
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Host desconhecido: " + host);
        } catch (IOException e) {
            System.err.println("Não foi possível ligar a " + host + ":" + port + " (" + e.getMessage() + ")");
        }
    }
}