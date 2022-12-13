import java.util.Objects;

public class User {
    private String name;
    private String number;
    private String usrName;
    private String pass;

    public User(String name, String number, String usrName, String pass) throws SignUpException {
        setName(name);
        checkNumber(number);
        setNumber(number);
        checkUsrName(usrName);
        setUsrName(usrName);
        checkPassword(pass);
        setPass(pass);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }
    public static boolean checkNumber(String number) throws SignUpException {
        if (number.matches("[0-9]{9,10}$")) {
            return true;
        }
        else{
            System.out.println("phone number doesn't meet the requirements!");
            throw new SignUpException();
        }
    }
    public void setNumber(String number){this.number=number;}

    public String getUsrName() {
        return usrName;
    }

    public static boolean checkUsrName(String usrName) throws SignUpException {
        if (usrName.matches("[a-z0-9]{10,}$")) {
            return true;
        }
        else{
            System.out.println("user name doesn't meet the requirements!");
            throw new SignUpException();
        }
    }
    public void setUsrName(String usrName){this.usrName=usrName;}

    public String getPass() {
        return pass;
    }

    public static boolean checkPassword(String pass) throws SignUpException {
        if (pass.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
            return true;
        }else{
            System.out.println("password doesn't meet the requirements!");
            throw new SignUpException();
        }
    }

    public void setPass(String pass) {
        this.pass= String.valueOf(hashCode(pass));
    }
    public static int hashCode(String pass) {
        return Objects.hash(pass);
    }
}