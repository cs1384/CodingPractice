package Interview;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Tin on 12/7/15.
 */
public class Wayfair {
    public static void printArray(int[] a){
        System.out.print("[");
        for(int i : a)
            System.out.print(i+", ");
        System.out.println("]");
    }
    public static void main(String[] args){
        StringBuilder sb = new StringBuilder();
        printArray(maxSubarray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
    public static int[] maxSubarray(int[] arr){
        int len = arr.length;
        int[] dp = new int[arr.length];
        int index = 0;
        int newsum = arr[index];
        int max = arr[index];
        for(int i=1;i<len;i++){
            newsum=Math.max(newsum+arr[i],arr[i]);
            if(newsum>max){
                max = newsum;
                index = i;
            }
        }
        int op = 0;
        for(int i=index;i>=0;i--){
            op += arr[i];
            if(op==max) return Arrays.copyOfRange(arr, i, index+1);
        }
        return new int[0];
    }
}
