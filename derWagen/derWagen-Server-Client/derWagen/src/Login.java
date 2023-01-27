import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Login {
    static Connection connection;
    static User user;

    public Login() throws SQLException {
        connection = ConnectToDatabase.getConnection();
    }

    public void loginInfo(Scanner sc, PrintStream out) throws SQLException, SignUpException, IndexOfVehicleException {
        Scanner input = new Scanner(System.in);
        String usrName, pass;
        out.println("user name: ");
        usrName=input.next();
        out.println("password");
        pass=input.next();
        checkUser(usrName, pass, sc, out);
    }

    public void checkUser(String usrName, String pass, Scanner sc, PrintStream out) throws SQLException, SignUpException, IndexOfVehicleException {
        pass = String.valueOf(User.hashCode(pass));
        String query = "select * from usr where usrName=\"" + usrName + "\" and pass=\"" + pass + "\";";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int idUsr = resultSet.getInt("id");
            UserOptions userOptions = new UserOptions();
            user = userOptions.getUserFromId(idUsr, out);
            out.println("welcome "+user.getName()+"! You're logged-in as "+user.getUsrName());
            Menu.loginMenu(idUsr, user, sc, out);
        } else {
            String command;
            out.println("wrong username/password or non-existent user! Forgot password? (Y/N)");
            command=sc.nextLine();
            command=command.toLowerCase();
            if(command.equals("y")){
                UserOptions userOptions = new UserOptions();
                userOptions.forgotPassword(sc, out);
            }
        }
    }

    public static User getUser(){
        return user;
    }
}
