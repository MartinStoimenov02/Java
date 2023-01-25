package arrayGlobal;

import java.util.Arrays;
import java.util.Scanner;

public class ProcessingClass {
	int[] array=BasicClass.array;
	
	public void addArr() {
		Scanner keyboard = new Scanner(System.in);
		for(int i=0; i<array.length; i++) {
			array[i]=keyboard.nextInt();
		}
		keyboard.close();
	}
	
	public void sort() {
		Arrays.sort(array);
	}
	
	public int min() {
		int min=0;
		for(int x : array) {
			if (min>x) {
				min=x;
			}
		}
		return min;
	}
	
	public int max() {
		int max=0;
		for(int x : array) {
			if (max<x) {
				max=x;
			}
		}
		return max;
	}
	
	public void printEl() {
		for(int i=0; i<array.length; i++) {
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}
}
