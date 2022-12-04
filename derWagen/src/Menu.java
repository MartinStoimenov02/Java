import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    String openVehicle;
    String newSearch;
    String backToAds;
    String query;
    Vehicles vehicle;
    String menu;
    String exportToFile;

    public Menu(String newSearch, String menu) {
        this.newSearch = newSearch;
        this.menu = menu;
    }

    public Menu(String openVehicle, String newSearch, String menu) {
        this.openVehicle = openVehicle;
        this.newSearch = newSearch;
        this.menu = menu;
    }

    public Menu(String newSearch, String backToAds, String query, Vehicles vehicle, String menu, String exportToFile) {
        this.newSearch = newSearch;
        this.backToAds = backToAds;
        this.query = query;
        this.vehicle = vehicle;
        this.menu = menu;
        this.exportToFile = exportToFile;
    }

    public void menuForReadVehicles() throws IndexOfVehicleException, SQLException {
        String command;
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("input command (");
            if(this.menu!=null){System.out.print(this.menu);}
            if(this.newSearch!=null){System.out.print(", " + this.newSearch);}
            if(this.openVehicle!=null){System.out.print(", " + this.openVehicle);}
            if(this.backToAds!=null){System.out.print(", " + this.backToAds);}
            if(this.exportToFile!=null){System.out.print(", " + this.exportToFile);}
            System.out.println(")");

            command=scanner.next();
            command=command.toLowerCase();

            if (command.equals(this.menu.toLowerCase())){
                Main.main(null);
            }
            else if (command.equals(this.newSearch.toLowerCase())){
                ReadVehicles.makeQuery();
            }
            else if(this.openVehicle!=null && command.equals(this.openVehicle.toLowerCase())){
                return;
            }
            else if (this.backToAds!=null && command.equals(this.backToAds.toLowerCase())){
                ReadVehicles.readFromDatabase(this.query);
            }
            else if (this.exportToFile!=null && command.equals(this.exportToFile.toLowerCase())){
                if(this.vehicle!=null){
                    ExportToFile.exportToFile(this.vehicle);
                }
            }
        }
    }
}