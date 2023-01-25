import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static void main(String[] args) throws Exception {
        //Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "1234567";

        Connection con = DriverManager.getConnection(url, username, password);
        if (con != null) {
            System.out.println("Database Connected successfully");
        } else {
            System.out.println("Database Connection failed");
        }
    }
}