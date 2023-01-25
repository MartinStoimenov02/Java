package lab5;

public class User implements ValidatePhone{
	String usname;
	String password;
	String number;
	
	public User(String usname, String password, String number) throws WrongPhoneNumberException {
		setUsname(usname);
		setPassword(password);
		setNumber(number);
		validatePhoneNumber(number);
	}

	public String getUsname() {
		return this.usname;
	}

	public void setUsname(String usname) {
		this.usname = usname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public void checkUsname() {
		UsernameValidator usr = new UsernameValidator();
		boolean bool = usr.validateUserName(this.usname);
		if (bool==true) {
			System.out.println("user name is valid!");
		}
		else {
			System.out.println("user name isn't valid!");
		}
	}
	
	public void checkPassword() {
		PasswordValidator psw = new PasswordValidator();
		boolean bool = psw.validatePassword(this.password);
		if (bool==true) {
			System.out.println("password is valid!");
		}
		else {
			System.out.println("password isn't valid!");
		}
	}

	@Override
	public boolean validatePhoneNumber(String phoneNumber) throws WrongPhoneNumberException {
		if (phoneNumber.matches("[0-9]{10}$")==false) {
			throw new WrongPhoneNumberException();
		}
		return true;
	}
	
}
