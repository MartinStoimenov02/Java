package wallpaper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Operations {
	public float a, b, h, S, br, pr;
	public float []win;
	public float []door;
	public int n,m;
	public Scanner keyboard=new Scanner(System.in);

	public void Input() {
		System.out.print("Price of a roll:");
		pr=keyboard.nextFloat();
		System.out.print("Length of room:");
		a=keyboard.nextFloat();
		System.out.print("Width of room:");
		b=keyboard.nextFloat();
		System.out.print("Height of room:");
		h=keyboard.nextFloat();
		System.out.print("Number of windows in room: ");
		n=keyboard.nextInt();
		System.out.print("Number of doors in room: ");
		m=keyboard.nextInt();
		calculations();
	}
	
	public float window() {
		win=new float[n*2];
		float sum_win = 0;
		for(int i=0; i<n*2; i+=2) {
			System.out.print("Length of "+(i+1)+" window: ");
			win[i]=keyboard.nextFloat();
			System.out.print("Width of "+(i+1)+" window: ");
			win[i+1]=keyboard.nextFloat();
		}
		for(int i=0; i<n*2; i+=2) {
			sum_win+=win[i]*win[i+1];
		}
		return sum_win;
	}
	
	public float door() {
		door=new float[m*2];
		float sum_door = 0;
		for(int i=0; i<m*2; i+=2) {
			System.out.print("Length of "+(i+1)+" door: ");
			door[i]=keyboard.nextFloat();
			System.out.print("Width of "+(i+1)+" door: ");
			door[i+1]=keyboard.nextFloat();
		}
		for(int i=0; i<m*2; i+=2) {
			sum_door+=door[i]*door[i+1];
		}
		return sum_door;
	}
	
	public void calculations() {
		float sum_win, sum_door;
		sum_win=window();
		sum_door=door();
		S=2*((a*h)+(b*h))-(sum_win+sum_door);
		br=S/5;
		
		print_result();
	}
	
	public void print_result() {
		System.out.println("Quadrature of room: "+S);
		BigDecimal brUp=new BigDecimal(br).setScale(0,RoundingMode.UP);
		br=brUp.intValue();
		System.out.printf("Number of rolls: %.0f\n", br);
		System.out.printf("Full price: %.2f\n",(br*pr));
	}
}
