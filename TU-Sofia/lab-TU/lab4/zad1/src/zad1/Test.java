package zad1;

public class Test {
	public static void main(String[] args) throws PriceException, PowerException {
		try {
			Books book = new Books (0, 1234, "Autor", "Zaglavie");
			System.out.println("new price book 1: "+book.checkPromo());
		}
		catch(Exception e) {
			System.out.println(e);
			
		}
		Books book1 = new Books (21, 1231, "Autor1", "Zaglavie1");
		Books book2 = new Books (30, 1232, "Autor2", "Zaglavie2");
		Televisiors tv = new Televisiors(200, 1, "Philips", "TV-123cf", 40);
		Televisiors tv1 = new Televisiors(250, 2, "LG", "LG-awcf", 50);
		Televisiors tv2 = new Televisiors(300, 3, "Samsung", "O-123", 150);
		System.out.println("new price book 2: "+book1.checkPromo());
		System.out.println("new price book 3: "+book2.checkPromo());
		System.out.println("new price tv 1: "+tv.checkPromo());
		System.out.println("new price tv 2: "+tv1.checkPromo());
		System.out.println("new price tv 3: "+tv2.checkPromo());
	}
}
