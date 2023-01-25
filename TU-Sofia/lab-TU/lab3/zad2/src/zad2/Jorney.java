package zad2;

public abstract class Jorney {
	String destination;
	int days;
	double price;
	public Jorney(String destination, int days, double price) {
		setDestination(destination);
		setDays(days);
		setPrice(price);
	}
	public String getDestination() {
		return this.destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getDays() {
		return this.days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public double getPrice() {
		return this.price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public abstract double fixPrice();
}
