package LeetCode.lc_001_050;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 5/1/16.
 */
public class lc031_Next_Permutation {
    public static void main(String[] args) {
//        int[] nums1 = new int[]{1,2,3};
//        nextPermutation(nums1);
//        Printer.printArray(nums1);
//        int[] nums2 = new int[]{3,2,1};
//        nextPermutation(nums2);
//        Printer.printArray(nums2);
//        int[] nums3 = new int[]{1,1,5};
//        nextPermutation(nums3);
//        Printer.printArray(nums3);
        int[] nums4 = new int[]{1,5,1};
        nextPermutation(nums4);
        Printer.printArray(nums4);
    }
    public static void nextPermutation(int[] nums) {
        int len = nums.length;
        // find the decreased one, which will be swapped for next permutation
        int index = len-2;
        while(index>=0){
            if(nums[index]<nums[index+1]) break;
            index--;
        }
        // find the swapping target, and swap it
        if(index!=-1){
            int nextHead = len-1;
            while(nums[nextHead]<=nums[index]) nextHead--;
            swap(nums, index, nextHead);
        }
        // the elements on the right need to be in ascending order. Since they were in descending, just reserve them
        reverse(nums, index+1, len-1);

    }
    private static void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
    private static void reverse(int[] nums, int start, int end){
        while(start<end){
            swap(nums, start ,end);
            start++;
            end--;
        }
    }
}
