package zad3;

public class Laptop extends Electric{
	float RAM;
	float HDD;
	public Laptop(float vIn, String model, float rAM, float hDD) {
		super(vIn, model);
		this.RAM = rAM;
		this.HDD = hDD;
	}
	public float getRAM() {
		return this.RAM;
	}
	public void setRAM(float rAM) {
		this.RAM = rAM;
	}
	public float getHDD() {
		return this.HDD;
	}
	public void setHDD(float hDD) {
		this.HDD = hDD;
	}
	
	public boolean isBetter(Laptop lp) {
		float hdd, ram;
		System.out.print("HDD: ");
		hdd=Test.sc.nextFloat();
		System.out.print("RAM: ");
		ram=Test.sc.nextFloat();
		if(lp.HDD>hdd && lp.RAM>ram) {
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public String toString() {
		return "Laptop [Model: "+model+", vIn: "+vIn+", RAM=" + RAM + ", HDD=" + HDD + "]";
	}
	
	
}
