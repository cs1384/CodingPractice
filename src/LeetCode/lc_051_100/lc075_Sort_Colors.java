package LeetCode.lc_051_100;

import LeetCode.util.Printer;

/**
 * Created by Tin on 6/20/16.
 */
public class lc075_Sort_Colors {
    public static void main(String[] args) {
        int[] nums = {0,1,2,2,1,1,2,2,1,1,1,1,1};
        sortColors(nums);
        Printer.printArray(nums);
    }
    /*
    Naive way is two-passes. First to know the count of each color and then second to fill them up
    One pass solution is to swap them to the head/rear along the way
     */
    public static void sortColors(int[] nums) {
        int head = 0, rear = nums.length-1;
        int i = 0;
        while(i<=rear){
            if(nums[i]==0){
                swap(nums, i, head);
                if(i==head) i++;
                head++;
            }else if(nums[i]==2){
                swap(nums, i, rear);
                rear--;
            }else{
                i++;
            }
        }
    }
    private static void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
