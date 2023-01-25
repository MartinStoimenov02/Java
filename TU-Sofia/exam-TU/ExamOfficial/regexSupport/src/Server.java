import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    public final static String FILENAME="regex.bin";
    private final Object usersLock;
    private ServerSocket server;
    public Server() throws IOException {
        usersLock=new Object();
        createFile();
    }

    public void createFile() throws IOException {
        File file = new File(FILENAME);
        if(!file.exists()){
            file.createNewFile();
            List<Regex> basicRegex = new ArrayList<>();
            basicRegex.add(new Regex("[0-9]+", "one or more number", 0));
            writeInFile(basicRegex);
        }
    }
    public void start() throws IOException {
        usersMenu(new Scanner(System.in), System.out);
        try {
            server = new ServerSocket(8080);
            while (true) {
                Socket client = server.accept();
                Thread clientThread = new Thread(() -> {
                    Scanner sc = null;
                    PrintStream out = null;
                    try {
                        sc = new Scanner(client.getInputStream());
                        out = new PrintStream(client.getOutputStream());
                        usersMenu(sc, out);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (sc != null) sc.close();
                        if (out != null) out.close();
                    }
                });
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Regex> readFromFile(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME))){
            return (List<Regex>) in.readObject();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public void writeInFile(List<Regex> regexs){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME))){
            out.writeObject(regexs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void usersMenu(Scanner sc, PrintStream out) throws IOException {
        while(true){
            out.println("command(createRegex|searchRegex)");
            String command = sc.nextLine().toLowerCase();
            switch (command){
                case "createregex":
                    createRegex(sc, out);
                    break;
                case "searchregex":
                    searchRegex(sc, out);
                    break;
            }
        }
    }

    public boolean isExist(List<Regex> savedRegex, Regex regex, PrintStream out){
        for(Regex r : savedRegex){
            if(regex.getPattern().equals(r.getPattern())){
                out.println("this regex already exist!");
                return true;
            }
        }
        return false;
    }
    public String[] testStrings(Scanner sc, PrintStream out){
        out.println("number of strings to test:");
        int n = sc.nextInt();
        sc.nextLine();
        String [] strings = new String[n];
        out.println("input "+n+" strings to test");
        for(int i=0; i<n; i++){
            strings[i]=sc.nextLine();
        }
        return strings;
    }

    public void createRegex(Scanner sc, PrintStream out) throws IOException {
        out.println("pattern:");
        String pattern = sc.nextLine();
        out.println("description:");
        String description = sc.nextLine().toLowerCase();
        Regex regex = new Regex(pattern, description);
        List<Regex> list = readFromFile();
        List<Regex> savedRegex = new ArrayList<>(list);
        boolean isExist = isExist(savedRegex, regex, out);
        if(isExist){return;}
        String []strings = testStrings(sc, out);
        for(int i=0; i<strings.length; i++){
            out.println(strings[i]);
        }
        List<Boolean> testers = RegexTester.test(regex, strings);
        out.println(testers);
        out.println("do you want to add this regex? Y/N");
        String yesNo = sc.nextLine().toUpperCase();
        if(yesNo.equals("Y")) {
            synchronized (usersLock) {
                savedRegex.add(regex);
                writeInFile(savedRegex);
                out.println("your regex has been added successfully!");
            }
        }
    }

    public Regex chooseRegex(List<Regex> savedRegex, int id){
        for(Regex r : savedRegex){
            if(r.getId()==id){
                return r;
            }
        }
        return null;
    }

    public void searchRegex(Scanner sc, PrintStream out){
        out.println("keyword:");
        String keyword = sc.nextLine().toLowerCase();
        List<Regex> savedRegex = readFromFile().stream().sorted(Comparator.comparingInt(Regex::getRating).reversed())
                .filter(regex -> regex.getDescription().contains(keyword)).toList();
        out.println(savedRegex);
        out.println("choose by id:");
        int id=sc.nextInt();
        Regex currentRegex = chooseRegex(savedRegex, id);
        if(currentRegex==null){
            out.println("there isnt regex with this id!");
            return;
        }
        String []strings = testStrings(sc, out);
        List<Boolean> testers = RegexTester.test(currentRegex, strings);
        out.println(testers);
        synchronized (usersLock){
            out.println("do you want to rateUp/rateDown this regex?");
            String yesNo = sc.nextLine().toLowerCase();
            int rate = changeRating(yesNo, currentRegex);
            if(rate!=0){
                int index = savedRegex.indexOf(currentRegex);
                savedRegex.get(index).setRating(rate);
                writeInFile(savedRegex);
                out.println("successfully!");
            }

        }
    }

    public int changeRating(String yesNo, Regex regex){
        if(yesNo.equals("rateup")){
            return regex.getRating()+1;
        }
        else if(yesNo.equals("ratedown")){
            return regex.getRating()-1;
        }
        return 0;
    }
}
