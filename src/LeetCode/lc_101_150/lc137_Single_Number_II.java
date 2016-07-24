package LeetCode.lc_101_150;

import LeetCode.util.Printer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tin on 7/23/16.
 */
public class lc137_Single_Number_II {
    public static void main(String[] args) {
        System.out.println(singleNumber4(new int[]{8, 2, 8, 8}));
//        System.out.println(singleNumber2(new int[]{-2,-2,-4,-2}));
        System.out.println(singleNumber5(new int[]{8, 8, 8, 8, 2}));
    }
    public static int singleNumber5(int[] nums) {
        int one = 0, two = 0, three = 0;
        for(int num : nums){
            one = one ^ num & ~three & ~two;
            two = two ^ num & ~one & ~three;
            three = three ^ num & ~two & ~one;
        }
        return one;
    }
    /*
    https://discuss.leetcode.com/topic/2031/challenge-me-thx/18
    See general approach in replying discussion
     */
    public static int singleNumber4(int[] nums) {
        int firstApear = 0, secondApear = 0;
        for(int num : nums){
            // & ~secondApear: If bit sets(appears) in secondApear,
            // this is third appearance -> not set in first.
            // If not set, this is first appearance.
            firstApear = firstApear ^ num & ~secondApear;
            // & ~firstApear: If bit sets(appears) in firstAppear, this is
            // second appearance -> set bit in second. If not, this is first
            secondApear = secondApear ^ num & ~firstApear;
        }
        return firstApear;
    }
    /*
    Use res as count[] directly
     */
    public static int singleNumber3(int[] nums) {
        int res = 0;
        for(int i=0;i<32;i++){
            int mask = 1<<i;
            int count = 0;
            for(int num : nums){
                if((num&mask)!=0) count++;
            }
            if(count%3!=0) res ^= mask;
        }
        return res;
    }
    /*
    http://fisherlei.blogspot.com/2013/11/leetcode-single-number-ii-solution.html
     */
    public static int singleNumber2(int[] nums) {
        int[] count = new int[32];
        for(int num : nums) {
            for (int i = 0; i < 32; i++) {
                if ((num & (1 << i)) != 0) count[i]++;
            }
        }
        int res = 0;
        for(int i=0;i<count.length;i++) {
            if (count[i] % 3 != 0) res ^= (1 << i);
        }
        return res;
    }
    /*
    Basica space O(N) solution
     */
    public static int singleNumber1(int[] nums) {
        Map<Integer, Integer> numToCount = new HashMap<>();
        for(int num :nums){
            if(!numToCount.containsKey(num)) numToCount.put(num, 1);
            else numToCount.put(num, numToCount.get(num)+1);
        }
        for(int num : numToCount.keySet()){
            if(numToCount.get(num)!=3) return num;
        }
        return -1;
    }
}
