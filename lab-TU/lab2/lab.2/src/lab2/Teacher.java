package lab2;

public class Teacher extends Person{
	private Course nameCourse;
	public Teacher(String n, String e, Course nc) {
		super(n, e);
		this.nameCourse=nc;
	}
	
	public Course getNameCourse() {
		return this.nameCourse;
	}
	
	public void setNameCourse(Course nameCourse) {
		this.nameCourse = nameCourse;
	}
}
