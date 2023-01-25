import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DesignationSelector {
    Connection connection;

    public DesignationSelector(Connection connection) {
        this.connection = connection;
    }

    public ResultSet getAllDesignation() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM designation;");
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public ResultSet getDesignationsByTitle(String title) throws SQLException {
//        title="\""+title+"\"";
        PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT * FROM designation WHERE title=\""+title+"\";");
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
}
