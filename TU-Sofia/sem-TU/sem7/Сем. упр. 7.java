import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server
{
    public static void main(String[] args)
    {
        new Server().start();
    }

    private List<PrintStream> clients;
    private final Object clientsLock;

    private class ServerRunnable implements Runnable
    {
        private final Socket client;

        public ServerRunnable(Socket client)
        {
            this.client = client;
        }

        @Override
        public void run()
        {
            try (Scanner in = new Scanner(client.getInputStream());
                 PrintStream out = new PrintStream(client.getOutputStream()))
            {
                synchronized (clientsLock)
                {
                    clients.add(out);
                }

                while (true)
                {
                    String line = in.nextLine();
                    if (line.contains("quit"))
                    {
                        synchronized (clientsLock)
                        {
                            clients.remove(out);
                            return;
                        }
                    }
                    synchronized (clientsLock)
                    {
                        for (PrintStream c : clients)
                            c.println(line);
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public Server()
    {
        clients = new ArrayList<>();
        clientsLock = new Object();
    }

    public void start()
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(8080);

            while (true)
            {
                Socket client = serverSocket.accept();
                Thread clientThread = new Thread(new ServerRunnable(client));
                clientThread.start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
        new Client().start();
    }

    private String myName;
    private volatile boolean shouldStop;

    public Client()
    {
        myName = null;
        shouldStop = false;
    }

    public void start()
    {
        try
        {
            Socket server = new Socket("localhost", 8080);

            Scanner in = new Scanner(server.getInputStream());
            PrintStream out = new PrintStream(server.getOutputStream());

            Thread reader = new Thread(() ->
            {
                while (!shouldStop)
                {
                    if (in.hasNextLine())
                    {
                        String line = in.nextLine();
                        if (!line.startsWith(myName))
                            System.out.println(line);
                    }
                }
            });

            Thread writer = new Thread(() ->
            {
                Scanner console = new Scanner(System.in);

                System.out.println("Enter name:");
                myName = console.nextLine();

                String line = null;

                while (true)
                {
                    line = console.nextLine();
                    out.println(myName + ": " + line);
                    if (line.contains("quit"))
                    {
                        shouldStop = true;
                        try
                        {
                            server.close();
                            in.close();
                            out.close();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        return;
                    }
                }
            });

            reader.start();
            writer.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
