package LeetCode.lc_001_050;

import java.util.Arrays;

/**
 * Created by ytliu on 4/17/16.
 */
public class lc016_3Sum_Closest {
    public static void main(String[] args) {
        int[] nums = {-1,2,1,-4}; //{-4, -1, 1, 2}
        System.out.println(threeSumClosest(nums, 1));
    }
    public static int threeSumClosest(int[] nums, int target){
        int res = target;
        int minDiff = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            int left = i+1;
            int right = nums.length-1;
            int diffThisRound = Integer.MAX_VALUE;
            while(left<right){
                int sum = nums[i] + nums[left] + nums[right];
                int diff = Math.abs(sum-target);
                if(diff<minDiff){
                    minDiff = diff;
                    res = sum;
                }
                if(diff>diffThisRound) break;
                diffThisRound = diff;
                if(sum>target) right--;
                else left++;
            }
        }
        return res;
    }

}
