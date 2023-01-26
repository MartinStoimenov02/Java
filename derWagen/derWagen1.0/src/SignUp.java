import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SignUp {
    Connection connection;
    public SignUp() throws SQLException {
        this.connection = ConnectToDatabase.getConnection();
    }
    public void signUp(Scanner sc, PrintStream out){
        String name, number, usrName, pass;
        out.println("name: ");
        name=sc.nextLine();
        out.println("phone number: ");
        number=sc.nextLine();
        out.println("user name(it must contains only small letters and numbers, length: minimum 10 symbols): ");
        usrName=sc.nextLine();
        out.println("password(It must contains at least one small letter, one big letter, and one number, length: minimum 8 symbols): ");
        pass=sc.nextLine();
        try {
            User user = new User(name, number, usrName, pass, out);
            writeUser(user);
            out.println("Your new profile was created successfully");
        } catch (SignUpException | SQLException e) {
            if(e.toString().equals("java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '"+number+"' for key 'usr.number'")){
                out.println("User with this phone number is already exist!");
            }
            else if(e.toString().equals("java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '"+usrName+"' for key 'usr.usrName'")){
                out.println("User with this user name is already exist!");
            }
            else{
                out.println(e);
            }
        }
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
