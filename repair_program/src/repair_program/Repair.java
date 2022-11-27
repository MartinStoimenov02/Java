package repair_program;

public abstract class Repair {
	private float width, length, height;
	private float []win;
	private float []door;

	public Repair(float width, float length, float height, float []win, float []door) {
		setWidth(width);
		setLength(length);
		setHeight(height);
		setWin(win);
		setDoor(door);
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		while(width<0) {
			System.out.println("Do you mean "+(-width)+" for width?(Y/N)");
			String ch = MainClass.sc.next();
			if (ch.equals("Y")) {
				this.width = -width;
				return;
			}
			else if(ch.equals("N")) {
				System.out.print("Can't calculate with negative number! Enter a valid value: ");
				width=MainClass.sc.nextFloat();
			}
		}
		this.width=width;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		while(length<0) {
			System.out.println("Do you mean "+(-length)+" for Length?(Y/N)");
			String ch = MainClass.sc.next();
			if (ch.equals("Y")) {
				this.length = -length;
				return;
			}
			else if(ch.equals("N")) {
				System.out.print("Can't calculate with negative number! Enter a valid value: ");
				length=MainClass.sc.nextFloat();
			}
		}
		this.length=length;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		while(height<0) {
			System.out.println("Do you mean "+(-height)+" for Height?(Y/N)");
			String ch = MainClass.sc.next();
			if (ch.equals("Y")) {
				this.height = -height;
				return;
			}
			else if(ch.equals("N")) {
				System.out.print("Can't calculate with negative number! Enter a valid value: ");
				height=MainClass.sc.nextFloat();
			}
		}
		this.height=height;
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
	
	abstract float Calculate();
}
