package zad1;

public class Ship extends Vehicle implements isSeaVessel{
	float deadweight;

	public Ship(float maxspeed, String model, float price, float deadweight) {
		super(maxspeed, model, price);
		setDeadweight(deadweight);
	}

	public float getDeadweight() {
		return this.deadweight;
	}

	public void setDeadweight(float deadweight) {
		this.deadweight = deadweight;
	}

	@Override
	public double checkPromo() {
		return this.price-0.003*this.price;
	}

	@Override
	public void enterSea() {
		System.out.println("It's sea vessel!");		
	}
}
