import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddInDatabase {
    Connection connection;

    public AddInDatabase() throws SQLException {
        this.connection = ConnectToDatabase.getConnection();
    }

    public boolean AddVehicleInDataBase(Vehicle vehicle) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO car (date, mark, model, price," +
                "engine, transmission, state, year, power, kilometres, colour, coupeType, euroCategory, city, description, idUsr" +
                ")VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setDate(1, (Date) vehicle.getDate());
        preparedStatement.setString(2, vehicle.getMark());
        preparedStatement.setString(3, vehicle.getModel());
        preparedStatement.setDouble(4, vehicle.getPrice());
        preparedStatement.setString(5, vehicle.getEngine());
        preparedStatement.setString(6, vehicle.getTransmission());
        preparedStatement.setString(7, vehicle.getState());
        preparedStatement.setInt(8, vehicle.getYearOfManufacture());
        preparedStatement.setInt(9, vehicle.getPower());
        preparedStatement.setInt(10, vehicle.getKilometres());
        preparedStatement.setString(11, vehicle.getColour());
        preparedStatement.setString(12, vehicle.getCoupeType());
        preparedStatement.setInt(13, vehicle.getEuroCategory());
        preparedStatement.setString(14, vehicle.getCity());
        preparedStatement.setString(15, vehicle.getDescription());
        preparedStatement.setInt(16, vehicle.getIdUsr());
        preparedStatement.executeUpdate();
        return true;
    }
}
