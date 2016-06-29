package LeetCode.lc_051_100;

/**
 * Created by Tin on 6/22/16.
 */
public class lc081_Search_in_Rotated_Sorted_Array_II {
    public static void main(String[] args) {
//        System.out.println(search(new int[]{0,1,2,2,2,2,4,5,6,7}, 2));
//        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 3));
        System.out.println(search(new int[]{1,3,1,1,1,1}, 3));
    }
    public static boolean searchWrong(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        while(low<=high){
            int mid = (low+high)/2;
            if(nums[mid]==target) return true;
            if(nums[low]<=nums[mid]){
                if(nums[low]<=target && target<nums[mid]){
                    high = mid-1;
                }else{
                    low = mid+1;
                }
            }else{
                if(nums[mid]<target && target<=nums[high]){
                    low = mid+1;
                }else{
                    high = mid-1;
                }
            }
        }
        return false;
    }
    public static boolean search(int[] nums, int target) {
        return searchhelper(nums, 0, nums.length-1, target);
    }
    private static boolean searchhelper(int[] nums, int low, int high, int target){
        if(low>high) return false;
        int mid = (low+high)/2;
        if(nums[mid]==target) return true;
        // in this case, we don't know which part is sorted
        if(nums[low]==nums[mid] && nums[mid]==nums[high]){
            return searchhelper(nums, low, mid-1, target) ||
                    searchhelper(nums, mid+1, high, target);
        }
        // left part is sorted
        if(nums[low]<=nums[mid]){
            if(nums[low]<=target && target<nums[mid]){
                return searchhelper(nums, low, mid-1, target);
            }else{
                return searchhelper(nums, mid+1, high, target);
            }
        // right part is sorted
        }else{
            if(nums[mid]<target && target<=nums[high]){
                return searchhelper(nums, mid+1, high, target);
            }else{
                return searchhelper(nums, low, mid-1, target);
            }
        }
    }
}
