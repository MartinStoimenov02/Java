package lab5;

import java.util.Scanner;

public class Test {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws WrongPhoneNumberException{
		String []array= {"gosho_21-21", "usernameUserNAME", "0889212121", "vancho", "vancovanchoVANCHO", "0987654321"};
		String command, username, password, phoneNumber;
		System.out.println("command:");
		command=sc.nextLine();
		
		
		
		if(command.equals("signIn")) {
			System.out.println("username:");
			username=sc.next();
			System.out.println("password:");
			password=sc.next();
			for(int i=0; i<array.length; i+=3) {
				if(username.equals(array[i]) && password.equals(array[i+1])) {
					System.out.println("username: "+array[i]+" password: "+array[i+1]+" phone number: "+array[i+2]);
				}
			}
		}
		
		else if(command.equals("signUp")) {
			try {
				System.out.println("username:");
				username=sc.next();
				System.out.println("password:");
				password=sc.next();
				System.out.println("phone number:");
				phoneNumber=sc.next();
				for(int i=0; i<array.length; i+=3) {
					if(username.equals(array[i])) {
						System.out.println("exist user");
					}
				}
				User user = new User(username, password, phoneNumber);
				user.checkUsname();
				user.checkPassword();
			}
			catch(WrongPhoneNumberException e) {
				System.out.println(e);
			}
		}
		
		else {
			System.out.println("invalid command");
		}
	}

}
