package LeetCode.lc_001_050;

/**
 * Created by ytliu on 6/2/16.
 */
public class lc041_First_Missing_Positive {



    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{1,2,0}));
        System.out.println(firstMissingPositive(new int[]{3,4,-1,1}));

        System.out.println(firstMissingPositive(new int[]{2}));
        System.out.println(firstMissingPositive(new int[]{1,-2}));
        System.out.println(firstMissingPositive(new int[]{1,1}));
        System.out.println(firstMissingPositive(new int[]{3,4,1,-1,1}));
    }
    /* thinking process
    1. Would like to sort first, but it requires O(NlogN)
    2. Iterate the indices. In each iteration, iterate all left elements to get smallest to put it at current index -> O(N^2)
    3. Have a tracker[], iterate the nums and change corresponding tracker index -> O(N), O(N)
    4. the positive value itself actaully tells its position. so we can use nums array itself as a tracker!
    5. for duplicates, we just don't do swap to avoid infinite loop, but we don't really care where the duplicate will be placed since
    we just need to make sure an index has the right correponding element on it.
    */
    public static int firstMissingPositive(int[] nums) {
        int i = 0;
        while(i<nums.length){
            // if we have the right element at this index or this index is waiting to be swapped
            if(nums[i]==i+1 || nums[i]<=0) i++;
            // if swap target is in the range (ex. {2} not in the ragne) and no duplicate (ex. {1,1})
            else if(nums[i]-1<nums.length && nums[nums[i]-1]!=nums[i]) swap(nums, i, nums[i]-1);
            else i++;
        }
        i = 0;
        while(i<nums.length && nums[i]==i+1) i++;
        return i+1;

    }
    private static void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
