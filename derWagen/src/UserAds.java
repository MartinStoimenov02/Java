import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserAds {
    Connection connection;
    public UserAds() throws SQLException {
        this.connection = ConnectToDatabase.getConnection();
    }

    public void savedAds(int id) throws SQLException, IndexOfVehicleException, SignUpException {
        String query = "select savedAds from usr where id="+id+";";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        String savedAdsBefore="";

        if (resultSet.next()){
            savedAdsBefore = resultSet.getString("savedAds");
        }
        String[] string={};
        int index = 0;
        Vehicles[] cars = {};
        if(savedAdsBefore!=null && savedAdsBefore!=""){
            string = savedAdsBefore.split(",");
            int[] arr = new int[string.length];

            // parsing the String argument as a signed decimal
            // integer object and storing that integer into the
            // array
            if(!string[0].equals("")){
                for (int i = 0; i < string.length; i++) {
                    arr[i] = Integer.valueOf(string[i]);
                }
                cars = new Vehicles[arr.length];

                for(int i : arr){
                    try{
                        query = "select * from car where id="+i+";";
                        preparedStatement = connection.prepareStatement(query);
                        resultSet = preparedStatement.executeQuery();
                        resultSet.next();
                        System.out.println((index + 1) + ") " + resultSet.getString("mark") + " " + resultSet.getString("model") + " - " +
                                resultSet.getInt("year") + " - " + resultSet.getInt("kilometres") + "km. - " + resultSet.getDouble("price") + "lv.");
                        Vehicles vehicle = new Vehicles(resultSet.getInt("id"), resultSet.getDate("date"),
                                resultSet.getString("mark"), resultSet.getString("model"), resultSet.getDouble("price"),
                                resultSet.getString("engine"), resultSet.getString("transmission"), resultSet.getString("state"),
                                resultSet.getInt("year"), resultSet.getInt("power"), resultSet.getInt("kilometres"),
                                resultSet.getString("colour"), resultSet.getString("coupeType"), resultSet.getInt("euroCategory"),
                                resultSet.getString("city"), resultSet.getString("description"), resultSet.getInt("idUsr"));
                        cars[index] = vehicle;
                        index++;
                    }catch(SQLException e){
                        System.out.println("this ad have been removed by the seller!");
                    }
                }

            }
        // declaring an array with the size of string
        }

        if (index == 0) {
            System.out.println("There isn't saved ads in your profile!");
            LoginMenu.adsMenu(id);
        }
        else{
            String command;
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("input command(back, openVehicle): ");
                command = scanner.next();
                command = command.toLowerCase();
                if(command.equals("back")){
                    LoginMenu.adsMenu(id);
                }
                else if(command.equals("openvehicle")){
                    ReadVehicles.readVehicle(cars, query, id, true);
                }
            }
        }
    }
}
