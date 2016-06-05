package LeetCode.lc_001_050;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 6/4/16.
 */
public class lc034_Search_for_a_Range {
    public static void main(String[] args) {
        int[] arr1 = {1,1,2,2,3,3};
        System.out.println(firstEqualGreater(arr1, 1));
        System.out.println(firstEqualGreater(arr1, 2));
        System.out.println(firstEqualGreater(arr1, 4));
        Printer.printArray(searchRange(arr1, 1));
        Printer.printArray(searchRange(arr1, 2));
        Printer.printArray(searchRange(arr1, 3));

        System.out.println(firstEqualGreater(new int[]{1}, 0));
        Printer.printArray(searchRange(new int[]{1}, 0));

        Printer.printArray(searchRange(new int[]{2,2}, 3));
    }
    public static int[] searchRange(int[] nums, int target) {
        int start = firstEqualGreater(nums, target);
        if(start==-1 || start>=nums.length || nums[start]!=target) return new int[]{-1,-1};
        else return new int[]{start, firstEqualGreater(nums, target+1)-1};
    }
    private static int firstEqualGreater(int[] nums, int target){
        int low = 0;
        int high = nums.length-1;
        if(target>nums[high]) return nums.length;
        while(low<=high){
            int mid = (low+high)/2;
            if(nums[mid]>=target && (mid==0 || nums[mid-1]<target)){
                return mid;
            }else if(nums[mid]<target){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return -1;
    }
    /**
     * you have to find first and last occurrence of target. if cant, return default answer {-1,-1}
     *
     */
    public int[] searchRange1(int[] A, int target) {
        int[] result = {-1,-1};
        int low = 0;
        int high = A.length-1;
        int mid;
        while(low<=high){ //to allow case of one element
            mid = (low+high)/2;
            if(A[mid]>target){
                high = mid-1;
            }else if(A[mid]<target){
                low = mid+1;
            }else{ //A[mid]==target, dealing with independent == case would be easier.
                if(mid==0 || A[mid-1]<target){
                    result[0] = mid;
                    break; //either end the loop
                }else{
                    high = mid-1; //or procceed
                }
            }
        }
        low = 0;
        high = A.length-1;
        while(low<=high){
            mid = (low+high)/2;
            if(A[mid]>target){
                high = mid-1;
            }else if(A[mid]<target){
                low = mid+1;
            }else{ //A[mid]==target
                if(mid==A.length-1 || A[mid+1]>target){
                    result[1] = mid;
                    break;
                }else{
                    low = mid+1;
                }
            }
        }
        return result;
    }

}
