import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginMenu {
    String exit;
    String ads;
    String search;
    String edit;
    String exportToFile;
    String back;
    String unsave;

    public LoginMenu(String exit, String ads, String search, String edit) {
        this.exit = exit;
        this.ads = ads;
        this.search = search;
        this.edit = edit;
    }

    public LoginMenu(String exportToFile, String back, String unsave){
        this.exportToFile=exportToFile;
        this.back=back;
        this.unsave=unsave;
    }

    public void userMenu(int id) throws SQLException, SignUpException, IndexOfVehicleException {
        String command;
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("input command (");
            if(this.exit!=null){System.out.print(this.exit);}
            if(this.search!=null){System.out.print(", " + this.search);}
            if(this.ads!=null){System.out.print(", " + this.ads);}
            if(this.edit!=null){System.out.print(", " + this.edit);}
            System.out.println(")");

            command=scanner.next();
            command=command.toLowerCase();

            if(command.equals(this.exit)){
                Main.main(null);
            }
            else if (command.equals(this.search)){
                Menu.searchV=1;
                ReadVehicles.makeQuery(id);
            }
            else if (command.equals(this.ads)){
                Menu.searchV=0;
                adsMenu(id);
            }
        }
    }

    public static void adsMenu(int id) throws IndexOfVehicleException, SQLException, SignUpException {
        String command;
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("input command(exit, back, myAds, savedAds, addAd): ");
            command=scanner.next();
            command=command.toLowerCase();
            if(command.equals("exit")){
                Main.main(null);
            }
            else if (command.equals("back")){
                LoginMenu loginMenu = new LoginMenu("exit", "ads", "search", "edit");
                loginMenu.userMenu(id);
            }
            else if(command.equals("savedads")){
                Menu.searchV=0;
                UserAds userAds = new UserAds();
                userAds.savedAds(id);
            }
            else if(command.equals("myads")){
                Menu.searchV=-1;
                UserAds userAds = new UserAds();
                ReadVehicles.readFromDatabase("SELECT * FROM car WHERE idUsr="+id+";", id);
            }
            else if(command.equals("addad")){
                WriteVehicleInDatabase wr = new WriteVehicleInDatabase();
                wr.writeVehicle(id);
            }
        }
    }

    public void savedVehicleOptions(int id, int idOfVehicle, Vehicles arr) throws SQLException, IndexOfVehicleException, SignUpException {
        String command;
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("input command(back, exportToFile, unsave): ");
            command=scanner.next();
            command=command.toLowerCase();
            if(command.equals("back")){
                if(Menu.searchV==1){
                    ReadVehicles.makeQuery(id);
                }
                else{
                    UserAds userAds = new UserAds();
                    userAds.savedAds(id);
                }
            }
            else if (command.equals("exporttofile")){
                ExportToFile.exportToFile(arr);
            }
            else if(command.equals("unsave")){
                UserOptions userOptions = new UserOptions();
                userOptions.unsaveAdInProfile(id, idOfVehicle);
                UserAds userAds = new UserAds();
                userAds.savedAds(id);
            }
        }
    }
}
