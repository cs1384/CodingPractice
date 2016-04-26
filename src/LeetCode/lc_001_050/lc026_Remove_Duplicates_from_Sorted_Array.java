package LeetCode.lc_001_050;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 4/25/16.
 */
public class lc026_Remove_Duplicates_from_Sorted_Array {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2};
        System.out.println(removeDuplicates(nums));
        Printer.printArray(nums);
    }
    public static int removeDuplicates(int[] nums) {
        if(nums.length==0) return 0;
        int progress = nums[0];
        int swapIndex = 1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]>progress){
                progress = nums[i];
                if(swapIndex!=i) swap(nums, i, swapIndex);
                swapIndex++;
            }
        }
        return swapIndex;
    }
    private static void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
