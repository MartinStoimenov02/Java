package zad2;

import java.util.Random;

public class MonsterFactory implements Monster {

	public void generate(){
        int []arr;
        arr=new int[5];
        int sum=0;
        for(int i=0; i<5; i++){
        	Random rand = new Random();
        	int num = rand.nextInt(3);
        	System.out.println(num+"\n");
            if(num==0){
                arr[i]=5;
            }
            else if(num==1){
                arr[i]=10;
            }
            else if(num==2){
                arr[i]=20;
            }
        }
        
        for(int i=0; i<arr.length; i++){
            sum+=arr[i];
        }

        if(sum<50){
            System.out.println("Pechelish! - "+sum);
        }
        else{
            System.out.println("Gubish! - "+sum);
        }

    }

	@Override
	public void attack() {
		System.out.println("Atack!");
	}
}
