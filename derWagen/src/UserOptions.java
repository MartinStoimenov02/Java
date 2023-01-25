import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UserOptions {
    static Connection connection;
    public UserOptions() throws SQLException {
        this.connection = ConnectToDatabase.getConnection();
    }
    public void newPassword() throws SQLException{
        Scanner sc = new Scanner(System.in);
        String usrName, number;
        System.out.println("user name:");
        usrName=sc.next();
        System.out.println("phone number: ");
        number=sc.next();
        String query = "select * from usr where usrName=\"" + usrName + "\" and number=\"" + number + "\";";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            System.out.println("this username doesn't exist!");
        }else{
            String newPassword="", newPasswordRepeat="";
            boolean flag;
            do{
                try{
                    flag=true;
                    System.out.println("new password: ");
                    newPassword=sc.next();
                    System.out.println("repeat new password: ");
                    newPasswordRepeat=sc.next();
                    User.checkPassword(newPassword);
                }catch(SignUpException e){
                    flag=false;
                }
            }while(!newPassword.equals(newPasswordRepeat) || !flag);

            newPassword= String.valueOf(User.hashCode(newPassword));
            query="Update usr set pass = \""+newPassword+"\" where usrName=\"" + usrName + "\" and number=\"" + number + "\";";
            preparedStatement = connection.prepareStatement(query);
            int correct = preparedStatement.executeUpdate();
            if(correct==1){
                System.out.println("your password has been changed!");
            }else{
                System.out.println("something's wrong!");
            }
        }
    }

    public void saveAdInProfile(int id, int idOfVehicle) throws SQLException {
        String query = "select savedAds from usr where id="+id+";";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        String savedAdsBefore="";
        if (resultSet.next()){
            savedAdsBefore = resultSet.getString("savedAds");
        }
        String[] string={};
        if(savedAdsBefore!=null){
            string = savedAdsBefore.split(",");
        }
        // declaring an array with the size of string
        int[] arr = new int[string.length];

        // parsing the String argument as a signed decimal
        // integer object and storing that integer into the
        // array
        if(!string[0].equals("")){
            for (int i = 0; i < string.length; i++) {
                arr[i] = Integer.valueOf(string[i]);
            }
        }

        String record;
        if(toStringArray(arr)==""){
            record= String.valueOf(idOfVehicle);
        }
        else{
            record = toStringArray(arr)+ "," + idOfVehicle;
        }
        query="Update usr set savedAds = \""+ record +"\" where id="+id+";";
        preparedStatement = connection.prepareStatement(query);
        int correct = preparedStatement.executeUpdate();
        if(correct!=0){
            System.out.println("this ad has been added successfully in your profile!");
        }
    }

    public void unsaveAdInProfile(int id, int idOfVehicle) throws SQLException {
        String query = "select savedAds from usr where id="+id+";";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        String savedAdsBefore="";
        if (resultSet.next()){
            savedAdsBefore = resultSet.getString("savedAds");
        }
        String[] string={};
        if(savedAdsBefore!=null){
            string = savedAdsBefore.split(",");
        }
        // declaring an array with the size of string
        ArrayList<Integer> arr = new ArrayList<Integer>();
        //int[] arr = new int[string.length];

        // parsing the String argument as a signed decimal
        // integer object and storing that integer into the
        // array
        for (int i = 0; i < string.length; i++) {
            arr.add(Integer.valueOf(string[i]));
        }

        arr.remove(Integer.valueOf(idOfVehicle));
        String record;
        record= arrayListToString(arr);
        query="Update usr set savedAds = \""+ record +"\" where id="+id+";";
        preparedStatement = connection.prepareStatement(query);
        int correct = preparedStatement.executeUpdate();
        if(correct!=0){
            System.out.println("this ad has been removed successfully from your profile!");
        }
    }

    public static String arrayListToString(ArrayList<Integer> arr){
        StringBuilder str = new StringBuilder("");

        // Traversing the ArrayList
        for (int ids : arr) {

            // Each element in ArrayList is appended
            // followed by comma
            str.append(ids).append(",");
        }

        // StringBuffer to String conversion
        String commaseparatedlist = str.toString();

        // Condition check to remove the last comma
        if (commaseparatedlist.length() > 0){
            commaseparatedlist = commaseparatedlist.substring(0, commaseparatedlist.length() - 1);
        }
        return commaseparatedlist;
    }

    public static String toStringArray(int []a){
        if (a == null)
            return "null";
        int iMax = a.length - 1;
        if (iMax == -1)
            return "";
        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(a[i]);
            if (i == iMax)
                return b.toString();
            b.append(",");
        }
    }
}