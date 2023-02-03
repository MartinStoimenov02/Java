import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {
    private ServerSocket server;

    public static int searchOrMyContacts;   //1 for searching contact, 0 for my contacts
    public void start(){
        try{
            //myMenu(new Scanner(System.in), System.out);   //for testing!
            System.out.println("server start!");
            server=new ServerSocket(8080);
            while(true){
                Socket client = server.accept();

                Thread clientThread = new Thread(() -> {
                    Scanner sc=null;
                    PrintStream out=null;
                    try{
                        sc=new Scanner(client.getInputStream());
                        out=new PrintStream(client.getOutputStream());
                        myMenu(sc, out);
                    }catch(IOException | SQLException | SignUpException e){e.printStackTrace();}
                });
                clientThread.start();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                System.out.println(clientThread+" is in server at "+dtf.format(now));
            }
        }catch (IOException e){e.printStackTrace();}
//        catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (SignUpException e) {
//            throw new RuntimeException(e);
//        }
    }

    public static void myMenu(Scanner sc, PrintStream out) throws SQLException, SignUpException {
        while(true){
                out.println("command(login, signUp, end)");
                String command=sc.nextLine().toLowerCase();

                switch (command) {
                    case "signup" -> {
                        SignUp signUp = new SignUp();
                        signUp.signUp(sc, out);
                    }
                    case "login" -> {
                        Login login = new Login();
                        login.loginInfo(sc, out);
                    }
                    case "end" -> {
                        System.out.println("client disconnected!\n");
                        out.println("goodbye!");
                        return;
                        //System.exit(14);
                    }
                }
        }
    }

    public static void loginMenu(int idUsr, Scanner sc, PrintStream out) throws SQLException, SignUpException {
        while(true) {
            out.println("command(exit, newMail, inbox, sent, myContacts, edit)");
            String command = sc.nextLine().toLowerCase();
            switch (command) {
                case "newmail":
                    searchOrMyContacts=1;
                    SearchUsers.makeQuery(idUsr, sc, out);
                    break;
                case "inbox":
                    MailboxOptions.mailBox(out, sc, idUsr, "inbox");
                    break;
                case "sent":
                    MailboxOptions.mailBox(out, sc, idUsr, "sent");
                    break;
                case "mycontacts":
                    searchOrMyContacts=0;
                    UserOptions.myContacts(idUsr, sc, out);
                    break;
                case "edit":
                    editProfile(idUsr, sc, out);
                    break;
                case "exit":
                    UserOptions userOptions = new UserOptions();
                    userOptions.changeLastseen(idUsr);
                    myMenu(sc, out);
                    break;
            }
        }
    }

    public static void newCorenspondationMenu(int idUsr, List<Integer> users, Scanner sc, PrintStream out) throws SQLException, SignUpException {
        while(true) {
            if(searchOrMyContacts==1) out.println("command(newSearch, choose, back)");
            else out.println("command(choose, back)");
            String command = sc.nextLine().toLowerCase();

            switch (command) {
                case "newsearch":
                    searchOrMyContacts=1;
                    SearchUsers.makeQuery(idUsr, sc, out);
                    break;
                case "choose":
                    int friendID = SearchUsers.chooseUser(users, sc, out);
                    sc.nextLine();
                    MailboxOptions.sentEmail(friendID, idUsr, sc, out, "", "");
                    break;
                case "back":
                    loginMenu(idUsr, sc, out);
                    break;
            }
        }
    }

    public static void mailboxMenu(int idUsr, PrintStream out, Scanner sc, List<Message> allMessages, String type) throws SQLException, SignUpException {
        while(true) {
            out.println("command(read, back)");
            String command = sc.nextLine().toLowerCase();

            switch (command) {
                case "read":
                    MailboxOptions.read(allMessages, idUsr, out, sc, type);
                    break;
                case "back":
                    loginMenu(idUsr, sc, out);
                    break;
            }
        }
    }

    public static void menuForOneMessage(int index, int idUsr, PrintStream out, Scanner sc,
                                         List<Message> allMessages, String filePath, String type) throws SQLException, SignUpException {
        while(true) {
            if(type.equals("inbox")) out.println("command(delete, reply, back)");
            else out.println("command(back)");
            String command = sc.nextLine().toLowerCase();
            switch (command) {
                case "delete":
                    allMessages.remove(index);
                    Chat.saveNewMessage(allMessages, filePath);
                    MailboxOptions.mailBox(out, sc, idUsr, type);
                    break;
                case "reply":
                    Message m = allMessages.get(index);
                    int friendID=m.getSender();
                    String topic = m.getTopic();
                    String reply = "reply to: "+m.getText()+"\n";
                    MailboxOptions.sentEmail(friendID, idUsr, sc, out, reply, topic);
                    break;
                case "back":
                    MailboxOptions.mailBox(out, sc, idUsr, type);
                    mailboxMenu(idUsr, out, sc, allMessages, type);
                    break;
            }
        }
    }

    public static void editProfile(int idUsr, Scanner sc, PrintStream out) throws SQLException, SignUpException {
        while(true) {
            out.println("command(edit, delete, back)");
            String command = sc.nextLine().toLowerCase();

            switch (command) {
                case "edit":
                    UserOptions options = new UserOptions();
                    out.println("What to edit(name, number, username, password) or want to back?:");
                    String property = sc.nextLine().toLowerCase();
                    if(options.editUser(idUsr, sc, out, property)){
                        out.println("successfully edited "+property+"!");
                    }else{
                        out.println("something wrong! Check property!");
                    }

                    break;
                case "delete":
                    if(UserOptions.deleteUser(idUsr, out)){
                        out.println("successfully delete your profile!");
                        Server.myMenu(sc, out);
                    }else{
                        out.println("can't delete your acount!");
                    }
                    break;
                case "back":
                    loginMenu(idUsr, sc, out);
                    break;
            }
        }
    }
}
