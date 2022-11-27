package repair_program;

import java.util.Scanner;

public class MainClass {
	public static Scanner sc = new Scanner(System.in);
	public static float a, b, h;
	public static float []win;
	public static float[]door;
	public static String command;
	public static void main (String []args) {
		String command;
		while (true) {
			System.out.print("Command (WP(for walpapers), LT (for latex), TL(for tiles)): ");
			command=sc.next();
			
			if(command.equals("WP") || command.equals("wp")){
	            
				input();
				wallPaper();		
	       }
			
			else if(command.equals("LT") || command.equals("lt")){
	            
				input();
				latex();		
	       }
			
			else if(command.equals("TL") || command.equals("tl")){
	            
				input();
				tiles();		
	       }
		}
	}
	
	public static void input() {
		System.out.print("Calculate Quadrature (All, Walls, Floor/Roof, WidthWall, LengthWall): ");
		command=sc.next();
		System.out.print("Length of room:");
		a=sc.nextFloat();
		System.out.print("Width of room:");
		b=sc.nextFloat();
		System.out.print("Height of room:");
		h=sc.nextFloat();
		win=window();
		door=door();
	}
	
	public static float[] window() {
		int n;
		System.out.print("Number of windows in room: ");
		n=sc.nextInt();
		float []win;
		win=new float[n*2];
		for(int i=0; i<n*2; i+=2) {
			System.out.print("Length of "+(i+1)+" window: ");
			win[i]=sc.nextFloat();
			System.out.print("Width of "+(i+1)+" window: ");
			win[i+1]=sc.nextFloat();
		}
		return win;
	}
	
	public static float[] door() {
		int m;
		System.out.print("Number of doors in room: ");
		m=sc.nextInt();
		float []door;
		door=new float[m*2];
		for(int i=0; i<m*2; i+=2) {
			System.out.print("Length of "+(i+1)+" door: ");
			door[i]=sc.nextFloat();
			System.out.print("Width of "+(i+1)+" door: ");
			door[i+1]=sc.nextFloat();
		}
		return door;
	}
	
	public static void wallPaper() {
		float wpa, wpb, pr;
		System.out.print("Length of the roll: ");
		wpa=sc.nextFloat();
		System.out.print("Width of the roll: ");
		wpb=sc.nextFloat();
		System.out.print("Price of a roll: ");
		pr=sc.nextFloat();
		Wallpaper obj = new Wallpaper(a, b, h, win, door, wpa, wpb, pr, command);
		System.out.println("Quadrature of room:"+obj.Calculate());
		System.out.println("count of rolls: "+obj.calculateCount());
		System.out.println("Full price: "+obj.calculatePrice());
	}
	
	public static void latex() {
		float l, pr;
		System.out.print("Litres of the bucket: ");
		l=sc.nextFloat();
		System.out.print("Price of a bucket: ");
		pr=sc.nextFloat();
		Latex obj = new Latex(a, b, h, win, door, l, pr, command);
		System.out.println("Quadrature of room:"+obj.Calculate());
		System.out.println("count of buckets: "+obj.calculateCount());
		System.out.println("Full price: "+obj.calculatePrice());
	}
	
	public static void tiles() {
		float ta, tb, pr;
		int br;
		System.out.print("Length of the tile: ");
		ta=sc.nextFloat();
		System.out.print("Width of the tile: ");
		tb=sc.nextFloat();
		System.out.print("Count of tiles in a carton: ");
		br=sc.nextInt();
		System.out.print("Price of a carton: ");
		pr=sc.nextFloat();
		Tiles obj = new Tiles(a, b, h, win, door, ta, tb, br, pr, command);
		System.out.println("Quadrature of room:"+obj.Calculate());
		System.out.println("count of cartoons: "+obj.calculateCountOfCartoons());
		System.out.println("count of tiles: "+obj.calculateCountOfTiles());
		System.out.println("Full price: "+obj.calculatePrice());
	}
}
