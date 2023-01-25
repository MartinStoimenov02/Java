import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IndexOfVehicleException, SignUpException {
        Scanner sc = new Scanner(System.in);
        String command;
        do{
            System.out.println("command (search, login, signUp, end): ");
            command=sc.next();
            command=command.toLowerCase();
            if(command.equals("search")){
                ReadVehicles.makeQuery(0);
            }
            else if(command.equals("signup")){
                Scanner input = new Scanner(System.in);
                String name, number, usrName, pass;
                System.out.println("name: ");
                name=input.nextLine();
                System.out.println("phone number: ");
                number=input.next();
                System.out.println("user name(it must contains only small letters and numbers, length: minimum 10 symbols): ");
                usrName=input.next();
                System.out.println("password(It must contains at least one small letter, one big letter, and one number, length: minimum 8 symbols): ");
                pass=input.next();
                try {
                    User user = new User(name, number, usrName, pass);
                    WriteUserInDatabase writeUser = new WriteUserInDatabase();
                    writeUser.writeUser(user);
                    System.out.println("Your new profile was created successfully");
                } catch (SignUpException | SQLException e) {
                    if(e.toString().equals("java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '"+number+"' for key 'usr.number'")){
                        System.out.println("User with this phone number is already exist!");
                    }
                    else if(e.toString().equals("java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '"+usrName+"' for key 'usr.usrName'")){
                        System.out.println("User with this user name is already exist!");
                    }
                    else{
                        System.out.println(e);
                    }
                }
            }
            else if(command.equals("login")){
                Scanner input = new Scanner(System.in);
                String usrName, pass;
                System.out.println("user name: ");
                usrName=input.next();
                System.out.println("password");
                pass=input.next();
                Login login = new Login();
                login.checkUser(usrName, pass);
            }
            else if(command.equals("end")){System.exit(0);}
        }while(true);
    }


}