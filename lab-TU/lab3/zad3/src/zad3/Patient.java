package zad3;

public abstract class Patient {
	String name;
	String address;
	String egn;
	public Patient(String name, String address, String egn) {
		setName(name);
		setAddress(address);
		setEgn(egn);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getEgn() {
		return this.egn;
	}
	
	public void setEgn(String egn) {
		this.egn = egn;
	}
	
	public abstract void cure(String lekarstvo, int doza);
	
}
