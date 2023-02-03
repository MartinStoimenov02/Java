import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chat {
    public static String createNewChat(int userID, int friendID){
        String filePath="messagesFiles\\msg"+userID+"-"+friendID+".bin";
        try {
            File file = new File(filePath);
            file.createNewFile();
            return filePath;
        }catch (IOException e) {
            System.err.println("Exception Occurred:");
        }
        return null;
    }

    public static List<Message> readChat(String filePath, PrintStream out){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Message>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
        }
        return null;
    }

    public static Message writeMessage(int sender, int receiver, Scanner sc, PrintStream out, String reply, String topic){
        if(reply.equals("")){
            out.println("Topic:");
            topic=sc.nextLine();
        }else{
            out.println("Topic: "+topic);
        }

        out.println("text:");
        String text=sc.nextLine();
        return new Message(sender, receiver, topic, reply+text);
    }

    public static void saveNewMessage(List<Message> messages, String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(messages);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<String> listFilesUsingJavaIO() {
        return Stream.of(new File("messagesFiles").listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

}
