package zad11;

import java.util.Arrays;

public class Main {
	static int index=0;
	
    public static void main(String[] args) {
        int []arr={1, 4, 9, 2, 6, 7, 3};
        arr=sortAndFilter(arr, 5);
        for(int i=0; i<index; i++){
            System.out.println(arr[i]);
        }
    }

    static int[] sortAndFilter(int[] array, int key){
        Arrays.sort(array);
        int []arr;
        arr=new int[array.length];
        for(int i=0; i<array.length; i++) {
        	if(array[i]>=key) {
        		break;
        	}
        	arr[index]=array[i];
        	index++;
        }
        return arr;
    }
}
