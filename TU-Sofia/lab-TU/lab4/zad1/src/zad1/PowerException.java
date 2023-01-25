package zad1;

public class PowerException extends Exception{
	@Override
	public String getMessage() {
		return "Invalid power!";
	}
}
