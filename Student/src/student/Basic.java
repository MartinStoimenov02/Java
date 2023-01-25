package student;

import java.util.Scanner;

public class Basic {
	public Scanner keyboard=new Scanner(System.in);
	public Student[] arr;
	public static void main(String[] args) {
		Basic obj=new Basic();
		obj.input();
	}
		
	
	public void input() {
		int n, b, m, nom;
		String name;
		int []math;
		int []bg;
		System.out.print("Number of Students: ");
		n=keyboard.nextInt();
		arr = new Student[n];
		for(int i=0; i<n; i++) {
			System.out.print("num of "+(i+1)+" student: ");
			nom=keyboard.nextInt();
			System.out.print("name of "+(i+1)+" student: ");
			name=keyboard.next();
			System.out.print("number of math grades: ");
			m=keyboard.nextInt();
			math=new int[m];
			for(int j=0; j<m; j++) {
				System.out.print((j+1)+" math grade: ");
				math[j]=keyboard.nextInt();
			}
			System.out.print("number of bg grades: ");
			b=keyboard.nextInt();
			bg=new int[b];
			for(int j=0; j<b; j++) {
				System.out.print((j+1)+" bg grade: ");
				bg[j]=keyboard.nextInt();
			}
			arr[i]=new Student(nom, name, math, bg);
		}
		print_Result();
	}
	
	public void print_Result() {
		for (int i = 0; i < arr.length; i++) {
			System.out.print("Element at " + i + " : "+ arr[i].getRollNo() + " "+ arr[i].getName()+" math grades: ");
			for(int j : arr[i].getMath()) {
				System.out.print(j+" ");
			}
			
			System.out.print(";bg grades: "); 
			for(int j : arr[i].getBg()) {
				System.out.print(j+" ");
			}
			System.out.println();
		}
	}
}
