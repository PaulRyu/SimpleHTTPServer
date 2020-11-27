import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class SimpleHTTPServer {
    public static void main (String[] args) throws Exception {
        final ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening on port 8080...");

        // Endlessly loop for now
        while (true) {

            // Accepts HTTP connection and outputs the current date to the connection
            try (Socket sock = server.accept()) {
                Date today = new Date();
                String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
                sock.getOutputStream().write(httpResponse.getBytes(StandardCharsets.UTF_8));
            }

            // Accepts HTTP connection and reads information sent by the connection
            final Socket client = server.accept();
            InputStreamReader isr = new InputStreamReader(client.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            String line = reader.readLine();
            while (!line.isEmpty()) {
                System.out.println(line);
                line = reader.readLine();
            }
        }
    }
}
