package LeetCode.lc_151_200;

import LeetCode.util.Printer;

import java.sql.Time;
import java.util.*;

/**
 * Created by Tin on 8/1/16.
 */
public class lc169_Majority_Element {
    public static void main(String[] args) {
        int[] arr = {1,2,1,2,1,1,2,3,3,3,3,3,3,3,3};
        System.out.println(majorityElement1(arr));
        System.out.println(majorityElement2(arr));
        System.out.println(majorityElement3(arr));
        System.out.println(majorityElement4(arr));
        System.out.println(majorityElement5(arr));
    }
    /*
    Divide and Conqur
    O(logN)<-function stack, and O(NlogN)
     */
    public static int majorityElement5(int[] nums) {
        int len = nums.length;
        if(len==1) return nums[0];
//        Printer.printArray(nums);
        int half = len/2;
        int left = majorityElement5(Arrays.copyOfRange(nums, 0, half));
        int right = majorityElement5(Arrays.copyOfRange(nums, half, len));
        int countOfLeft = 0;
        for(int num : nums){
            if(num==countOfLeft) countOfLeft++;
        }
        return countOfLeft>half?left:right;
    }
    /*
    moore voting
    https://www.quora.com/What-is-the-proof-of-correctness-of-Moores-voting-algorithm
    O(1) and O(N)
     */
    public static int majorityElement4(int[] nums) {
        int majority = nums[0], count = 0;
        for(int num : nums){
            if(count==0){
                majority = num;
                count = 1;
            }else if(num==majority){
                count++;
            }else count --;
        }
        return majority;
    }
    /*
    sorting
    O(1) and O(NlogN)
     */
    public static int majorityElement3(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
    /*
    count bit
    O(32) and O(N)
     */
    public static int majorityElement2(int[] nums) {
        int threshold = nums.length/2;
        int[] bits = new int[32];
        int res = 0;
        for(int i=0;i<bits.length;i++){
            int mask = 1<<i;
            for(int num : nums){
                if((num&mask)!=0) bits[i]++;
            }
            if(bits[i]>threshold) res |= mask;
        }
        return res;
    }
    /*
    Hashmap
    O(N) and O(N)
     */
    public static int majorityElement1(int[] nums) {
        Map<Integer, Integer> numToCount = new HashMap<>();
        int majority = nums[0];
        int count = 1;
        for(int num : nums){
            if(!numToCount.containsKey(num)) numToCount.put(num, 1);
            else numToCount.put(num, numToCount.get(num)+1);
            if(numToCount.get(num)>count){
                majority = num;
                count = numToCount.get(num);
            }
        }
        return majority;
    }
}
