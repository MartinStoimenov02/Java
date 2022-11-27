package repair_program;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Latex extends Repair{
	private float litres;
	private float price;
	private String command;
	
	public Latex(float width, float length, float height, float []win, float []door, float litres, float price, String command) {
		super(width, length, height, win, door);
		setLitres(litres);
		setPrice(price);
		setCommand(command);
	}
	
	public float getLitres() {
		return this.litres;
	}

	public void setLitres(float litres) {
		while(litres<0) {
			System.out.println("Do you mean "+(-litres)+" for volume of lawwpaper?(Y/N)");
			String ch = MainClass.sc.next();
			if (ch.equals("Y")) {
				this.litres = -litres;
				return;
			}
			else if(ch.equals("N")) {
				System.out.print("Can't calculate with negative number! Enter a valid value: ");
				litres=MainClass.sc.nextFloat();
			}
		}
		this.litres=litres;
	}

	public float getPrice() {
		return price;
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
		br=(float)(S/(this.litres*1.2));
		BigDecimal brUp=new BigDecimal(br).setScale(0,RoundingMode.UP);
		count=brUp.intValue();
		return count;
	}
	
	float calculatePrice() {
		return calculateCount()*this.price;
	}
}
