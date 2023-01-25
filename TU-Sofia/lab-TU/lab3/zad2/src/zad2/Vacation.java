package zad2;

public class Vacation extends Jorney{
	String hotelName;
	
	public Vacation(String destination, int days, double price, String hotelName) {
		super(destination, days, price);
		setHotelName(hotelName);
	}
	
	public String getHotelName() {
		return this.hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	@Override
	public double fixPrice() {
		return this.price+this.price*0.4;
	}

}
