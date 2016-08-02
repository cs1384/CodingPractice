package LeetCode.lc_151_200;

/**
 * Created by Tin on 7/28/16.
 */
public class lc153_Find_Minimum_in_Rotated_Sorted_Array {
    public static void main(String[] args) {
        System.out.println(findMin(new int[]{4,5,6,7,0,1,2}));
        System.out.println(findMin(new int[]{1,2,3,4,5}));
        System.out.println(findMin(new int[]{2,3,4,5,1}));
        System.out.println(findMin(new int[]{3,4,5,1,2}));
        System.out.println(findMin(new int[]{4,5,1,2,3}));
        System.out.println(findMin(new int[]{5,1,2,3,4}));
    }
    /*
    From discussion, best solution
    The premise if that there's no duplicate
     */
    public static int findMin(int[] nums) {
        int low = 0, high = nums.length-1;
        while(low<high){
            int mid = (low+high)/2;
            // mid is always less than high unless high==low, which will not trigger the loop
            if(nums[high]>nums[mid]) high = mid;
            else low = mid+1;
        }
        return nums[low];
    }
    /*
    came up myself, workable
     */
    public static int findMin1(int[] nums) {
        int len = nums.length;
        int low = 0, high = len-1;
        while(low<high){
            int mid = (low+high)/2;
            int left = mid-1==-1?len-1:mid-1;
            int right = mid+1==len?0:mid+1;
            if(nums[mid]<nums[left]) return nums[mid];
            if(nums[mid]>nums[right]) return nums[right];
            if( mid+1<=high && nums[mid+1]<=nums[high]) high = mid - 1;
            else low = mid+1;
        }
        return nums[low];
    }
}
