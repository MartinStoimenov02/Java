package zad1;

public class Books extends Stoki{
	String autor;
	String zaglavie;
	
	public Books(double price, int num, String autor, String zaglavie) throws PriceException {
		super(price, num);
		setAutor(autor);
		setZaglavie(zaglavie);
	}
	
	public String getAutor() {
		return this.autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getZaglavie() {
		return this.zaglavie;
	}
	public void setZaglavie(String zaglavie) {
		this.zaglavie = zaglavie;
	}

	@Override
	public double checkPromo() {
		this.price=this.price-this.price*0.15;
		return this.price;
	}
	
}
