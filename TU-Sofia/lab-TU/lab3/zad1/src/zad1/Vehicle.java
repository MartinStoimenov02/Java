package zad1;

public abstract class Vehicle {
	float maxspeed;
	String model;
	float price;
	public Vehicle(float maxspeed, String model, float price) {
		setMaxspeed(maxspeed);
		setModel(model);
		setPrice(price);
		
	}
	public float getMaxspeed() {
		return this.maxspeed;
	}
	public void setMaxspeed(float maxspeed) {
		this.maxspeed = maxspeed;
	}
	public String getModel() {
		return this.model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public float getPrice() {
		return this.price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public abstract double checkPromo();
	
}
