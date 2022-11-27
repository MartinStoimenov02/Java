package lab5;

public class WrongPhoneNumberException extends WrongUserException{

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Invalid phone number!";
	}
}
