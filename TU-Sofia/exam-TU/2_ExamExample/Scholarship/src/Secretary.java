import java.io.Serializable;

public class Secretary extends User{
    public Secretary(String username, String password) {
        super(username, password);
    }

    @Override
    public UserType getUserType() {
        return UserType.SECRETARY;
    }
}
