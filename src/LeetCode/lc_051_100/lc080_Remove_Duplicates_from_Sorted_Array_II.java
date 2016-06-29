package LeetCode.lc_051_100;

import LeetCode.util.Printer;

/**
 * Created by Tin on 6/22/16.
 */
public class lc080_Remove_Duplicates_from_Sorted_Array_II {
    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,2,3,3};
        System.out.println(removeDuplicates(nums));
        Printer.printArray(nums);
    }
    public static int removeDuplicates(int[] nums) {
        int to = 1;
        boolean second = false;
        for(int i=1;i<nums.length;i++){
            if(nums[i]==nums[i-1] && second) continue;
            second = nums[i]==nums[i-1];
            if(i!=to) nums[to] = nums[i];
            to++;
        }
        return to;
    }
}
