package zad1;

public class Televisiors extends Stoki implements ElectricalAppliance{
	String proizvoditel;
	String model;
	double power;
	
	public Televisiors(double price, int num, String proizvoditel, String model, double power) throws PriceException, PowerException {
		super(price, num);
		setProizvoditel(proizvoditel);
		setModel(model);
		setPower(power);
	}

	public String getProizvoditel() {
		return this.proizvoditel;
	}

	public void setProizvoditel(String proizvoditel) {
		this.proizvoditel = proizvoditel;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPower() {
		return this.power;
	}

	public void setPower(double power) throws PowerException {
		if(power<=0) {
			throw new PowerException();
		}
		else
			this.power = power;
	}

	@Override
	public double checkPromo() {
		this.price=this.price-this.price*0.09;
		return this.price;
	}

	@Override
	public double calcPower() {
		return this.power*24;
	}
	
}
