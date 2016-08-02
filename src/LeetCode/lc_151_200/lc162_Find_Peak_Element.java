package LeetCode.lc_151_200;

/**
 * Created by Tin on 7/29/16.
 */
public class lc162_Find_Peak_Element {
    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1,2,3,1}));
    }
    public static int findPeakElement(int[] nums) {
        int len = nums.length;
        int low = 0, high = len-1;
        while(low<=high){
            int mid = (low+high)/2;
            int num = nums[mid];
            int left = mid-1>=0?nums[mid-1]:Integer.MIN_VALUE;
            int right = mid+1<len?nums[mid+1]:Integer.MIN_VALUE;
            // if either side if greater than current index, here is not a peak,
            // and the peak is garanteed to be occurred on a hill
            if(left>num) high = mid-1;
            else if(right>num) low = mid+1;
            // given the condition that nums[i]=/=nums[i+1] (no neighboring same
            // number), we can return mid directly here.
            else return mid;
        }
        return -1;
    }
}
