import java.io.PrintStream;
import java.sql.*;

public class ReadFromDatabase {
    Connection connection;

    public ReadFromDatabase() throws SQLException {
        this.connection = ConnectToDatabase.getConnection();
    }

    public int getCountOfVehicles(String query, PrintStream out) throws SQLException {
        try{
            query="SELECT COUNT(*) "+query.substring(9, query.length());
            PreparedStatement preparedStatement1 = connection.prepareStatement(query);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();
            return resultSet1.getInt(1);
        }catch(SQLSyntaxErrorException e){
            out.println("Something's wrong! Check input params!");
        }
        return 0;
    }
    public ResultSet getUsersOrVehicle(String query, PrintStream out) throws SQLException {
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
