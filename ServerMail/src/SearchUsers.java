import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchUsers {
    public static void makeQuery(int idUsr, Scanner sc, PrintStream out) throws SQLException, SignUpException {
        out.println("Search by name, username, or phone:");
        String search = sc.nextLine();
        String sqlSelectQuery;
        if(search.equals("")){
            sqlSelectQuery="select * from usr";
        }
        else{
            sqlSelectQuery = "select * from usr where name="+search+" or usrName="+search+" or number="+search+";";
        }

        readUsers(sqlSelectQuery, idUsr, sc, out);
    }

    public static void readUsers(String query, int idUsr, Scanner sc, PrintStream out) throws SQLException, SignUpException {
        ReadFromDatabase readFromDatabase = new ReadFromDatabase();
        ResultSet resultSet = readFromDatabase.getUsers(query, out);
        List<Integer> users = new ArrayList<>();
        if(resultSet!=null){
            while (resultSet.next()) {
                int id=resultSet.getInt("id");
                UserOptions userOptions = new UserOptions();
                User user = userOptions.getUserFromId(id, out);
                out.println(id+")"+user.toString());
                users.add(id);
            }
        }

        if (users.size()==0) {
            if (Server.searchOrMyContacts==1) out.println("There isn't users with this search!");
            else out.println("There isn't saved contacts!");
            Server.loginMenu(idUsr, sc, out);
        }
        else {
            Server.newCorenspondationMenu(idUsr, users, sc, out);
        }
    }

    public static int chooseUser(List<Integer> users, Scanner sc, PrintStream out) {
        int index;
        do {
            out.println("who wants to chat with: ");
            index = sc.nextInt();
        } while (!users.contains(index));
        return index;
    }

    public static String getLastSeen(int receiver, PrintStream out) throws SQLException, SignUpException {
        UserOptions userOptions = new UserOptions();
        User user = userOptions.getUserFromId(receiver, out);
        return user.getLastseen();
    }
}
