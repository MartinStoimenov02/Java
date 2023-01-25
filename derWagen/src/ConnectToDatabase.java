import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDatabase {
        public static Connection getConnection() throws SQLException {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars",
                    "root", "1234567");
            return connection;
        }
    }