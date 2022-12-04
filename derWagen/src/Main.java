import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws SQLException, IndexOfVehicleException {
        String command;
        do{
            System.out.println("command (search, end): ");
            command=sc.next();
            command=command.toLowerCase();
            if(command.equals("search")){
                ReadVehicles.makeQuery();
            }
            else if(command.equals("end")){System.exit(0);}
        }while(true);
    }


}