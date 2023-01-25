import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    public static int searchV;
    String openVehicle;
    String newSearch;
    String backToAds;
    String query;
    Vehicles vehicle;
    String menu;
    String exportToFile;
    String saveInProfile;
    String edit;
    String delete;

    public Menu(String newSearch, String menu) {
        this.newSearch = newSearch;
        this.menu = menu;
    }

    public Menu(String openVehicle, String back, int pr) {
        this.openVehicle = openVehicle;
        this.menu = back;
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

    public Menu(String delete, String backToAds, String query, Vehicles vehicle, String edit, String exportToFile, int pr) {
        this.delete = delete;
        this.backToAds = backToAds;
        this.query = query;
        this.vehicle = vehicle;
        this.edit = edit;
        this.exportToFile = exportToFile;
    }

    public Menu(String newSearch, String backToAds, String query, Vehicles vehicle, String menu, String exportToFile, String saveInProfile) {
        this.newSearch = newSearch;
        this.backToAds = backToAds;
        this.query = query;
        this.vehicle = vehicle;
        this.menu = menu;
        this.exportToFile = exportToFile;
        this.saveInProfile = saveInProfile;
    }

    public void menuForReadVehicles(int id, int idOfVehicle) throws IndexOfVehicleException, SQLException, SignUpException {
        String command;
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("input command (");
            if(this.menu!=null){System.out.print(this.menu);}
            if(this.newSearch!=null){System.out.print(", " + this.newSearch);}
            if(this.openVehicle!=null){System.out.print(", " + this.openVehicle);}
            if(this.backToAds!=null){System.out.print(", " + this.backToAds);}
            if(this.exportToFile!=null){System.out.print(", " + this.exportToFile);}
            if(this.saveInProfile!=null){System.out.print(", " + this.saveInProfile);}
            if(this.edit!=null){System.out.print(", " + this.edit);}
            if(this.delete!=null){System.out.print(", " + this.delete);}
            System.out.println(")");

            command=scanner.next();
            command=command.toLowerCase();

            if (this.menu!=null && command.equals(this.menu.toLowerCase())){
                if(this.menu.toLowerCase().equals("menu")){
                    Main.main(null);
                }
                else{
                    if(Menu.searchV==-1){
                        LoginMenu loginMenu = new LoginMenu("exit", "ads", "search", "edit");
                        loginMenu.adsMenu(id);
                    }
                    else{
                        LoginMenu loginMenu = new LoginMenu("exit", "ads", "search", "edit");
                        loginMenu.userMenu(id);
                    }

                }

            }
            else if (this.newSearch!=null && command.equals(this.newSearch.toLowerCase())){
                searchV=1;
                ReadVehicles.makeQuery(id);
            }
            else if(this.openVehicle!=null && command.equals(this.openVehicle.toLowerCase())){
                return;
            }
            else if (this.backToAds!=null && command.equals(this.backToAds.toLowerCase())){
                ReadVehicles.readFromDatabase(this.query, id);
            }
            else if (this.exportToFile!=null && command.equals(this.exportToFile.toLowerCase())){
                if(this.vehicle!=null){
                    ExportToFile.exportToFile(this.vehicle);
                }
            }
            else if(this.saveInProfile!=null && command.equals(this.saveInProfile.toLowerCase())){
                UserOptions userOptions = new UserOptions();
                userOptions.saveAdInProfile(id, idOfVehicle);
            }
            else if(this.delete!=null && command.equals(this.delete.toLowerCase())){
                
            }
        }
    }
}