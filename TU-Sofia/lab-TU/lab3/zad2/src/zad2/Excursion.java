package zad2;

public class Excursion extends Jorney implements daysCount{
	int hotels;
		
	public Excursion(String destination, int days, double price, int hotels) {
		super(destination, days, price);
		setHotels(hotels);
	}

	public int getHotels() {
		return this.hotels;
	}

	public void setHotels(int hotels) {
		this.hotels = hotels;
	}

	@Override
	public double fixPrice() {
		return this.price+this.price*0.1;
	}

	@Override
	public int daysCount() {
		return this.days-2;
	}
	
}
