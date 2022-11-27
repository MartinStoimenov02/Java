package lab5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator{
	private Pattern pattern;
	private Matcher matcher;
	
	private static final String PASSWORD_PATTERN = "[a-zA-Z]{11,}$";
	
	public PasswordValidator(){
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}
	
	public boolean validatePassword(String password){
	
		matcher = pattern.matcher(password);
		
		return matcher.matches();
	}
}