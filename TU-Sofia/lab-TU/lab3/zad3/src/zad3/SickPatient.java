package zad3;

public class SickPatient extends Patient{
	String epikriza;
	String status;
	int day;
	
	public SickPatient(String name, String address, String egn, 
			String epikriza, String status, int day) {
		super(name, address, egn);
		setEpikriza(epikriza);
		setStatus(status);
		setDay(day);
	}
	
	public String getEpikriza() {
		return this.epikriza;
	}
	
	public void setEpikriza(String epikriza) {
		this.epikriza = epikriza;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getDay() {
		return this.day;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	@Override
	public void cure(String lekarstvo, int doza) {
		this.epikriza=this.epikriza+" lekarstvo: "+lekarstvo+" v doza: "+doza;
	}
	
	@Override
	public String toString() {
		return "SickPatient [name = "+this.name+"; address = "+this.address+"; "
				+ "egn = "+this.egn+"; epikriza=" + this.epikriza + ", "
						+ "status=" + this.status + ", day=" + this.day + "]";
	}
	
	
	
}
