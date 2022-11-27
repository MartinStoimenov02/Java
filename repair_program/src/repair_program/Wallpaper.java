package repair_program;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Wallpaper extends Repair {
	
	private float lengthWP;
	private float widthWP;
	private float price;
	private String command;
	
	public Wallpaper(float width, float length, float height, float []win, float []door, float lengthWP, float widthWP, float price, String command) {
		super(width, length, height, win, door);
		setLengthWP(lengthWP);
		setWidthWP(widthWP);
		setPrice(price);
		setCommand(command);
	}
	
	public float getLengthWP() {
		return lengthWP;
	}

	public void setLengthWP(float lengthWP) {
		while(lengthWP<0) {
			System.out.println("Do you mean "+(-lengthWP)+" for Length of lawwpaper?(Y/N)");
			String ch = MainClass.sc.next();
			if (ch.equals("Y")) {
				this.lengthWP = -lengthWP;
				return;
			}
			else if(ch.equals("N")) {
				System.out.print("Can't calculate with negative number! Enter a valid value: ");
				lengthWP=MainClass.sc.nextFloat();
			}
		}
		this.lengthWP=lengthWP;
	}

	public float getWidthWP() {
		return this.widthWP;
	}

	public void setWidthWP(float widthWP) {
		while(widthWP<0) {
			System.out.println("Do you mean "+(-widthWP)+" for width of wallpaper?(Y/N)");
			String ch = MainClass.sc.next();
			if (ch.equals("Y")) {
				this.widthWP = -widthWP;
				return;
			}
			else if(ch.equals("N")) {
				System.out.print("Can't calculate with negative number! Enter a valid value: ");
				widthWP=MainClass.sc.nextFloat();
			}
		}
		this.widthWP=widthWP;
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
	
	int calculateCount() {
		float br, S;
		int count;
		S=Calculate();
		br=S/(this.lengthWP*this.widthWP);
		BigDecimal brUp=new BigDecimal(br).setScale(0,RoundingMode.UP);
		count=brUp.intValue();
		return count;
	}
	
	float calculatePrice() {
		return calculateCount()*this.price;
	}
}