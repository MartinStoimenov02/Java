import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String filePath;
        System.out.println("input filepath and name: ");
        filePath=sc.nextLine();
        try {
            File file = new File(filePath);
            boolean isCreated = file.createNewFile();
            if (isCreated) {
                System.out.println("The file has been created");
            } else {
                System.out.println("File already exists ");
            }
        } catch (IOException e) {
            System.err.println("Exception Occurred:");
            e.printStackTrace();
        }

    }

}