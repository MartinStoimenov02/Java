package arrayGlobal;

public class BasicClass {
	public static int[] array=new int[5];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProcessingClass obj = new ProcessingClass();
		obj.addArr();
		obj.printEl();
		obj.sort();
		obj.printEl();
		System.out.println("min element is: "+obj.min());
		System.out.println("max element is: "+obj.max());
//		for(int i=0; i<array.length; i++) {
//			System.out.print(array[i]+" ");
//		}
	}

}
