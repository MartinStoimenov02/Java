import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static int LISTENING_PORT = 3333;

    public static void main(String[] args) throws IOException {
        @SuppressWarnings("resource")
        ServerSocket serverSocket = new ServerSocket(LISTENING_PORT);
        System.out.println("Server started...");
        while (true) {
            Socket socket = serverSocket.accept();
            new ServerStart(socket).start();
        }
    }
}
