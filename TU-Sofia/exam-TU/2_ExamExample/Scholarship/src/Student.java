public class Student extends User{
    public Student(String username, String password){
        super(username, password);
    }

    public UserType getUserType(){
        return UserType.STUDENT;
    }
}
