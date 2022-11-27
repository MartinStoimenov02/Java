package repair_program;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tiles extends Repair{
	private float lengthT;
	private float widthT;
	private int brCartoon;
	private float price;
	private String command;
	
	public Tiles(float width, float length, float height, float []win, float []door, float lengthT, float widthT, int brCartoon, float price, String command) {
		super(width, length, height, win, door);
		setLengthT(lengthT);
		setWidthT(widthT);
		setBrCartoon(brCartoon);
		setPrice(price);
		setCommand(command);
	}
	
	public float getLengthT() {
		return this.lengthT;
	}

	public void setLengthT(float lengthT) {
		while(lengthT<0) {
			System.out.println("Do you mean "+(-lengthT)+" for Length of a tile?(Y/N)");
			String ch = MainClass.sc.next();
			if (ch.equals("Y")) {
				this.lengthT = -lengthT;
				return;
			}
			else if(ch.equals("N")) {
				System.out.print("Can't calculate with negative number! Enter a valid value: ");
				lengthT=MainClass.sc.nextFloat();
			}
		}
		this.lengthT=lengthT;
	}

	public float getWidthT() {
		return this.widthT;
	}

	public void setWidthT(float widthT) {
		while(widthT<0) {
			System.out.println("Do you mean "+(-widthT)+" for width of a tile?(Y/N)");
			String ch = MainClass.sc.next();
			if (ch.equals("Y")) {
				this.widthT = -widthT;
				return;
			}
			else if(ch.equals("N")) {
				System.out.print("Can't calculate with negative number! Enter a valid value: ");
				widthT=MainClass.sc.nextFloat();
			}
		}
		this.widthT=widthT;
	}
	
	public int getBrCartoon() {
		return this.brCartoon;
	}

	public void setBrCartoon(int brCartoon) {
		while(brCartoon<0) {
			System.out.println("Do you mean "+(-brCartoon)+" for width of a tile?(Y/N)");
			String ch = MainClass.sc.next();
			if (ch.equals("Y")) {
				this.brCartoon = -brCartoon;
				return;
			}
			else if(ch.equals("N")) {
				System.out.print("Can't calculate with negative number! Enter a valid value: ");
				brCartoon=MainClass.sc.nextInt();
			}
		}
		this.brCartoon=brCartoon;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		while(price<0) {
			System.out.println("Do you mean "+(-price)+" for price?(Y/N)");
			String ch = MainClass.sc.next();
			if (ch.equals("Y")) {
				this.price = -price;
				return;
			}
			else if(ch.equals("N")) {
				System.out.print("Can't calculate with negative number! Enter a valid value: ");
				price=MainClass.sc.nextFloat();
			}
		}
		this.price=price;
	}
	
	public String getCommand() {
		return this.command;
	}

	public void setCommand(String command) {
		if (command.equals("All")||command.equals("all")||command.equals("Walls")||command.equals("walls")||command.equals("Floor")||command.equals("floor")||command.equals("Roof")||command.equals("roof")||command.equals("WidthWall")||command.equals("widthwall")||command.equals("LengthWall")||command.equals("lengthwall")) {
			this.command=command;
		}
		else {
			System.out.println("error command!");
			System.exit(0);
		}
	}

	@Override
	float Calculate() {
		float S;
		if(this.command.equals("All")||this.command.equals("all")) {
			S=2*((this.getWidth()*this.getHeight())+(this.getLength()*this.getHeight()))+(this.getLength()*this.getWidth())-(this.getSumWin()+this.getSumDoor());
		}
		
		else if(this.command.equals("Walls")||this.command.equals("walls")) {
			S=2*((this.getWidth()*this.getHeight())+(this.getLength()*this.getHeight()))-(this.getSumWin()+this.getSumDoor());
		}
		
		else if(this.command.equals("Floor")||this.command.equals("floor")||this.command.equals("Roof")||this.command.equals("roof")) {
			S=(this.getLength()*this.getWidth())-(this.getSumWin()+this.getSumDoor());
		}
		
		else if(this.command.equals("WidthWall")||this.command.equals("widthwall")) {
			S=this.getWidth()*this.getHeight()-(this.getSumWin()+this.getSumDoor());
		}
		
		else if(this.command.equals("LengthWall")||this.command.equals("lengthwall")) {
			S=this.getLength()*this.getHeight()-(this.getSumWin()+this.getSumDoor());
		}
		
		else {
			return 0;
		}
		return S;
	}
	
	int calculateCountOfCartoons() {
		float br, S;
		int count;
		S=Calculate();
		br=S/((this.lengthT*this.widthT)*this.brCartoon);
		BigDecimal brUp=new BigDecimal(br).setScale(0,RoundingMode.UP);
		count=brUp.intValue();
		return count;
	}
	
	int calculateCountOfTiles() {
		float br, S;
		int count;
		S=Calculate();
		br=S/(this.lengthT*this.widthT);
		BigDecimal brUp=new BigDecimal(br).setScale(0,RoundingMode.UP);
		count=brUp.intValue();
		return count;
	}
	
	float calculatePrice() {
		return calculateCountOfCartoons()*this.price;
	}
}
