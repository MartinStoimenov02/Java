import java.sql.*;

public class ReadFromDatabase {
    Connection connection;

    public ReadFromDatabase() throws SQLException {
        this.connection = ConnectToDatabase.getConnection();
    }

    public int getCountOfVehicles(String query) throws SQLException {
        try{
            query="SELECT COUNT(*) "+query.substring(9, query.length());
            PreparedStatement preparedStatement1 = ConnectToDatabase.getConnection().prepareStatement(query);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();
            int count = resultSet1.getInt(1);
            return count;
        }catch(SQLSyntaxErrorException e){
            System.out.println("Something's wrong! Check input params!");
        }
        return 0;
    }
    public ResultSet getVehicles(String query) throws SQLException {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        }catch(SQLSyntaxErrorException e){
            System.out.println("Something's wrong! Check input params!");
        }
        return null;
    }
}
