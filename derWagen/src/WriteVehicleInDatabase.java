import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WriteVehicleInDatabase {
    static Connection connection;

    public WriteVehicleInDatabase() throws SQLException {
        this.connection = ConnectToDatabase.getConnection();
    }

    public void writeVehicle(int id) throws SQLException {
        Vehicles vehicles = readParamsFromConsole.inputVehicleOptions(id);
        System.out.println(vehicles.toString());
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO car (date, mark, model, price," +
                "engine, transmission, state, year, power, kilometres, colour, coupeType, euroCategory, city, description, idUsr" +
                ")VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setDate(1, (Date) vehicles.getDate());
        preparedStatement.setString(2, vehicles.getMark());
        preparedStatement.setString(3, vehicles.getModel());
        preparedStatement.setDouble(4, vehicles.getPrice());
        preparedStatement.setString(5, vehicles.getEngine());
        preparedStatement.setString(6, vehicles.getTransmission());
        preparedStatement.setString(7, vehicles.getState());
        preparedStatement.setInt(8, vehicles.getYearOfManufacture());
        preparedStatement.setInt(9, vehicles.getPower());
        preparedStatement.setInt(10, vehicles.getKilometres());
        preparedStatement.setString(11, vehicles.getColour());
        preparedStatement.setString(12, vehicles.getCoupeType());
        preparedStatement.setInt(13, vehicles.getEuroCategory());
        preparedStatement.setString(14, vehicles.getCity());
        preparedStatement.setString(15, vehicles.getDescription());
        preparedStatement.setInt(16, vehicles.getIdUsr());
        preparedStatement.executeUpdate();
        System.out.println("You added vehicle successfully!");
    }
}
