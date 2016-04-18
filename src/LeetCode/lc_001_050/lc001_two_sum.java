package LeetCode.lc_001_050;

import java.util.HashMap;
import java.util.Map;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 3/3/16.
 */
public class lc001_two_sum {
    public static void main(String[] args) {
        Printer.printArray(twoSum(new int[]{2, 7, 11, 15}, 9));//[0,1]
    }
    // use map to track the index of required diff -> time:O(N) space:O(N)
    // two pointers from head and tail -> time:O(N) space:O(1)
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numToIndex = new HashMap<>();
        int[] res = new int[2];
        for(int i=0;i<nums.length;i++){
            int need = target - nums[i];
            if(numToIndex.containsKey(need)){
                res[0] = numToIndex.get(need);
                res[1] = i;
                break;
            }
            numToIndex.put(nums[i],i);
        }
        return res;
    }
}
