package LeetCode.lc_001_050;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 6/8/16.
 */
public class lc045_Jump_Game_II {
    public static void main(String[] args) {
        System.out.println(jump(new int[]{2,3,1,1,4}));

        //self test
        System.out.println(jump(new int[]{2,2,2,1,4}));
        //extreme cases
        System.out.println(jump(new int[]{5,4,3,2,1}));
        System.out.println(jump(new int[]{1,1,1,1,1}));
        System.out.println(jump(new int[]{0}));
    }

    public static int jump(int[] nums) {
        int len = nums.length;
        if(len==1) return 0;
        int nStep = 0;
        int reach = 0;
        int nextReach = 0;
        for(int i=0;i<len;i++){
            if(reach<i){
                reach = nextReach;
                nStep++;
            }
            nextReach = Math.max(nextReach, i+nums[i]);
            if(nextReach>=len-1) return nStep+1;
        }
        return -1;
    }
    /**
     * passed but slow O(n)
     * @param nums
     * @return
     */
    public static int jump2(int[] nums) {
        int len = nums.length;
        if(len==1) return 0;
        Map<Integer, Integer> nStep_reach = new HashMap<>();
        nStep_reach.put(0,0);
        int nStep = 0;
        int i = 0;
        while(i<len){
            if(i<=nStep_reach.get(nStep)){
                if(!nStep_reach.containsKey(nStep+1)) nStep_reach.put(nStep+1, -1);
                int reach = i+nums[i];
                if(reach>=len-1) return nStep+1;
                int curMax = nStep_reach.get(nStep+1);
                nStep_reach.put(nStep+1, Math.max(reach, curMax));
                i++;
            }else{
                nStep++;
            }
        }
        return len-1;
     }
    /**
     * basic dp thinking, but {25000, 24999, 24998, ...., 1} will TLE
     */
    public static int jump1(int[] nums) {
        int len = nums.length;
        int[] minimums = new int[len];
        Arrays.fill(minimums, len);
        minimums[0] = 0;
        for(int i=0;i<len;i++){
            for(int j=1;j<=nums[i]&&(i+j)<len;j++){
                minimums[i+j] = Math.min(minimums[i+j], minimums[i]+1);
            }
        }
        return minimums[len-1];
    }
}
