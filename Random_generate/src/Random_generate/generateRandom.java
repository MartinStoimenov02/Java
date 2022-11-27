package Random_generate;
import java.util.Formatter;
// A Java program to demonstrate random number generation 
// using java.util.Random; 
import java.util.Random; 
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
  
public class generateRandom{ 
  
	public static final int CONST=2;
	
    public static void main(String args[]){ 
        // create instance of Random class 
        Random rand = new Random(); 
  
        // Generate random integers in range 0 to 999 
        int rand_int1 = rand.nextInt(100);  
  
        // Print random integers 
        System.out.println("Random Integers: "+rand_int1); 
        System.out.println("Random Integers: "+rand_int1+CONST); 
        System.out.println("Random Integers: "+(rand_int1+CONST)); 
        System.out.println("Random Integers: "+rand_int1*CONST); 
  
        // Generate Random doubles 
        double rand_dub1 = rand.nextDouble(10);
  
        // Print random doubles 
        System.out.println("Random Doubles: "+rand_dub1); 
        System.out.println("Random Doubles: "+rand_dub1/CONST); 
        System.out.println("Double upto 2 decimal places: "+String.format("%.2f",rand_dub1));
        System.out.printf("Double upto 2 decimal places: %.2f\n",rand_dub1);
        Formatter formatter = new Formatter();
        formatter.format("%.2f", rand_dub1);
        
        System.out.println("Double upto 2 decimal places: " + formatter.toString());
        BigDecimal bd=new BigDecimal(rand_dub1).setScale(2,RoundingMode.HALF_DOWN);
        System.out.println("Double upto 2 decimal places: "+bd.doubleValue());
 
        // You can use RoundingMode to round double Up or Down
        BigDecimal bdDown=new BigDecimal(rand_dub1).setScale(2,RoundingMode.DOWN);
        System.out.println("Double upto 2 decimal places - RoundingMode.DOWN: "+bdDown.doubleValue());
 
        BigDecimal bdUp=new BigDecimal(rand_dub1).setScale(2,RoundingMode.UP);
        System.out.println("Double upto 2 decimal places - RoundingMode.UP: "+bdUp.doubleValue());
    } 
}