package lab5;

public class WrongUserException extends Exception{

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Invalid User!";
	}
}
