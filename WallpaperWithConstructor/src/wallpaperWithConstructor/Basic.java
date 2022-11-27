package wallpaperWithConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Basic {
	
	public static Scanner keyboard = new Scanner(System.in);
	public static Operations op;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float a, b, h;
		float []win;
		float[]door;
		System.out.print("Length of room:");
		a=keyboard.nextFloat();
		System.out.print("Width of room:");
		b=keyboard.nextFloat();
		System.out.print("Height of room:");
		h=keyboard.nextFloat();
		win=Basic.window();
		door=Basic.door();
		op = new Operations(a, b, h, win, door);
		Basic.calculate();
	}
	
	
	public static float[] window() {
		int n;
		System.out.print("Number of windows in room: ");
		n=keyboard.nextInt();
		float []win;
		win=new float[n*2];
		for(int i=0; i<n*2; i+=2) {
			System.out.print("Length of "+(i+1)+" window: ");
			win[i]=keyboard.nextFloat();
			System.out.print("Width of "+(i+1)+" window: ");
			win[i+1]=keyboard.nextFloat();
		}
		return win;
	}
	
	public static float[] door() {
		int m;
		System.out.print("Number of doors in room: ");
		m=keyboard.nextInt();
		float []door;
		door=new float[m*2];
		for(int i=0; i<m*2; i+=2) {
			System.out.print("Length of "+(i+1)+" door: ");
			door[i]=keyboard.nextFloat();
			System.out.print("Width of "+(i+1)+" door: ");
			door[i+1]=keyboard.nextFloat();
		}
		return door;
	}
	
	
	public static void calculate() {
		float S, br, pr;
		System.out.print("price of a roll: ");
		pr=keyboard.nextFloat();
		S=2*((op.getA()*op.getH())+(op.getB()*op.getH()))-(op.getSumWin()+op.getSumDoor());
		br=S/5;
		System.out.println("Quadrature of room: "+S);
		BigDecimal brUp=new BigDecimal(br).setScale(0,RoundingMode.UP);
		br=brUp.intValue();
		System.out.printf("Number of rolls: %.0f\n", br);
		System.out.printf("Full price: %.2f\n",(br*pr));
	}

}
