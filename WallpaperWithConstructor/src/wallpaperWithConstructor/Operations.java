package wallpaperWithConstructor;

import java.util.Scanner;

public class Operations {
	public Scanner sc = new Scanner(System.in);
	private float a, b, h;
	private float []win;
	private float []door;

	public Operations(float a, float b, float h, float []win, float []door) {
		// TODO Auto-generated constructor stub
		setA(a);
		setB(b);
		setH(h);
		setWin(win);
		setDoor(door);
	}
	
	
	
	public float getA() {
		return this.a;
	}



	public void setA(float a) {
		while(a<0) {
			System.out.println("Do you mean "+(-a)+" for Length?(Y/N)");
			String ch = sc.next();
			if (ch.equals("Y")) {
				this.a = -a;
				return;
			}
			else if(ch.equals("N")) {
				System.out.print("Can't calculate with negative number! Enter a valid value: ");
				a=sc.nextFloat();
			}
		}
		this.a=a;
	}



	public float getB() {
		return this.b;
	}



	public void setB(float b) {
		while(b<0) {
			System.out.println("Do you mean "+(-b)+" for Width?(Y/N)");
			String ch = sc.next();
			if (ch.equals("Y")) {
				this.b = -b;
				return;
			}
			else if(ch.equals("N")) {
				System.out.print("Can't calculate with negative number! Enter a valid value: ");
				b=sc.nextFloat();
			}
		}
		this.b=b;
	}



	public float getH() {
		return this.h;
	}



	public void setH(float h) {
		while(h<0) {
			System.out.println("Do you mean "+(-h)+" for Height?(Y/N)");
			String ch = sc.next();
			if (ch.equals("Y")) {
				this.h = -h;
				return;
			}
			else if(ch.equals("N")) {
				System.out.print("Can't calculate with negative number! Enter a valid value: ");
				h=sc.nextFloat();
			}
		}
		this.h=h;
	}



	public float getSumWin() {
		float sum_win=0;
		for(int i=0; i<this.win.length; i+=2) {
			sum_win+=this.win[i]*this.win[i+1];
		}
		return sum_win;
	}



	public void setWin(float[] win) {
		this.win = win;
	}



	public float getSumDoor() {
		float sum_door=0;
		for(int i=0; i<this.door.length; i+=2) {
			sum_door+=this.door[i]*this.door[i+1];
		}
		return sum_door;
	}

	

	public void setDoor(float[] door) {
		this.door = door;
	}
}
