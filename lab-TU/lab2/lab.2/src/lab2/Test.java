package lab2;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p1=new Person();
		//p1.name="Ivan";
		p1.setName("Ivan");
		//System.out.println(p1.name);
		System.out.println(p1.getName());
		Person p2=new Person ("Georgi", "0202020202");
		//System.out.println(p2.name);
		System.out.println(p2.getName());
		p1.sayName();
		Person.sayHi();
		
		Course c = new Course ("PPE");
		Teacher t = new Teacher("doc. Ivanov", "7005050505", c);
		
		Course c1 = new Course ("math");
		Teacher t1 = new Teacher("doc. Petkov", "7005050505", c1);
		
		Course c2 = new Course ("PNPE");
		Teacher t2 = new Teacher("prof. Georgiev", "7005050505", c2);
		
		Teacher []arr={t, t1, t2};
		
		for(int i=0; i<arr.length; i++) {
			System.out.println("name: "+arr[i].getName()+"; Course: "+arr[i].getNameCourse().getNameCourse());
		}
		
	}

}