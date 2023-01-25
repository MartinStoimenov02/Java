package za2d2;

public class House extends Building {
	int brEt;
	String name;
	
	public House(int height, double plosht, String adress, int brEt, String name) throws EtazhException {
		super(height, plosht, adress);
		setBrEt(brEt);
		setName(name);
	}

	public int getBrEt() {
		return this.brEt;
	}

	public void setBrEt(int brEt) throws EtazhException {
		if(brEt<1) {
			throw new EtazhException();
		}
		this.brEt = brEt;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int prostorna(House []arr) {
		double max=0;
		int index=0;
		for(int i=0; i<arr.length; i++) {
			if(arr[i].plosht>max) {
				max=plosht;
				index=i;
			}
		}
		return index;
	}
}
