import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        while(true){
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
            if(newPassword.equals(newPasswordRepeat) && flag){
                break;
            }
            if(flag){
                out.println("try again:");
            }

        }

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
            String friendsString = resultSet.getString("friends");

            List<Integer> friends = new ArrayList<>();
            if(friendsString!=null && !friendsString.equals("")){
                friends = Arrays.stream(friendsString.split(",")).map(Integer::parseInt).collect(Collectors.toList());
                user.setFriends(friends);
            }
            String date=resultSet.getString("lastseen");
            user.setLastseen(date);
            return user;
        }
        return null;
    }

    public void changeLastseen(int idUsr) throws SQLException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query="Update usr set lastseen = \""+dtf.format(now)+"\" where id=" + idUsr+";";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
    }

    public boolean addToContact(int idUsr, int receiver) throws SQLException {
        List<Integer> friends = Login.user.getfriends();
        if(friends.contains(receiver)){
            return false;
        }
        else {
            friends.add(receiver);
            String stringFriends = friends.stream().map(String::valueOf).collect(Collectors.joining(","));
            String query="Update usr set friends = \""+ stringFriends +"\" where id="+idUsr+";";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int correct = preparedStatement.executeUpdate();
            if(correct!=0){
                return true;
            }
        }
        return false;
    }

    public static boolean myContacts(int idUsr, Scanner sc, PrintStream out) throws SQLException, SignUpException {
        UserOptions userOptions = new UserOptions();
        User user = userOptions.getUserFromId(idUsr, out);
        List<Integer> friends = user.getfriends();
        if(friends.size()==0){
            return false;
        }
        String query = "select * from usr where ";
        for(int i : friends){
            query=query+"id="+i + " or ";
        }
        query=query.substring(0, query.length() - 3);
        SearchUsers.readUsers(query, idUsr, sc, out);
        return true;
    }

    public static boolean deleteFromFriends(int idUsr, PrintStream out) throws SQLException, SignUpException {
        ReadFromDatabase readFromDatabase = new ReadFromDatabase();
        String query = "SELECT * FROM usr;";
        ResultSet resultSet = readFromDatabase.getUsers(query, out);
        if(resultSet!=null){
            while (resultSet.next()) {
                int userID = resultSet.getInt("id");
                UserOptions userOptions = new UserOptions();
                User user = userOptions.getUserFromId(userID, out);
                String friendsString = resultSet.getString("friends");
                List<Integer> friends;
                if (friendsString!=null && !friendsString.equals("")) {
                    friends = Arrays.stream(friendsString.split(",")).map(Integer::parseInt).collect(Collectors.toList());
                    user.setFriends(friends);

                    if (friends.contains(idUsr)) {
                        friends.remove(Integer.valueOf(idUsr));
                        String stringSavedAds = friends.stream().map(String::valueOf).collect(Collectors.joining(","));
                        query = "Update usr set friends = \"" + stringSavedAds + "\" where id=" + userID + ";";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        int correct = preparedStatement.executeUpdate();
                        if (correct != 0) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean deleteUser(int idUsr, PrintStream out) throws SQLException, SignUpException {
        ReadFromDatabase read = new ReadFromDatabase();
        if(deleteFromFriends(idUsr, out)){
            String query = "DELETE FROM usr WHERE id="+idUsr+";";
            return read.deleteOrEdit(query, out);
        }
        return false;
    }

    public boolean editUser(int idUsr, Scanner sc, PrintStream out, String property) throws SQLException, SignUpException {
        User user = getUserFromId(idUsr, out);
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
                out.println("new name:");
                String name=sc.nextLine();
                String query="Update usr set name = \""+name+"\" where id="+idUsr+";";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                int correct = preparedStatement.executeUpdate();
                if(correct==1){
                    return true;
                }
            case "back":
                Server.editProfile(idUsr, sc, out);
        }
        return false;
    }

    public boolean changeUsername(int idUsr, PrintStream out, Scanner sc){
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

    public boolean changePhoneNumber(int idUsr, PrintStream out, Scanner sc){
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
