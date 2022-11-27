package zad3;

import java.util.Scanner;

public abstract class Test {
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Laptop lt = new Laptop(12, "HP", 8, 1000);
		Laptop lt1 = new Laptop(12, "Lenovo", 4, 256);
		Laptop lt2 = new Laptop(5, "Mac", 16, 1024);
		Laptop []arr = {lt, lt1, lt2};
		for(int i=0; i<arr.length; i++) {
			System.out.println(arr[i].toString());
			if(arr[i].isBetter(arr[i])==true) {
				System.out.println("This laptop is better than your input values!");
			}
			else {
				System.out.println("This laptop isn't better than your input values!");
			}
			
		}
	}

}
