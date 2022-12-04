import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadVehicles {
    public static Scanner sc = new Scanner(System.in);

    public static void makeQuery() throws SQLException, IndexOfVehicleException {
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
        readFromDatabase(sqlSelectQuery);
    }

    public static void readFromDatabase(String query) throws SQLException, IndexOfVehicleException {
        ReadFromDatabase readFromDatabase = new ReadFromDatabase();
        int count = readFromDatabase.getCoutOfVehicles(query);
        ResultSet resultSet = readFromDatabase.getVehicles(query);
        Vehicles[] arr = new Vehicles[count];
        int index = 0;
        int numberOfVehicle = 0;
        while (resultSet.next()) {
            System.out.println((index + 1) + ") " + resultSet.getString("mark") + " " + resultSet.getString("model") + " - " +
                    resultSet.getInt("year") + " - " + resultSet.getInt("kilometres") + "km. - " + resultSet.getDouble("price") + "lv.");
            Vehicles vehicle = new Vehicles(resultSet.getInt("id"), resultSet.getDate("date"),
                    resultSet.getString("mark"), resultSet.getString("model"), resultSet.getDouble("price"),
                    resultSet.getString("engine"), resultSet.getString("transmission"), resultSet.getString("state"),
                    resultSet.getInt("year"), resultSet.getInt("power"), resultSet.getInt("kilometres"),
                    resultSet.getString("colour"), resultSet.getString("coupeType"), resultSet.getInt("euroCategory"),
                    resultSet.getString("city"), resultSet.getString("description"), resultSet.getInt("id_usr"));
            arr[index] = vehicle;
            index++;
        }

        if (index == 0) {
            System.out.println("There isn't vehicles with this param!");
            Menu simpleMenu = new Menu("newSearch", "menu");
            simpleMenu.menuForReadVehicles();
        }

        Menu menuWithAds = new Menu("openVehicle", "newSearch", "menu");
        menuWithAds.menuForReadVehicles();
        readVehicle(arr, query);
    }

    public static void readVehicle(Vehicles []arr, String query) throws IndexOfVehicleException, SQLException {
        int index, i;
        do{
            System.out.println("choose vehicle: ");
            index=sc.nextInt();
        }while(index>arr.length);

        for(i=0; i<arr.length; i++){
            if(i==index-1){
                System.out.println(arr[i].toString());
                break;
            }
        }

        Menu menuForAd = new Menu("newSearch", "backToAds", query, arr[i], "menu", "exportToFile");
        menuForAd.menuForReadVehicles();
    }
}