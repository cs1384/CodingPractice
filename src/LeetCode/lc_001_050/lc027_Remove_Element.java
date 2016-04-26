package LeetCode.lc_001_050;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 4/25/16.
 */
public class lc027_Remove_Element {
    public static void main(String[] args) {

//        int[] nums = new int[]{3,2,2,3};
//        System.out.println(removeElement(nums, 3));
//        Printer.printArray(nums);
//
//        int[] nums1 = new int[]{3};
//        System.out.println(removeElement(nums1, 3));
//        Printer.printArray(nums1);
//
//        int[] nums2 = new int[]{2};
//        System.out.println(removeElement(nums2, 3));
//        Printer.printArray(nums2);
//
//        int[] nums3 = new int[]{0,4,4,0,4,4,4,0,2};
//        System.out.println(removeElement(nums3, 4));
//        Printer.printArray(nums3);

        int[] nums4 = new int[]{4,2,0,2,2,1,4,4,1,4,3,2};
        System.out.println(removeElement(nums4, 4));
        Printer.printArray(nums4);

    }
    public static int removeElement(int[] nums, int val) {
        int len = nums.length;
        if(len==0) return 0;
        int swapindex = len-1;
        while(swapindex>=0 && nums[swapindex]==val) swapindex--;
        int i = 0;
        int res = 0;
        while(i<=swapindex){
            if(nums[i]==val) swap(nums, i, swapindex);
            while(nums[swapindex]==val) swapindex--;
            if(nums[i++]!=val) res++;
        }
        return res;
    }
    private static void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    public static int removeElement2(int[] A, int elem) {
        int index = A.length-1;
        for(int i=0;i<=index;i++){
            while(A[i]==elem){
                swap(A, i, index);
                index--;
                if(index<0 || i>index)
                    break;
            }
        }
        return index+1;
    }
}

