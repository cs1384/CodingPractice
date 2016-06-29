package LeetCode.lc_201_250;

import LeetCode.util.Printer;

/**
 * Created by Tin on 6/27/16.
 */
public class lc238_Product_of_Array_Except_Self {
    public static void main(String[] args) {
        Printer.printArray(productExceptSelf(new int[]{1,2,3,4}));
        Printer.printArray(productExceptSelf(new int[]{1,4,0}));
        Printer.printArray(productExceptSelf(new int[]{0,0}));
        Printer.printArray(productExceptSelf(new int[]{0,1}));
    }
    public static int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] output = new int[len];
        int product = 1;
        int nZero = 0;
        for(int i=0;i<len;i++){
            if(nums[i]==0) nZero++;
            else product *= nums[i];
        }
        for(int i=0;i<len;i++){
            if(nums[i]==0) output[i] = nZero>1?0:product;
            else output[i] = nZero>0?0:product/nums[i];
        }
        return output;
    }
}
