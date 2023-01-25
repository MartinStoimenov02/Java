import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student extends User{
    private List<Grade> grades;
    public Student(String username, String password){
        super(username, password);
        grades=new ArrayList<>();
    }

    public UserType getUserType(){
        return UserType.STUDENT;
    }

    public List<Grade> getGrades() {
        return grades;
    }
}
