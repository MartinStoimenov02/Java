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
        while(true){    //1)... 2)...
            read=sc.nextLine();
            System.out.println(read);
            if(read.startsWith("command")){ //command(openVehicle, newSearch...
                read=console.nextLine().toLowerCase();    //read command
                out.println(read);
                if(read.equals("newsearch")){
                    sc.nextLine();
                    searchVehicle(console, sc, out);
                }else if(read.equals("openvehicle")){
                    while(true){
                        read=sc.nextLine();
                        System.out.println(read);
                        //out.println("");
                        if(read.startsWith("date")){break;}
                        out.println(console.nextInt());
                    }
                    printOneVehicle(console, sc, out);
                }else if(read.equals("menu")){
                    myMenu(console, sc, out);
                }
            }
        }
    }

    public static void printOneVehicle(Scanner console, Scanner sc, PrintStream out) throws IOException {
        String read;
        while(true) {
            read = sc.nextLine();
            if (read.startsWith("command")) {
                break;
            }
            System.out.println(read);
        }
        read=console.nextLine().toLowerCase();    //read command
        out.println(read);
        if(read.equals("backtoads")){
            printVehicles(console, sc, out);
        }

        else if(read.equals("newsearch")){
            sc.nextLine();
            searchVehicle(console, sc, out);
        }

        else if(read.equals("exporttofile")){
            exportToFile(console, sc, out);
        }

        else if(read.equals("menu")){
            myMenu(console, sc, out);
        }
        sc.nextLine();
    }

    public static void exportToFile(Scanner console, Scanner sc, PrintStream out){
        String read;
        System.out.println("exporttofile function");
        out.println("C:\\Users\\marti\\Downloads\\Toyota.txt");

//        read=console.nextLine();
//        out.println(read);
        read=sc.nextLine();
        System.out.println(read);
        if(read.startsWith("The")){
            System.out.println(sc.nextLine());
        }
        else{
            System.out.println(sc.nextLine());
            read=console.next();
            out.println(read);
            if(read.equals("rename")){
                System.out.println(sc.nextLine());
                read=console.nextLine();
                out.println(read);
            }else if(read.equals("rewrite") || read.equals("add")){
                System.out.println(sc.nextLine());
            }
        }
    }

    public static void loginMenu(Scanner console, Scanner sc, PrintStream out){
        System.out.println("login");
    }

    public static void signupMenu(Scanner console, Scanner sc, PrintStream out){
        System.out.println("signup");
    }
}
