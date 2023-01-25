package zad1;

public class Test {
	public static void main(String []args) {
		Car car = new Car(240, "Opel", 12000, 115);
		car.enterLand();
		System.out.println("new Car price: "+car.checkPromo());
		Hovercraft hc = new Hovercraft(45, "HC-1029", 150, 2);
		hc.enterLand();
		System.out.println("new hovercraft price: "+hc.checkPromo());
		Ship ship = new Ship(50, "LN-48324", 2000000, 200);
		ship.enterSea();
		System.out.println("new ship price: "+ship.checkPromo());
	}
}
