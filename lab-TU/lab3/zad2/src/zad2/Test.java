package zad2;

public class Test {

	public static void main(String[] args) {
		Excursion ex = new Excursion("Rodopi", 5, 600, 3);
		System.out.println("price: "+ex.fixPrice());
		System.out.println("broy dni: "+ex.daysCount());
		Vacation v = new Vacation("Plovdiv", 7, 1200, "Plovdiv");
		System.out.println("price: "+v.fixPrice());
	}

}
