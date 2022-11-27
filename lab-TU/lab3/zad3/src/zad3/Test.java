package zad3;

public class Test {

	public static void main(String[] args) {
		SickPatient sp = new SickPatient("Ivan", "Sofia", "0202020202", 
				"OVI - vitamin C, Taylol hot", "bolen", 25);
		sp.cure("Trahizan", 3);
		System.out.println(sp.toString());
	}

}
