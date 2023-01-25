package lab2;

public class Student extends Person {
	private String fNum;
	public Student() {
		super();
		this.fNum=" ";
	}
	
	public Student(String n, String e, String f) {
		super(n,e);
		this.fNum=f;
	}
}
