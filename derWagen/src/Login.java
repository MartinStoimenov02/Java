import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    static Connection connection;

    public Login() throws SQLException {
        this.connection = ConnectToDatabase.getConnection();
    }

    public void checkUser(String usrName, String pass) throws SQLException, SignUpException, IndexOfVehicleException {
        Scanner sc = new Scanner(System.in);
        pass = String.valueOf(User.hashCode(pass));
        String query = "select * from usr where usrName=\"" + usrName + "\" and pass=\"" + pass + "\";";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            System.out.println("welcome "+resultSet.getString("name")+"! You're logged-in as "+resultSet.getString("usrName"));
            LoginMenu loginMenu = new LoginMenu("exit", "ads", "search", "edit");
            loginMenu.userMenu(id);
        } else {
            String command;
            System.out.println("wrong username/password or non-existent user! Forgot password? (Y/N)");
            command=sc.next();
            command=command.toLowerCase();
            if(command.equals("y")){
                UserOptions userOptions = new UserOptions();
                userOptions.newPassword();
            }
        }
    }
}
