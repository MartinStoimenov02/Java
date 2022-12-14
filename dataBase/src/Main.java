import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = Connect.getConnection();

        InputDesignation inputDesignation=new InputDesignation(connection);
        inputDesignation.inputDesign("title20");

        DesignationSelector designationSelector = new DesignationSelector(connection);
        ResultSet resultSet = designationSelector.getAllDesignation();

        ResultSet selectByTitle = designationSelector.getDesignationsByTitle("title1");

        PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT COUNT(*) FROM designation;");
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        resultSet1.next();
        int count = resultSet1.getInt(1);
        Designation[] arr = new Designation[count];
        int index = 0;
        while (resultSet.next()) {
//            System.out.println(resultSet.getInt("code"));
//            System.out.println(resultSet.getString("title"));
            Designation designation = new Designation(resultSet.getInt("code"), resultSet.getString("title"));
            arr[index] = designation;
            index++;
        }

        while (selectByTitle.next()) {
            System.out.println(selectByTitle.getInt("code"));
            System.out.println(selectByTitle.getString("title"));
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].toString());
        }
    }
}