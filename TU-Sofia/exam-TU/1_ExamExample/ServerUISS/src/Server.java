import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

public class Server {
    public static final String FILENAME="users.bin";
    private ServerSocket server;
    private final Object usersLock;

    public Server(){
        usersLock = new Object();
        initAdmins();
    }

    public void initAdmins()
    {
        if (new File(FILENAME).exists())
            return;
        List<User> users = new ArrayList<>();
        users.add(new Admin("admin", "12345"));
        writeInFile(users);
    }
    public void start(){
        try{
            usersMenu(new Scanner(System.in), System.out); //for local test
            server=new ServerSocket(8080);
            while(true){

                Socket client = server.accept();
                Thread clientThread = new Thread (() -> {
                    Scanner sc = null;
                    PrintStream out = null;
                    try{
                        sc=new Scanner(client.getInputStream());
                        out=new PrintStream(client.getOutputStream());
                        //usersMenu(sc, out);
                    }catch(IOException e){e.printStackTrace();}
                    finally{
                        if(sc!=null) sc.close();
                        if(out!=null) out.close();
                    }
                });
                clientThread.start();
            }
        }catch(IOException e) {e.printStackTrace();}
    }

    public List<User> readFromFile(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME))){
            return (List<User>) in.readObject();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public void writeInFile(List<User> users){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME))){
            out.writeObject(users);
        }catch(IOException e){e.printStackTrace();}
    }

    public User login(String userName, String password){
        synchronized(usersLock){
            for(User user : readFromFile()){
                user.toString();
                if(Objects.equals(user.getUsername(), userName) && Objects.equals(user.getPassword(), password)){
                    return user;
                }
            }
            return null;
        }
    }

    public void usersMenu(Scanner sc, PrintStream out){
        while(true){
            out.println("username:");
            String userName = sc.nextLine();
            out.println("password:");
            String password = sc.nextLine();
            User user = login(userName, password);
            if(user==null){
                out.println("Error username or password!");
                continue;
            }

            switch(user.getUserType()){
                case ADMIN:
                    adminMenu(sc, out, (Admin)user);
                    break;
                case TEACHER:
                    teacherMenu(sc, out, (Teacher)user);
                    break;
                case STUDENT:
                    studentMenu(sc, out, (Student)user);
                    break;
            }
        }
    }

    public User check(String username, String password, UserType userType){
        switch(userType){
            case ADMIN:
                Admin admin = new Admin(username, password);
                return admin;
            case TEACHER:
                if(username.matches("[a-z]+@tu-sofia.bg") && password.length()>5){
                    Teacher teacher = new Teacher(username, password);
                    return teacher;
                }
            case STUDENT:
                if(username.matches("[0-9]{9}") && password.matches("[0-9]{10}")){
                    Student student = new Student(username, password);
                    return student;
                }
        }
        return null;
    }
    public void adminMenu(Scanner sc, PrintStream out, Admin admin){
        out.println("logged as admin!");
        out.println("what to add: ADMIN|STUDENT|TEACHER");
        UserType userType = UserType.valueOf(sc.nextLine().toUpperCase());
        out.println("username to add:");
        String username = sc.nextLine();
        out.println(("password to add:"));
        String password = sc.nextLine();
        User user = check(username, password, userType);
        if(user==null){
            out.println("add user denied!");
            return;
        }
        synchronized (usersLock){
            List<User> users = readFromFile();
            users.add(user);
            writeInFile(users);
            out.println("successfully added!");
        }
    }

    public void teacherMenu(Scanner sc, PrintStream out, Teacher teacher){
        out.println("logged as teacher!");
        out.println("faculty number of student:");
        String facultyNumber = sc.nextLine();
        out.println("subject:");
        String subject = sc.nextLine();
        out.println("semester:");
        int semester = sc.nextInt();
        out.println("grade:");
        double grade = sc.nextDouble();
        Grade studentGrade = new Grade(subject, semester, grade);
        synchronized(usersLock) {
            List<User> users = readFromFile();
            for (User user : users) {
                if (Objects.equals(user.getUsername(), facultyNumber) && user instanceof Student) {
                    ((Student) user).getGrades().add(studentGrade);
                    writeInFile(users);
                    out.println("Success!");
                    return;
                }
            }
            out.println("can't add this grade!");
        }
    }
    public void studentMenu(Scanner sc, PrintStream out, Student student){
        out.println("logged as student!");
        List<Grade> grades = student.getGrades().stream().sorted(
                Comparator.comparingInt(Grade::getSemester).thenComparing(Grade::getSubject)).
                filter(grade -> grade.getSubject().startsWith("m")).collect(Collectors.toList());
//        List<Grade> grades = student.getGrades().stream().
//                filter(grade -> grade.getSubject().startsWith("m")).collect(Collectors.toList());
//        List<Grade> grades = student.getGrades().stream().sorted(
//                        Comparator.comparingInt(Grade::getSemester).thenComparing(Grade::getSubject)).collect(Collectors.toList());
        out.println(grades);
    }
}
