package LeetCode.lc_151_200;

/**
 * Created by Tin on 7/28/16.
 */
public class lc154_Find_Minimum_in_Rotated_Sorted_Array_II {
    public static void main(String[] args) {
//        System.out.println(findMin(new int[]{1,1,1,1,1,2,3,4,1}));
//        System.out.println(findMin(new int[]{3,3,3,3,3,1,3}));
//        System.out.println(findMin(new int[]{3,3,3,3,3,3,1}));
        System.out.println(findMin(new int[]{0,1,1,1,1,2,2}));
//        System.out.println(findMin(new int[]{1,1,1,1,1,1,1}));
    }
    public static int findMin(int[] nums) {
        int low = 0, high = nums.length-1;
        while(low<high){
            int mid = (low+high)/2;
            // right part is sorted and mid is min of right part, keep
            if(nums[high]>nums[mid]) high = mid;
            // left part is sorted but mid is max of left part, abandon
            else if(nums[mid]>nums[high]) low = mid+1;
            // if right part is not sorted (contains pivot), then removing
            // high is ok since if high is pivot, the mid is too, or
            // the pivot is in between mid and high. Same as left part. But we
            // still can't remove low because if both parts are actaully sorted
            // say [0,1,1,1,1,2,2], then low could be the min
            else high--;
        }
        return nums[low];
    }
    /*
    not working
     */
    public static int findMin1(int[] nums) {
        int len = nums.length-1;
        if (len == 1) return nums[0];
        return nums[findMinHelper1(nums, 0, len-1)];
    }
    private static int findMinHelper1(int[] nums, int from, int to){
        int len = nums.length;
        int mid = (from+to)/2;
        int left = mid-1==-1?len-1:mid-1;
        int right = mid+1==len?0:mid+1;
        if(nums[mid]<nums[left]) return mid;
        if(nums[mid]>nums[right]) return right;
        int index = -1;
        if(mid+1<len && nums[mid+1]>=nums[to]){
            index = findMinHelper1(nums, mid + 1, to);
        }
        if(index==-1 && mid-1>=0 && nums[from]>=nums[mid-1]){
            index = findMinHelper1(nums, from, mid - 1);
        }
        return index==-1?0:index;
    }

}
