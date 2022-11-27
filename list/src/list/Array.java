package list;

public class Array {
	private int []arr;
	private int n;
	private int currentIndex=-1;
	
	public Array(int n) {
		this.n = n;
		arr=new int [this.n];
		Input();
	}
	
	private void Input() {
		for(int i=0; i<this.n; i++) {
			arr[i]=i*2;
		}
	}
	
	public int next() {
		return arr[++currentIndex];
	}
	
	public boolean hasNext() {
		return currentIndex+1<this.n;
	}
	
}
