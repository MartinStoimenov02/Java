import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadVehicles {
    public static Scanner sc = new Scanner(System.in);

    public static void makeQuery(int id) throws SQLException, IndexOfVehicleException, SignUpException {
        ArrayList<String> searchParams = readParamsFromConsole.inputParams();
        String sqlSelectQuery = "select * from car where";
        int i;

        for(i=0; i<searchParams.size()-4; i+=2) {
            if (!searchParams.get(i + 1).equals("") && !searchParams.get(i).equals("order")) {
                if (searchParams.get(i).substring(searchParams.get(i).length() - 4, searchParams.get(i).length()).equals("From")) {
                    String fromVar;
                    fromVar = searchParams.get(i).substring(0, searchParams.get(i).length() - 4);
                    sqlSelectQuery = sqlSelectQuery + " " + fromVar + " >= \"" + searchParams.get(i+1) + "\""+" and";
                }
                else if (searchParams.get(i).substring(searchParams.get(i).length() - 2, searchParams.get(i).length()).equals("To")) {
                    String toVar;
                    toVar = searchParams.get(i).substring(0, searchParams.get(i).length() - 2);
                    sqlSelectQuery = sqlSelectQuery + " " + toVar + " <= \"" + searchParams.get(i+1) + "\""+" and";
                }
                else{
                    sqlSelectQuery = sqlSelectQuery + " " + searchParams.get(i) + " = \"" + searchParams.get(i + 1) + "\"" + " and";
                }
            }
        }

        if(sqlSelectQuery=="select * from car where")
        {
            sqlSelectQuery="select * from car";
        }
        else{
            sqlSelectQuery=sqlSelectQuery.substring(0, sqlSelectQuery.length() - 3);
        }
        i+=2;
        if(searchParams.get(i).equals("order") && !searchParams.get(i+1).equals("")){
            sqlSelectQuery = sqlSelectQuery + " " + "order by " + searchParams.get(i+1);
        }
        readFromDatabase(sqlSelectQuery, id);
    }

    public static void readFromDatabase(String query, int id) throws SQLException, IndexOfVehicleException, SignUpException {
        ReadFromDatabase readFromDatabase = new ReadFromDatabase();
        int count = readFromDatabase.getCountOfVehicles(query);
        ResultSet resultSet = readFromDatabase.getVehicles(query);
        int index = 0;
        Vehicles[] arr = new Vehicles[count];
        if(resultSet!=null && count!=0){
            while (resultSet.next()) {
                System.out.println((index + 1) + ") " + resultSet.getString("mark") + " " + resultSet.getString("model") + " - " +
                        resultSet.getInt("year") + " - " + resultSet.getInt("kilometres") + "km. - " + resultSet.getDouble("price") + "lv.");
                Vehicles vehicle = new Vehicles(resultSet.getInt("id"), resultSet.getDate("date"),
                        resultSet.getString("mark"), resultSet.getString("model"), resultSet.getDouble("price"),
                        resultSet.getString("engine"), resultSet.getString("transmission"), resultSet.getString("state"),
                        resultSet.getInt("year"), resultSet.getInt("power"), resultSet.getInt("kilometres"),
                        resultSet.getString("colour"), resultSet.getString("coupeType"), resultSet.getInt("euroCategory"),
                        resultSet.getString("city"), resultSet.getString("description"), resultSet.getInt("idUsr"));
                arr[index] = vehicle;
                index++;
            }
        }

        if (index == 0) {
            System.out.println("There isn't vehicles with this param!");
            if(id==0){
                Menu simpleMenu = new Menu("newSearch", "menu");
                simpleMenu.menuForReadVehicles(id, 0);
            }else{
                Menu simpleMenu = new Menu("newSearch", "back");
                simpleMenu.menuForReadVehicles(id, 0);
            }

        }
        else{
            if(id==0){
                Menu menuWithAds = new Menu("openVehicle", "newSearch", "menu");
                menuWithAds.menuForReadVehicles(id, 0);
                readVehicle(arr, query, id, false);
            }else{
                if(Menu.searchV==0){
                    Menu menuWithAds = new Menu("openVehicle", "newSearch", "back");
                    menuWithAds.menuForReadVehicles(id, 0);
                    readVehicle(arr, query, id, true);
                }
                else if(Menu.searchV==-1){
                    Menu menuWithAds = new Menu("openVehicle", "back", 0);
                    menuWithAds.menuForReadVehicles(id, 0);
                    readVehicle(arr, query, id, true);
                }

            }
        }

    }

    public static void readVehicle(Vehicles []arr, String query, int id, boolean logged) throws IndexOfVehicleException, SQLException, SignUpException {
        int index, i, idOfVehicle = 0;
        do {
            System.out.println("choose vehicle: ");
            index = sc.nextInt();
        } while (index > arr.length);

        for (i = 0; i < arr.length; i++) {
            if (i == index - 1) {
                if(arr[i]==null){
                    readVehicle(arr, query, id, logged);
                }
                System.out.println(arr[i].toString());
                idOfVehicle = arr[i].getId();
                break;
            }
        }
        if(logged==false){
            checkMenu(id, idOfVehicle, query, arr[i]);
        }
        else{
            if(Menu.searchV==0){
                LoginMenu loginMenu = new LoginMenu("exportToFile", "back", "unsave");
                loginMenu.savedVehicleOptions(id, idOfVehicle, arr[i]);
            }else{
                checkMenu(id, idOfVehicle, query, arr[i]);
            }
        }
    }

    public static void checkMenu(int id, int idOfVehicle, String query, Vehicles arr) throws IndexOfVehicleException, SQLException, SignUpException {
        if(id==0){
            Menu menuForAd = new Menu("newSearch", "backToAds", query, arr, "menu", "exportToFile");
            menuForAd.menuForReadVehicles(id, idOfVehicle);
        }
        else if(Menu.searchV==-1){
            Menu menuForAd = new Menu("delete", "backToAds", query, arr, "edit", "exportToFile", 0);
            menuForAd.menuForReadVehicles(id, idOfVehicle);
        }
        else{
            Menu menuForAd = new Menu("newSearch", "backToAds", query, arr, "exit", "exportToFile", "saveInProfile");
            menuForAd.menuForReadVehicles(id, idOfVehicle);
        }
    }

}