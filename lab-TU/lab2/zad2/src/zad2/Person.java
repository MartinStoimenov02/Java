package zad2;

public class Person {
	String firstName;
	String lastName;
	String bDay;
	
	public Person(String fName, String lName, String bDay) {
		setFirstName(fName);
		setLastName(lName);
		setbDay(bDay);
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getbDay() {
		return this.bDay;
	}

	public void setbDay(String bDay) {
		this.bDay = bDay;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", bDay=" + bDay+"]";
	}
	
}
