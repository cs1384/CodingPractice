package LeetCode.lc_001_050;

/**
 * Created by ytliu on 6/4/16.
 */
public class lc035_Search_Insert_Position {
    public static void main(String[] args) {
        int[] arr1 = {1,3,5,6};
        System.out.println(searchInsert(arr1, 5));
        System.out.println(searchInsert(arr1, 2));
        System.out.println(searchInsert(arr1, 7));
        System.out.println(searchInsert(arr1, 0));
    }

    /**
     * Same as lc034 subroutine findEqualGreater()
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
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
}
