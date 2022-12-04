import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadFromDatabase {
    Connection connection;

    public ReadFromDatabase() throws SQLException {
        this.connection = ConnectToDatabase.getConnection();
    }

    public int getCoutOfVehicles(String query) throws SQLException {
        query="SELECT COUNT(*) "+query.substring(9, query.length());
        PreparedStatement preparedStatement1 = ConnectToDatabase.getConnection().prepareStatement(query);
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        resultSet1.next();
        int count = resultSet1.getInt(1);
        return count;
    }
    public ResultSet getVehicles(String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
}
