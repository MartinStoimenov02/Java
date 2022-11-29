import java.io.*;

public class Main {
    static final String filePath = "person.bin";
    public static void main(String[] args) {
        Person person1 = new Person("name1", 25);
        Person person2=null;
        try(ObjectOutputStream out =new ObjectOutputStream(new FileOutputStream(filePath)))
        {
            out.writeObject(person1);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath)))
        {
            person2 = (Person) in.readObject();
        }
        catch (IOException | ClassNotFoundException e){
            System.out.println(e);
        }
        System.out.println(person2.toString());
    }
}