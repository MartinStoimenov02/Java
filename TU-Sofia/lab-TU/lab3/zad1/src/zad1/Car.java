package zad1;

public class Car extends Vehicle implements IsLandVehicle{
	float hp;	
	
	public Car(float maxspeed, String model, float price, float hp) {
		super(maxspeed, model, price);
		setHp(hp);
	}
	
	public float getHp() {
		return this.hp;
	}

	public void setHp(float hp) {
		this.hp = hp;
	}

	@Override
	public double checkPromo() {
		return this.price-0.2*this.price;
	}

	@Override
	public void enterLand() {
		System.out.println("It's land vehicle!");
		
	}
	
}
