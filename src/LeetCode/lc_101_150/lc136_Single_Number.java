package LeetCode.lc_101_150;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Tin on 7/24/16.
 */
public class lc136_Single_Number {
    public static void main(String[] args) {
        System.out.println(singleNumber1(new int[]{2,2,5,5,6}));
        System.out.println(singleNumber2(new int[]{2,2,5,5,6}));
        System.out.println(singleNumber(new int[]{2,2,5,5,6}));
    }
    public static int singleNumber(int[] nums) {
        int op = 0;
        for(int num : nums) op ^= num;
        return op;
    }
    public static int singleNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            if(set.contains(num)) set.remove(num);
            else set.add(num);
        }
        return set.iterator().next();
    }
    public static int singleNumber1(int[] nums) {
        Map<Integer, Integer> numToCount = new HashMap<>();
        for(int num :nums){
            if(!numToCount.containsKey(num)) numToCount.put(num, 1);
            else numToCount.put(num, numToCount.get(num)+1);
        }
        for(int num : numToCount.keySet()){
            if(numToCount.get(num)!=2) return num;
        }
        return -1;
    }
}
