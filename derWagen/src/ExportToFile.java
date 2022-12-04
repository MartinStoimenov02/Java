import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class ExportToFile{
    public static void exportToFile(Vehicles vehicle){
        Scanner sc = new Scanner(System.in);
        String filePath, text;
        System.out.println("input filepath and name: ");
        filePath=sc.nextLine();
        BufferedWriter buffWriter = null;
        FileWriter fileWriter = null;
        try {
            File file = new File(filePath);
            fileWriter = new FileWriter(file);
            buffWriter = new BufferedWriter(fileWriter);
            text=vehicle.toString();
            buffWriter.write(text);
            buffWriter.flush();
            System.out.println("Ready!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (buffWriter != null) {
                    buffWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
