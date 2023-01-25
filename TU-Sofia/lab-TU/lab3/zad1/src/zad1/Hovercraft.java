package zad1;

public class Hovercraft extends Vehicle implements IsLandVehicle{
	int count;	
	
	public Hovercraft(float maxspeed, String model, float price, int count) {
		super(maxspeed, model, price);
		setCount(count);
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public double checkPromo() {
		return this.price-0.03*this.price;
	}

	@Override
	public void enterLand() {
		System.out.println("It's land vehicle!");
		
	}
}
