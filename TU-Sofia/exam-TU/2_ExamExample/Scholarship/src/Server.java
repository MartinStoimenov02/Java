import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    public static final String FILENAME="users.bin";
    private final Object usersLock;
    private ServerSocket server;
    Queue<Document> students;
    Queue<Document> access;
    Queue<Document> denied;
    public Server(){
        usersLock=new Object();
        students = new ArrayDeque<>();
        access = new ArrayDeque<>();
        denied = new ArrayDeque<>();

        initSecretary();
    }
    public void initSecretary()
    {
        if (new File(FILENAME).exists())
            return;
        List<User> users = new ArrayList<>();
        users.add(new Secretary("admin", "12345"));
        users.add(new Secretary("admin2", "12345"));
        users.add(new Student("student", "12345"));
        saveInFile(users);
    }

    public void start(){
        try{
            usersMenu(new Scanner(System.in), System.out);
            server=new ServerSocket(8080);
            while(true){
                Socket client = server.accept();
                Thread clientThread = new Thread(() -> {
                    Scanner sc=null;
                    PrintStream out = null;
                    try{
                        sc=new Scanner(client.getInputStream());
                        out=new PrintStream(client.getOutputStream());
                        usersMenu(sc, out);
                    }catch(IOException e){e.printStackTrace();}
                    finally{
                        if(sc!=null){sc.close();}
                        if(out!=null){out.close();}
                    }
                });
                clientThread.start();
            }
        }catch(IOException e){e.printStackTrace();}
    }

    public List<User> readFromFile(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME))){
            return (List<User>) in.readObject();
        }catch(IOException | ClassNotFoundException e){e.printStackTrace();}
        return null;
    }

    public void saveInFile(List<User> users){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME))){
            out.writeObject(users);
        }catch(IOException e){e.printStackTrace();}
    }

    public User login(String username, String password){
        synchronized(usersLock){
            for(User user : readFromFile()){
                if(Objects.equals(user.getUsername(), username) && Objects.equals(user.getPassword(), password)){
                    return user;
                }
            }
            return null;
        }
    }

    public void usersMenu(Scanner sc, PrintStream out){
        while(true){
            out.println("username:");
            String username = sc.nextLine();
            out.println("password:");
            String password = sc.nextLine();
            User user = login(username, password);

            if(user==null){
                out.println("error username or password!");
                continue;
            }

            switch(user.getUserType()){
                case STUDENT:
                    studentMenu(sc, out, (Student) user);
                    break;
                case SECRETARY:
                    secretaryMenu(sc, out, (Secretary)user);
                    break;
            }
        }

    }

    public void studentMenu(Scanner sc, PrintStream out, Student student){
        byte izbor;
        do{
            out.println("1. stipendiq uspeh\n2. Specialna stipendiq");
            izbor = sc.nextByte();
        }while(izbor!=1 && izbor!=2);
        Document document = null;
        sc.nextLine();
        out.print("name: ");
        String name = sc.nextLine();
        out.print("faculty: ");
        String faculty = sc.nextLine();
        out.print("uspeh: ");
        double uspeh = sc.nextDouble();
        if(izbor==1){
            document = new Document(name, faculty, uspeh);
        }
        else{
            out.print("dogod: ");
            double dohod = sc.nextDouble();
            document = new Document(name, faculty, uspeh, dohod);
        }
        students.add(document);
        sc.nextLine();
    }

    public void secretaryMenu(Scanner sc, PrintStream out, Secretary secretary){
        Document current = students.poll();
        if(current==null){
            out.println("Nqma chakashti zaqvki!");
            return;
        }
        out.println(current.toString());
        String ans;
        do{
            out.println("Studenta otgovarq na iziskvaniqta: Y/N");
            ans = sc.nextLine().toUpperCase();
        }while(!ans.equals("Y") && !ans.equals("N"));
        if(ans.equals("Y")){
            access.add(current);
        }
        else{
            denied.add(current);
        }
    }
}
