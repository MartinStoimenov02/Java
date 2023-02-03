import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static Socket server;
    public static int type;
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
                loginMenu(sc, out);
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
        String option;
        System.out.println("search vehicle:");
        while(true){    //input: mark, model...
            option=sc.nextLine();
            System.out.println(option);
            out.println(console.nextLine().toLowerCase());
            if(option.startsWith("order")){   //input: order by (mark...
                printVehicles(console, sc, out);
                return;
            }
        }

    }

    public static void printVehicles(Scanner console, Scanner sc, PrintStream out) throws IOException {
        String read;
        while(true) {    //read vehicles with this params: 1)... 2)...
            read = sc.nextLine();
            System.out.println(read);
            if (read.startsWith("command")) { //if read: command(openVehicle, newSearch...
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
                        if(command.startsWith("date")){break;}  //start to read vehicle
                        out.println(console.nextInt()); //input number of vehicle to open
                    }
                    printOneVehicle(sc, out);
                }else if(command.equals("menu")){
                    type=0;
                    myMenu(console, sc, out);
                }
            }
        }
    }

    public static void printOneVehicle(Scanner sc, PrintStream out) throws IOException {
        Scanner console = new Scanner(System.in);
        String read;
        while(true) {   //read vehicle: date, mark, model...
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
                    if(type==1){    //if export to file before this command
                        sc.nextLine();
                        type=0;
                    }else{
                        sc.nextLine();
                        sc.nextLine();
                    }

                    searchVehicle(console, sc, out);
                }

                else if(command.equals("exporttofile")){
                    type=1;
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
                    if(type==1){
                        type=0;
                    }else{
                        sc.nextLine();
                    }
                    type=0;
                    myMenu(console, sc, out);
                }
            }
        }
    }

    public static void exportToFile(Scanner sc, PrintStream out){
        Scanner console = new Scanner(System.in);
        String read;
        String filePath=console.nextLine();
        out.println(filePath);
        read=sc.nextLine();
        System.out.println(read);   //print "The file has been created!" or "file exists"
        if(read.startsWith("The")){
            System.out.println(sc.nextLine());  //print "Ready!"
        }
        else{   //
            while(true){
                read=console.nextLine();    //rename, rewrite or add
                out.println(read);
                if(read.equals("rename")){
                    System.out.println(sc.nextLine());
                    //console.next();
                    read=console.nextLine();
                    out.println(read);
                    read=sc.nextLine();
                    if(read.startsWith("File")){    //if file is exist...
                        System.out.println(read);
                        continue;
                    }
                    else{
                        System.out.println(read);
                        System.out.println(sc.nextLine());
                    }
                    //console.nextLine();
                    break;
                }else if(read.equals("rewrite") || read.equals("add")){
                    System.out.println(sc.nextLine());
                    //console.nextLine();
                    break;
                }
                System.out.println(sc.nextLine());  //print "File's already exists!"
            }

        }
    }

    public static void loginMenu(Scanner sc, PrintStream out){
        Scanner console = new Scanner(System.in);
        String read;
        System.out.println("login:");
        System.out.println(sc.nextLine());     //username:
        String username=console.nextLine();     //read username
        out.println(username);
        System.out.println(sc.nextLine());      //password:
        String password=console.nextLine();     //read password
        out.println(password);
        read=sc.nextLine();
        System.out.println(read);
        if(read.startsWith("wrong")){       //wrong username or password, forgot password? Y/N
            forgotPassword(console, sc, out);
        }
    }

    public static void forgotPassword(Scanner console, Scanner sc, PrintStream out){
        String yesNo;
        yesNo=console.nextLine().toLowerCase(); //yes/no
        out.println(yesNo);
        if(!yesNo.equals("y")){return;}
        System.out.println(sc.nextLine());  //username:
        String username=console.nextLine();
        out.println(username);
        System.out.println(sc.nextLine());  //phone number:
        String phoneNumber=console.nextLine();
        out.println(phoneNumber);
        String read=sc.nextLine();
        if(read.startsWith("this")){    //this user doesn't exist
            System.out.println(read);
        }
        else{
            System.out.println(read);       //new password(It must contains...
            changePassword(username, phoneNumber, console, sc, out);
        }
    }

    public static void changePassword(String usrName, String number, Scanner console, Scanner sc, PrintStream out){
        String read;
        while(true){
            out.println(console.nextLine());        //read password
            System.out.println(sc.nextLine());      //repeat password:
            out.println(console.nextLine());        //read repeated password
            read=sc.nextLine();
            System.out.println(read);
            if(read.startsWith("your")){            //your password has been changed
                break;
            }
            System.out.println(sc.nextLine());      //error message
        }
    }

    public static void signupMenu(Scanner console, Scanner sc, PrintStream out){
        System.out.println("signup:");
        String read;
        System.out.println(sc.nextLine());
        String name=console.nextLine();
        out.println(name);
        System.out.println(sc.nextLine());
        String phoneNumber=console.nextLine();
        out.println(phoneNumber);
        System.out.println(sc.nextLine());
        String username=console.nextLine();
        out.println(username);
        System.out.println(sc.nextLine());
        String password=console.nextLine();
        out.println(password);
        System.out.println(sc.nextLine());  //print message: created profile, or error
    }
}
