import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WriteUserInDatabase {
    static Connection connection;

    public WriteUserInDatabase() throws SQLException {
        this.connection = ConnectToDatabase.getConnection();
    }

    public void writeUser(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO usr (name, number, usrName, pass)VALUES (?, ?, ?, ?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getNumber());
        preparedStatement.setString(3, user.getUsrName());
        preparedStatement.setString(4, user.getPass());
        preparedStatement.executeUpdate();
    }
}