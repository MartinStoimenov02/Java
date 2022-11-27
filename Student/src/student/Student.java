package student;

class Student { 
	private int rollNo; 
	private String name; 
	private int []math;
	private int []bg;
	Student(int rollNo, String name, int []math, int []bg)
	{
		setBg(bg);
		setMath(math);
		this.rollNo=rollNo;
		this.name=name;
	}
	
	public int getRollNo() {
		return this.rollNo;
	}
	
	public String getName() {
		return this.name;
	}

	public int[] getMath() {
		return math;
	}

	public int[] getBg() {
		return bg;
	}

	public void setMath(int[] math) {
		if(math.length==0) {
			System.out.println("Error!");
			return;
		}
		this.math = math;
	}
	
	public void setBg(int[] bg) {
		this.bg = bg;
	}	
} 