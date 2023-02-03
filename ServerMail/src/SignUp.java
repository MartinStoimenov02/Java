import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
            String query="Select * from usr where usrName=\""+usrName+"\";";
            ReadFromDatabase readFromDatabase = new ReadFromDatabase();
            ResultSet resultSet = readFromDatabase.getUsers(query, out);
            //List<Integer> users = new ArrayList<>();
            int idUsr=0;
            if(resultSet!=null){
                while (resultSet.next()) {
                    idUsr = resultSet.getInt("id");
                }
            }
            if(idUsr!=0){
                String filePath = Chat.createNewChat(1, idUsr);
                List <Message> messages = new ArrayList<>();
                if(Chat.readChat(filePath, out)!=null){
                    messages=Chat.readChat(filePath, out);
                }
                Message message = new Message(1, idUsr, "welcome", "welcome to mail! Enjoy it!");
                messages.add(message);
                Chat.saveNewMessage(messages, filePath);
            }

            out.println("Your new profile was created successfully");
        } catch (SignUpException | SQLException e) {
            if(e.toString().equals("java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '"+number+"' for key 'usr.number'")){
                out.println("User with this phone number is already exist!");
            }
            else if(e.toString().equals("java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '"+usrName+"' for key 'usr.usrName'")){
                out.println("User with this user name is already exist!");
            }
        }
    }

    public void writeUser(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO usr (name, number, usrName, pass, friends, lastseen)VALUES (?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getNumber());
        preparedStatement.setString(3, user.getUsrName());
        preparedStatement.setString(4, user.getPass());
        preparedStatement.setString(5, "");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        preparedStatement.setString(6, dtf.format(now));
        preparedStatement.executeUpdate();
    }
}
