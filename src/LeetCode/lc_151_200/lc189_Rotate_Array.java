package LeetCode.lc_151_200;

import LeetCode.util.Printer;

/**
 * Created by Tin on 8/4/16.
 */
public class lc189_Rotate_Array {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        rotate(arr, 3);
        Printer.printArray(arr);
    }
    /*
    O(N), O(1)
     */
    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        if((k%=len)==0) return;
        swapElements(nums, 0, len-1);
        swapElements(nums, 0, k-1);
        swapElements(nums, k, len-1);
    }
    private static void swapElements(int[] arr, int left, int right){
        while(left<right){
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;right--;
        }
    }
    /*
    O(KN), O(1), TLE
     */
    public static void rotate2(int[] nums, int k) {
        int len = nums.length;
        if((k%=len)==0) return;
        for(int i=0;i<k;i++){
            int temp = nums[len-1];
            for(int j=len-1;j>0;j--){
                nums[j] = nums[j-1];
            }
            nums[0] = temp;
        }
    }
    /*
    O(N), O(N)
     */
    public static void rotate1(int[] nums, int k) {
        int len = nums.length;
        if((k%=len)==0) return;
        int[] temp = new int[len];
        int op = 0;
        for(int i=len-k;i<len;i++) temp[op++] = nums[i];
        for(int i=0;i<len-k;i++) temp[op++] = nums[i];
        for(int i=0;i<len;i++) nums[i] = temp[i];
    }
}
