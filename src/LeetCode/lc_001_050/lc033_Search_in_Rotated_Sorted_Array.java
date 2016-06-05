package LeetCode.lc_001_050;

/**
 * Created by ytliu on 5/31/16.
 */
public class lc033_Search_in_Rotated_Sorted_Array {
    public static void main(String[] args) {
        System.out.println(search(new int[]{0,1,2,4,5,6,7}, 5));//4
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 5));//1
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 7));//3
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 0));//4
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 2));//6
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 4));//0
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 3));//-1
        System.out.println(search(new int[]{5,1,3}, 5));//0
        System.out.println(search(new int[]{1,2,3,4,5,6}, 1));//0
        System.out.println(search(new int[]{2,3,4,5,6,7,8,9,1}, 9));//7
    }
    public static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        // make sure there are three elements
        while(high-low>1){
            int mid = (low+high)/2;
            if(nums[mid]==target) return mid;
            // find where is the pivot
            // pivot is at mid
//            if(nums[low]<=nums[mid-1] && nums[mid+1]<=nums[high]){
//                if(nums[low]<=target && target<=nums[mid-1]) high = mid-1;
//                else low = mid+1;
//            }else
            // pivot is on the right of mid
            if(nums[low]<=nums[mid-1]){
                if(nums[low]<=target && target<=nums[mid-1]) high = mid-1;
                else low = mid+1;
            // pivot is on the left of mid
            }else{
                if(nums[mid+1]<=target && target<=nums[high]) low = mid+1;
                else high = mid-1;
            }
        }
        if(nums[low]==target) return low;
        if(nums[high]==target) return high;
        return -1;
    }
}
