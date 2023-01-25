package list;

public class Test {

	public static void main(String[] args) {
		Array arr = new Array(5);
//		System.out.println(arr.next());
//		System.out.println(arr.next());
		while(arr.hasNext()) {
			System.out.println(arr.next());
		}
	}
}
