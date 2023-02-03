import java.io.PrintStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private String number;
    private String usrName;
    private String pass;
    private List<Integer> friends;
    String lastseen;

    public User(String name, String number, String usrName, String pass, PrintStream out) throws SignUpException {
        setName(name);
        checkNumber(number, out);
        setNumber(number);
        checkUsrName(usrName, out);
        setUsrName(usrName);
        checkPassword(pass, out);
        setPass(pass);
        friends = new ArrayList<>();
    }

    public User(String name, String number, String usrName, PrintStream out) throws SignUpException {
        setName(name);
        checkNumber(number, out);
        setNumber(number);
        checkUsrName(usrName, out);
        setUsrName(usrName);
        friends = new ArrayList<>();
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

    public String getLastseen() {
        return lastseen;
    }

    public void setLastseen(String lastseen) {
        this.lastseen = lastseen;
    }

    public static boolean checkNumber(String number, PrintStream out) throws SignUpException {
        if (number.matches("[0-9]{9,10}$")) {
            return true;
        } else {
            out.println("phone number doesn't meet the requirements!");
            throw new SignUpException();
        }
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUsrName() {
        return usrName;
    }

    public static boolean checkUsrName(String usrName, PrintStream out) throws SignUpException {
        if (usrName.matches("[a-z0-9]{10,}$")) {
            return true;
        } else {
            out.println("user name doesn't meet the requirements!");
            throw new SignUpException();
        }
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getPass() {
        return pass;
    }

    public static boolean checkPassword(String pass, PrintStream out) throws SignUpException {
        if (pass.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
            return true;
        } else {
            out.println("password doesn't meet the requirements!");
            throw new SignUpException();
        }
    }

    public void setFriends(List<Integer> friends) {
        this.friends = friends;
    }

    public List<Integer> getfriends() {
        return friends;
    }

    public void setPass(String pass) {
        this.pass = String.valueOf(hashCode(pass));
    }

    public static int hashCode(String pass) {
        return Objects.hash(pass);
    }

    @Override
    public String toString() {
        return  name + " - " +
                number + " - " +
                usrName + '\n';
    }
}
