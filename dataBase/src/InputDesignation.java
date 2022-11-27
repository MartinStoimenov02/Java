import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InputDesignation {
    Connection connection;

    public InputDesignation(Connection connection) {
        this.connection = connection;
    }

    public void inputDesign(String title) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO designation (title)\n" +
                "VALUES (\""+title+"\");");
        preparedStatement.executeUpdate();
    }
}
