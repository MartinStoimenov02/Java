package zad2;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student st = new Student("Ivan", "Ivanov", "01.04.2002", "1212121212", 6, 5, 6);
		Student st1 = new Student("George", "Georgiev", "01.01.2002", "2121212121", 3, 3, 4);
		Student st2 = new Student("Ivan", "Georgiev", "04.01.2002", "1122112211", 3, 4, 5);
		Student []arr= {st, st1, st2};
		for(Student s:arr) {
			System.out.println(s.toString());
		}
		
	}

}
