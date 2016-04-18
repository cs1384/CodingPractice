package LeetCode.lc_001_050;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 4/16/16.
 */
public class lc015_3Sum {
    public static void main(String[] args) {
        Printer.printListList(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        Printer.printListList(threeSum(new int[]{0,2,2,3,0,1,2,3,-1,-4,2}));
    }
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2 && (0-nums[i])>=nums[i];i++){
            if(i>0 && nums[i]==nums[i-1]) continue;
            int need = 0-nums[i];
            int left = i+1;
            int right = nums.length-1;
            while(left<right) {
                int sum = nums[left] + nums[right];
                if (sum == need) {
                    List<Integer> list = new LinkedList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);
                    do left++; while (left < right && nums[left] == nums[left - 1]);
                    do right--; while (right > left && nums[right] == nums[right + 1]);
                } else if (sum < need) {
                    do left++; while (left < right && nums[left] == nums[left - 1]);
                } else {
                    do right--; while (right > left && nums[right] == nums[right + 1]);
                }
            }
        }
        return res;
    }
}
