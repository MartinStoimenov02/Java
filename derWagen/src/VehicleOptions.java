import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class VehicleOptions {
    public static void makeQuery(int idUsr, Scanner sc, PrintStream out) throws SQLException, IndexOfVehicleException, SignUpException {
        ArrayList<String> searchParams = readParamsFromConsole.inputParams(sc, out);
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
        readVehiclesFromDatabase(sqlSelectQuery, idUsr, sc, out);
    }

    public static void readVehiclesFromDatabase(String query, int idUsr, Scanner sc, PrintStream out) throws SQLException, IndexOfVehicleException, SignUpException {
        ReadFromDatabase readFromDatabase = new ReadFromDatabase();
        int count = readFromDatabase.getCountOfVehicles(query, out);
        ResultSet resultSet = readFromDatabase.getUsersOrVehicle(query, out);
        int index = 0;
        Vehicle[] arr = new Vehicle[count];
        if(resultSet!=null && count!=0){
            while (resultSet.next()) {
                out.println((index + 1) + ") " + resultSet.getString("mark") + " " + resultSet.getString("model") + " - " +
                        resultSet.getInt("year") + " - " + resultSet.getInt("kilometres") + "km. - " + resultSet.getDouble("price") + "lv.");
                Vehicle vehicle = new Vehicle(resultSet.getInt("id"), resultSet.getDate("date"),
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
            Menu.menuForNoVehicles(idUsr, sc, out);
        }
        else {
            Menu.menuForAllVehiclesListed(idUsr, query, arr, sc, out);
        }
    }

    public static void readVehicle(Vehicle []arr, String query, int idUsr, Scanner sc, PrintStream out) throws IndexOfVehicleException, SQLException, SignUpException {
        int index;
        do {
            out.println("choose vehicle: ");
            index = sc.nextInt();
            sc.nextLine();
        } while (index > arr.length || index<=0);

        if(arr[index-1]==null){
            readVehicle(arr, query, idUsr, sc, out);
        }
        printVehicle(arr[index-1], idUsr, out);
        Menu.menuForOneVehicle(idUsr, query, arr[index-1], sc, out);

    }

    public static void printVehicle(Vehicle vehicle, int idUsr, PrintStream out) throws SQLException, SignUpException {
        UserOptions userOptions = new UserOptions();
        out.println(vehicle.toString()+"\n"+userOptions.getUserFromId(vehicle.getIdUsr(), out).toString());
    }
}
