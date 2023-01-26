public class SignUpException extends Exception{
    @Override
    public String getMessage() {
        return "Invalid information!";
    }
}
