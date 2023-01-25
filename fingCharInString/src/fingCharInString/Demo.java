package fingCharInString;

import java.util.Scanner;

public class Demo {
	   public static void main(String []args) {
		  Scanner sc = new Scanner(System.in);
	      String str;
	      System.out.print("string: ");
	      str=sc.next();
	      System.out.println("String: "+str);
	      char ch;
	      System.out.print("char: ");
	      ch=sc.next().charAt(0);
	      int index = str.indexOf(ch);
	      if(index<0) {
	    	  System.out.println("There is no '"+ch+"' in this String!");
	      }
	      else {
	    	  System.out.println("'"+ch+"' is at index "+index);
	      }
	   }
	}