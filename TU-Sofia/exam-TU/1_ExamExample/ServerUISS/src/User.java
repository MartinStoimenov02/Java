import java.io.Serializable;

public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    public User(String username, String password){
        setUsername(username);
        setPassword(password);
    }
    public void setUsername(String username){
        this.username=username;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public abstract UserType getUserType();

    public String getUsername() {
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
