package zad3;

public class Electric {
	float vIn;
	String model;
	
	public Electric(float vIn, String model) {
		setvIn(vIn);
		setModel(model);
	}

	public float getvIn() {
		return this.vIn;
	}

	public void setvIn(float vIn) {
		this.vIn = vIn;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public int deviceOn() {
		return 1;
	}
	
	public int deviceOff() {
		return 0;
	}

	@Override
	public String toString() {
		return "Electric [vIn=" + vIn + ", model=" + model + "]: ";
	}
	
}
