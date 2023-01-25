import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ExportToFile{
    static BufferedWriter buffWriter = null;
    static FileWriter fileWriter = null;
    static String text;
    static String filePath;
    public static void exportToFile(Vehicles vehicle){
        Scanner sc = new Scanner(System.in);
        String command;
        boolean isCreated;
        inputPath(vehicle);

        try {
            while(true){
                File file = new File(filePath);
                isCreated = file.createNewFile();
                if (isCreated) {
                    System.out.println("The file has been created");
                    writeInFile(file, vehicle, false);
                    break;
                } else {
                    System.out.println("File already exists! Rename file, rewrite in the same file or add at the end of the same file?" +
                            "(rename/rewrite/add)?");
                    command=sc.next();
                    if(command.equals("rename")){
                        inputPath(vehicle);
                    }
                    else if(command.equals("rewrite")){
                        writeInFile(file, vehicle, false);
                        break;
                    }
                    else if(command.equals("add")){
                        writeInFile(file, vehicle, true);
                        break;
                    }
                }
            }
        }catch (IOException e) {
            System.err.println("Exception Occurred:");
            e.printStackTrace();
        }
    }

    public static void inputPath(Vehicles vehicle){
        Scanner sc = new Scanner(System.in);
        System.out.println("input filepath and name: ");
        filePath=sc.nextLine();
        if(filePath.equals("")){
            filePath="C:\\Users\\marti\\Downloads\\"+vehicle.getMark()+".txt";
        }
        else{
            filePath=filePath+".txt";
        }
    }

    public static void writeInFile(File file, Vehicles vehicle, boolean writeOrAppend){
        try{
            fileWriter = new FileWriter(file, writeOrAppend);
            buffWriter = new BufferedWriter(fileWriter);
            text=vehicle.toString()+"\n";
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
