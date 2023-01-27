import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AdOptions {
    static Connection connection;
    public AdOptions() throws SQLException {
        connection = ConnectToDatabase.getConnection();
    }
    public boolean saveAdInProfile(int idUsr, int idOfVehicle) throws SQLException {
        List<Integer> savedAds = Login.user.getSavedAds();
        if(savedAds.contains(idOfVehicle)){
            return false;
        }
        else {
            savedAds.add(idOfVehicle);
            String stringSavedAds = savedAds.stream().map(String::valueOf).collect(Collectors.joining(","));
            String query="Update usr set savedAds = \""+ stringSavedAds +"\" where id="+idUsr+";";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int correct = preparedStatement.executeUpdate();
            if(correct!=0){
                return true;
            }
        }
        return false;
    }

    public boolean unsaveAdInProfile(int idUsr, int idOfVehicle, User user) throws SQLException {
        List<Integer> savedAds = user.getSavedAds();
        if(!savedAds.contains(idOfVehicle)){
            return false;
        }
        else {
            savedAds.remove(Integer.valueOf(idOfVehicle));
            String stringSavedAds = savedAds.stream().map(String::valueOf).collect(Collectors.joining(","));
            String query="Update usr set savedAds = \""+ stringSavedAds +"\" where id="+idUsr+";";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int correct = preparedStatement.executeUpdate();
            if(correct!=0){
                return true;
            }
        }
        return false;
    }

    public static void myAds(int idUsr, Scanner sc, PrintStream out) throws IndexOfVehicleException, SQLException, SignUpException {
        String query = "SELECT * FROM car WHERE idUsr="+idUsr+";";
        VehicleOptions.readVehiclesFromDatabase(query, idUsr, sc, out);
    }

    public static boolean savedAds(int idUsr, Scanner sc, PrintStream out) throws IndexOfVehicleException, SQLException, SignUpException {
        UserOptions userOptions = new UserOptions();
        User user = userOptions.getUserFromId(idUsr, out);
        List<Integer> savedAdsNum = user.getSavedAds();
        if(savedAdsNum.size()==0){
            return false;
        }
        String query = "select * from car where ";
        for(int i : savedAdsNum){
            query=query+"id="+i + " or ";
        }
        query=query.substring(0, query.length() - 3);
        VehicleOptions.readVehiclesFromDatabase(query, idUsr, sc, out);
        return true;
    }

    public boolean deleteAd(int vehicleId, Scanner sc, PrintStream out) throws SQLException, SignUpException, IndexOfVehicleException {
        ReadFromDatabase readFromDatabase = new ReadFromDatabase();
        String query = "SELECT * FROM usr;";
        ResultSet resultSet = readFromDatabase.getUsersOrVehicle(query, out);
        if(resultSet!=null){
            while (resultSet.next()) {
                int userID = resultSet.getInt("id");
                UserOptions userOptions = new UserOptions();
                User user = userOptions.getUserFromId(userID, out);
                String savedAdsString = resultSet.getString("savedAds");
                if(!savedAdsString.equals("")){
                    List<Integer> savedAds = Arrays.stream(savedAdsString.split(",")).map(Integer::parseInt).collect(Collectors.toList());
                    user.setSavedAds(savedAds);
                }
                unsaveAdInProfile(userID, vehicleId, user);
                savedAds(userID, sc, out);
            }
        }
        query = "DELETE FROM car WHERE id = "+vehicleId;
        if(!readFromDatabase.deleteOrEdit(query, out)){
            return false;
        }
        return true;
    }

    public boolean editAd(int vehicleId, Scanner sc, PrintStream out, String property, String propertyValue) throws SQLException {
        ReadFromDatabase readFromDatabase = new ReadFromDatabase();
        String query = "Update car set "+property+" = \"" + propertyValue + "\" where id="+vehicleId+";";
        if(!readFromDatabase.deleteOrEdit(query, out)){
            return false;
        }
        return true;
    }

    public static Vehicle addAd(int idUsr,Scanner sc, PrintStream out) throws SQLException {
        Vehicle vehicle = readParamsFromConsole.inputVehicleOptions(idUsr,sc, out);
        AddInDatabase add = new AddInDatabase();
        if(add.AddVehicleInDataBase(vehicle)){return vehicle;}
        return null;
    }
}
