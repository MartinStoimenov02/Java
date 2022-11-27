package zad4;

public class MultiProccessor {
	int count;
	int cores;
	float frequency;
	public MultiProccessor(int count, int cores, float frequency) {
		setCount(count);
		setCores(cores);
		setFrequency(frequency);
	}
	public int getCount() {
		return this.count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCores() {
		return this.cores;
	}
	public void setCores(int cores) {
		this.cores = cores;
	}
	public float getFrequency() {
		return this.frequency;
	}
	public void setFrequency(float frequency) {
		this.frequency = frequency;
	}
	
	public double Calculate() {
		return (0.7*getCores())*(0.8*getCount())*getFrequency();
	}
	
}
