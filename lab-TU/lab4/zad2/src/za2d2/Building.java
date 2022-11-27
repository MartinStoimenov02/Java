package za2d2;

public abstract class Building {
	int height;
	double plosht;
	String adress;
	
	public Building(int height, double plosht, String adress) {
		setHeight(height);
		setPlosht(plosht);
		setAdress(adress);
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getPlosht() throws PloshtException {
		if(plosht<=0) {
			throw new PloshtException();
		}
		else
			return this.plosht;
	}

	public void setPlosht(double plosht) {
		this.plosht = plosht;
	}

	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	
	
}
