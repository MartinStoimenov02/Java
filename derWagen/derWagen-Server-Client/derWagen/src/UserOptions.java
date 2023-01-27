import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserOptions {
    static Connection connection;
    public UserOptions() throws SQLException {
        connection = ConnectToDatabase.getConnection();
    }
    public void forgotPassword(Scanner sc, PrintStream out) throws SQLException{
        String usrName, number;
        out.println("user name:");
        usrName=sc.nextLine();
        out.println("phone number: ");
        number=sc.nextLine();
        String query = "select * from usr where usrName=\"" + usrName + "\" and number=\"" + number + "\";";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            out.println("this username doesn't exist!");
        }else{
            changePassword(usrName, number, out, sc);
        }
    }

    public void changePassword(String usrName, String number, PrintStream out, Scanner sc) throws SQLException {
        String newPassword="", newPasswordRepeat="";
        boolean flag;
        do{
            try{
                flag=true;
                out.println("new password(It must contains at least one small letter, one big letter, and one number, length: minimum 8 symbols): ");
                newPassword=sc.nextLine();
                out.println("repeat new password: ");
                newPasswordRepeat=sc.nextLine();
                User.checkPassword(newPassword, out);
            }catch(SignUpException e){
                flag=false;
            }
        }while(!newPassword.equals(newPasswordRepeat) || !flag);

        newPassword= String.valueOf(User.hashCode(newPassword));
        String query="Update usr set pass = \""+newPassword+"\" where usrName=\"" + usrName + "\" and number=\"" + number + "\";";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        int correct = preparedStatement.executeUpdate();
        if(correct==1){
            out.println("your password has been changed!");
        }else{
            out.println("something's wrong!");
        }
    }

    public User getUserFromId(int idUsr, PrintStream out) throws SQLException, SignUpException {
        String query = "select * from usr where id="+idUsr+";";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user;
        if (resultSet.next()){
            user = new User(resultSet.getString("name"), resultSet.getString("number"),
                    resultSet.getString("usrName"), out);
            String savedAdsString = resultSet.getString("savedAds");
            if(!savedAdsString.equals("")){
                List<Integer> savedAds = Arrays.stream(savedAdsString.split(",")).map(Integer::parseInt).collect(Collectors.toList());
                user.setSavedAds(savedAds);
            }
            return user;
        }
        return null;
    }

    public static boolean deleteUser(int idUsr, Scanner sc, PrintStream out) throws SQLException, IndexOfVehicleException, SignUpException {
        ReadFromDatabase read = new ReadFromDatabase();
        String query = "SELECT * FROM car;";
        ResultSet resultSet = read.getUsersOrVehicle(query, out);
        if(resultSet!=null) {
            while (resultSet.next()) {
                int userID = resultSet.getInt("idUsr");
                int id = resultSet.getInt("id");
                if (userID == idUsr) {
                    AdOptions adOptions = new AdOptions();
                    adOptions.deleteAd(id, sc, out);
                }
            }
        }
        query = "DELETE FROM usr WHERE id="+idUsr+";";

        if(read.deleteOrEdit(query, out)){return true;}
        return false;
    }

    public boolean editUser(int idUsr, User user, Scanner sc, PrintStream out, String property) throws SQLException, IndexOfVehicleException, SignUpException {
        switch(property){
            case "password":
                changePassword(user.getUsrName(), user.getNumber(), out, sc);
                return true;
            case "username":
                if(changeUsername(idUsr, out, sc))return true;
                break;
            case "number":
                if(changePhoneNumber(idUsr, out, sc))return true;
                break;
            case "name":
                String name="";
                name=sc.nextLine();
                String query="Update usr set name = \""+name+"\" where id="+idUsr+";";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                int correct = preparedStatement.executeUpdate();
                if(correct==1){
                    return true;
                }
            case "back":
                Menu.editProfile(idUsr, user, sc, out);
        }
        return false;
    }

    public boolean changeUsername(int idUsr, PrintStream out, Scanner sc) throws SQLException {
        String username="";
        try{
            boolean flag;
            do{
                try{
                    flag=true;
                    out.println("new username(it must contains only small letters and numbers, length: minimum 10 symbols): ");
                    username=sc.nextLine();
                    User.checkUsrName(username, out);
                }catch(SignUpException e){
                    flag=false;
                }
            }while(!flag);

            String query="Update usr set usrName = \""+username+"\" where id="+idUsr+";";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int correct = preparedStatement.executeUpdate();
            if(correct==1){
                return true;
            }
        }catch(Exception e){
            if (e.toString().equals("java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '" + username + "' for key 'usr.usrName'")) {
                out.println("User with this user name is already exist!");
            }
        }
        return false;
    }

    public boolean changePhoneNumber(int idUsr, PrintStream out, Scanner sc) throws SQLException {
        String number="";
        try {
            boolean flag;
            do {
                try {
                    flag = true;
                    out.println("new phone number: ");
                    number = sc.nextLine();
                    User.checkNumber(number, out);
                } catch (SignUpException e) {
                    flag = false;
                }
            } while (!flag);

            String query = "Update usr set number = \"" + number + "\" where id=" + idUsr + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int correct = preparedStatement.executeUpdate();
            if (correct == 1) {
                return true;
            }
        }catch(Exception e){
            if (e.toString().equals("java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '" + number + "' for key 'usr.number'")) {
                out.println("User with this phone number is already exist!");
            }
        }
        return false;
    }
}
