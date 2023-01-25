import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ServerStart extends Thread{
    private Socket socket;
    private PrintWriter socketWriter;

    public static List<Message> messages;

    public ServerStart(Socket socket) throws IOException {
        setSocket(socket);
        this.socketWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public synchronized void print() {
        try {
            Scanner socketIn = new Scanner(new BufferedReader(new InputStreamReader(socket.getInputStream())));

            String command = null;
            do {
                socketWriter.flush();
                command = socketIn.nextLine();
                String userName, password;
                socketWriter.println("client/tech/exit:");
                switch (command) {
                    case "Client":
                        socketWriter.println("user name:");
                        userName=socketIn.nextLine();
                        socketWriter.println("password:");
                        password=socketIn.nextLine();
                        client(userName, password);
                        break;
                    case "tech":
                        socketWriter.println("user name:");
                        userName=socketIn.nextLine();
                        socketWriter.println("password:");
                        password=socketIn.nextLine();
                        tech(userName, password);
                        break;
                    default:
                        socketWriter.println("try again!");
                        break;
                }
            } while (!command.equalsIgnoreCase("exit"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        print();
    }

    public void client(String userName, String password) throws IOException {
        Scanner socketIn = new Scanner(new BufferedReader(new InputStreamReader(socket.getInputStream())));
        String message;
        socketWriter.println("message: ");
        message=socketIn.nextLine();
        Message msg1 = new Message(1, message, false, 0);
        messages.add(msg1);
    }

    public void tech(String userName, String password) throws IOException {
        Scanner socketIn = new Scanner(new BufferedReader(new InputStreamReader(socket.getInputStream())));

        for(int i=0;i<messages.size();i++){
            socketWriter.println(messages.get(i).toString());
        }
        int idOfMsg, idOfTS;
        String answer;
        socketWriter.println("choose message: ");
        idOfMsg=socketIn.nextInt();
        Message msg2 = messages.get(idOfMsg);
        socketWriter.println("Answer: ");
        answer=socketIn.nextLine();
        socketWriter.println(answer);
        socketWriter.println("your id: ");
        idOfTS=socketIn.nextInt();
        msg2.setIdTS(idOfTS);
        msg2.setStatus(true);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
