import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static Socket server;
    public static void main(String[] args) {
        server = null;
        Scanner console = null;
        Scanner sc = null;

        try
        {
            server = new Socket("localhost", 8080);
            console = new Scanner(System.in);
            sc = new Scanner(server.getInputStream());
            PrintStream out = new PrintStream(server.getOutputStream());

            myMenu(console, sc, out);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void myMenu(Scanner console, Scanner sc, PrintStream out) throws IOException {
        do{
            System.out.println(sc.nextLine()); //command(search, login, signUp, end)
            out.println(console.nextLine().toLowerCase());
            String next = sc.nextLine();
            //System.out.println(next);
            if(next.equals("search")){
                searchVehicle(console, sc, out);
            }else if(next.equals("login")){
                loginMenu(console, sc, out);
            }else if(next.equals("signup")){
                signupMenu(console, sc, out);
            }else if(next.equals("goodbye!")){
                System.out.println(next);
                server.close();
                System.exit(14);
            }else{
                out.println("error");
            }
        }while(true);
    }

    public static void searchVehicle(Scanner console, Scanner sc, PrintStream out) throws IOException {
        String read;
        System.out.println("search vehicle:");
        while(true){    //mark, model...
            read=sc.nextLine();
            System.out.println(read);
            out.println(console.nextLine().toLowerCase());
            if(read.startsWith("order")){   //order by (mark...
                printVehicles(console, sc, out);
                return;
            }
        }

    }

    public static void printVehicles(Scanner console, Scanner sc, PrintStream out) throws IOException {
        String read;
        while(true) {    //1)... 2)...
            read = sc.nextLine();
            System.out.println(read);
            if (read.startsWith("command")) { //command(openVehicle, newSearch...
                String command = console.nextLine().toLowerCase();    //read command
                out.println(command);
                if(command.equals("newsearch")){
                    sc.nextLine();
                    searchVehicle(console, sc, out);
                }else if(command.equals("openvehicle")){
                    while(true){
                        command=sc.nextLine();
                        System.out.println(command);
                        //out.println("");
                        if(command.startsWith("date")){break;}
                        out.println(console.nextInt());
                    }
                    printOneVehicle(sc, out);
                }else if(command.equals("menu")){
                    myMenu(console, sc, out);
                }
            }
        }
    }

    public static void printOneVehicle(Scanner sc, PrintStream out) throws IOException {
        Scanner console = new Scanner(System.in);
        String read;
        while(true) {
            read = sc.nextLine();
            System.out.println(read);
            if (read.startsWith("command")) {
                String command=console.nextLine().toLowerCase();    //read command
                    out.println(command);
                    if(command.equals("backtoads")){
                        sc.nextLine();
                        printVehicles(console, sc, out);
                    }

                    else if(command.equals("newsearch")){
                        sc.nextLine();
                        sc.nextLine();
                        searchVehicle(console, sc, out);
                    }

                    else if(command.equals("exporttofile")){
                        read=sc.nextLine();
                        if(read.startsWith("input")){
                            System.out.println(read);
                        }
                        else{
                            System.out.println(sc.nextLine());

                        }
                        exportToFile(sc, out);
                    }

                    else if(command.equals("menu")){
                        sc.nextLine();
                        myMenu(console, sc, out);
                    }
            }
        }
        //sc.nextLine();
    }

    public static void exportToFile(Scanner sc, PrintStream out){
        Scanner console = new Scanner(System.in);
        String read;
        read=console.nextLine();
        out.println(read);
        read=sc.nextLine();
        System.out.println(read);
        if(read.startsWith("The")){
            System.out.println(sc.nextLine());
        }
        else{
            //while(true){
                read=console.next();
                out.println(read);
                if(read.equals("rename")){
                    System.out.println(sc.nextLine());
                    console.nextLine();
                    read=console.nextLine();
                    out.println(read);
                    System.out.println(sc.nextLine());
                    System.out.println(sc.nextLine());
                    //console.nextLine();
                }else if(read.equals("rewrite") || read.equals("add")){
                    System.out.println(sc.nextLine());
                    console.nextLine();
                }
            //}

        }
    }

    public static void loginMenu(Scanner console, Scanner sc, PrintStream out){
        System.out.println("login");
    }

    public static void signupMenu(Scanner console, Scanner sc, PrintStream out){
        System.out.println("signup");
    }
}
