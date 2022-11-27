package string;

public class Basic {
    public static void main(String args[]) {
    	
	    String str = "i am good";
	    
	    char []ch;
    	ch=new char[str.length()];
	    
	    for(int i=0; i<str.length(); i++) {
	    	System.out.print(str.charAt(i)+" ");
	    }
	    System.out.println();
	    
	    ch=str.toCharArray();
	    
	    for(char x:ch) {
	    	System.out.print(x);
	    }
	    
	    System.out.println();
	    
	 // capitalize all letters
	    System.out.println(str.toUpperCase());

     // capitalize first letter
	     String output = str.substring(0, 1).toUpperCase() + str.substring(1);
	     System.out.println(output);

	 // reverse words in sentence
	     String rev = reverse(str);
	     System.out.println("Reversed sentence: " + rev);
	     
	 // reverse each word in sentence
	     System.out.println(Basic.reverseWord(str));  
	     
	     
     //reverse sentence
	     String reversed = reverseString(str);  
	     System.out.println("The reversed string is: " + reversed);  
	     }  
    
	     public static String reverseString(String s)  
	     {  
	     if (s.isEmpty())                            //checks the string if empty  
	    	 return s;  
	     return reverseString(s.substring(1)) + s.charAt(0);                     //recursively called function  
	     }  


    
    public static String reverse(String s) {
        // Finding the index of the whitespaces
        int x = s.indexOf(" ");
        
        //Base condition
        if(x == -1)
          return s;
          
         //Recursive call
        return reverse(s.substring(x+1)) +" "+ s.substring(0, x);
         }
    
    public static String reverseWord(String str){  
        String words[]=str.split("\\s");  
        String reverseWord="";  
        for(String w:words){  
            StringBuilder sb=new StringBuilder(w);  
            sb.reverse();  
            reverseWord+=sb.toString()+" ";  
        }  
        return reverseWord.trim();  
    }  
}