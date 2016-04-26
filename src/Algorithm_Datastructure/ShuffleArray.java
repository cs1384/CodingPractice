package Algorithm_Datastructure;

import java.util.Random;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 4/25/16.
 */
public class ShuffleArray {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5};
        shuffleArray(arr);
        Printer.printArray(arr);
    }
    public static void shuffleArray(int[] arr){
        Random random = new Random();
        for(int i=arr.length-1;i>=0;i--){
            int index = random.nextInt(i+1);
            if(index!=i) swap(arr, i, index);
        }
    }
    private static void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
