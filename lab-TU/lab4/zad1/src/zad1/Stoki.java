package zad1;

public abstract class Stoki {
	double price;
	int num;
	public Stoki(double price, int num) throws PriceException {
		setPrice(price);
		setNum(num);
	}
	public double getPrice() {
		return this.price;
	}
	public void setPrice(double price) throws PriceException {
		if(price<=0) {
			throw new PriceException(); 
		}
		else {
			this.price = price;
		}
	}
	
	public int getNum() {
		return this.num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public abstract double checkPromo();

}
