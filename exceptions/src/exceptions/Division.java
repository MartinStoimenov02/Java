package exceptions;

public class Division {
	int a;
	int b;
	public Division(int a, int b) {
		setA(a);
		setB(b);
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) throws ArithmeticException {
//			if(b==0) {
//				throw new ArithmeticException(); 
//			}
//			else {
				this.b = b;
			//}
	}
	
	public double div() {
		if(b==0) {
			throw new ArithmeticException("you cannot / by zero!?!?!?!?!?!?!?!?!"); 
		}
		return this.a/this.b;
	}
	
}
