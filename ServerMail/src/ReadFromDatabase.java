import java.io.PrintStream;
import java.sql.*;

public class ReadFromDatabase {
    Connection connection;

    public ReadFromDatabase() throws SQLException {
        this.connection = ConnectToDatabase.getConnection();
    }

    public ResultSet getUsers(String query, PrintStream out) throws SQLException {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            return preparedStatement.executeQuery();
        }catch(SQLSyntaxErrorException e){
            out.println("Something's wrong! Check input params!");
        }
        return null;
    }

    public boolean deleteOrEdit(String query, PrintStream out) throws SQLException {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            return true;
        }catch(SQLSyntaxErrorException e){
            out.println("Something's wrong! Check input params!");
        }
        return false;
    }
}
