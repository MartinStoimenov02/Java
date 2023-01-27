import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ExportToFile{
    static BufferedWriter buffWriter = null;
    static FileWriter fileWriter = null;
    static String text;
    static String filePath;
    public static void exportToFile(Vehicle vehicle, User user, Scanner sc, PrintStream out){
        String command;
        boolean isCreated;
        inputPath(vehicle, sc, out);

        try {
            while(true){
                File file = new File(filePath);
                isCreated = file.createNewFile();
                if (isCreated) {
                    out.println("The file has been created!");
                    writeInFile(file, vehicle, user, false, out);
                    break;
                } else {
                    out.println("File already exists! Rename file, rewrite in the same file or add at the end of the same file?" +
                            "(rename/rewrite/add)?");
                    command=sc.nextLine();
                    if(command.equals("rename")){
                        inputPath(vehicle, sc, out);
                    }
                    else if(command.equals("rewrite")){
                        writeInFile(file, vehicle, user, false, out);
                        break;
                    }
                    else if(command.equals("add")){
                        writeInFile(file, vehicle, user, true, out);
                        break;
                    }
                }
            }
        }catch (IOException e) {
            System.err.println("Exception Occurred:");
            e.printStackTrace();
        }
    }

    public static void inputPath(Vehicle vehicle, Scanner sc, PrintStream out){
        out.println("input filepath and name (default: Downloads): ");
        try{
            filePath=sc.nextLine();
        }catch(NoSuchElementException e){return;}

        if(filePath.equals("")){
            filePath="C:\\Users\\marti\\Downloads\\"+vehicle.getMark()+".txt";
        }
        else{
            filePath=filePath+".txt";
        }
    }

    public static void writeInFile(File file, Vehicle vehicle, User user, boolean writeOrAppend, PrintStream out){
        try{
            fileWriter = new FileWriter(file, writeOrAppend);
            buffWriter = new BufferedWriter(fileWriter);
            text=vehicle.toString()+"\n"+user.toString();
            buffWriter.write(text);
            buffWriter.flush();
            out.println("Ready!");
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
