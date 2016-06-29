package LeetCode.lc_051_100;

import LeetCode.util.Printer;

/**
 * Created by Tin on 6/17/16.
 */
public class lc066_Plus_One {
    public static void main(String[] args) {
        Printer.printArray(plusOne(new int[]{9,9,6,9,9}));
        Printer.printArray(plusOne(new int[]{9,9,9,9,9}));
    }
    public static int[] plusOne(int[] digits) {
        int len = digits.length;
        int op = len-1;
        while(op>=0 && digits[op]==9) op--;
        if(op==-1){
            int[] res = new int[len+1];
            res[0] = 1;
            return res;
        }else{
            int[] res = new int[len];
            res[op] = digits[op]+1;
            while(--op>=0) res[op] = digits[op];
            return res;
        }
    }
}
