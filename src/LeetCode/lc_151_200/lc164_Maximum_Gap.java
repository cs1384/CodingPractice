package LeetCode.lc_151_200;

import LeetCode.util.Printer;

/**
 * Created by Tin on 7/29/16.
 */
public class lc164_Maximum_Gap {
    public static void main(String[] args) {
//        System.out.println(maximumGap(new int[]{3,9,7,1}));
        System.out.println(maximumGap(new int[]{1,10000}));
    }
    /*
    radix sort, time complexity is still O(N) but with larger coefficient
     */
    public static int maximumGap(int[] nums) {
        int len = nums.length;
        int max = 0;
        for(int n : nums) max = Math.max(max, n);
        int[] helper = new int[len];
        int op = 1;
        while(max/op>=1){
            int[] count = new int[10];
            for(int n : nums){
                count[n%(op*10)/op]++;
            }
            for(int i=1;i<count.length;i++){
                count[i] += count[i-1];
            }
            for(int i=len-1;i>=0;i--){
                int n = nums[i];
                int index = --count[n%(op*10)/op];
                helper[index] = n;
            }
            for(int i=0;i<len;i++) nums[i] = helper[i];
            op *= 10;
        }
        int maxGap = 0;
        for(int i=1;i<len;i++) maxGap = Math.max(maxGap, nums[i]-nums[i-1]);
        return maxGap;
    }
    /*
    bucket sort with some trick, best solution
     */
    public static int maximumGap1(int[] nums) {
        int size = nums.length;
        if(size<2) return 0;
        // get the minimum gap, the answer should be greater than that
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int n : nums){
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        int gap = (int)Math.ceil((double)(max-min)/(size-1));
        // with this minGap, there will be size-1 buckets
        int[] bucketLargest = new int[size-1];
        int[] bucketSmallest = new int[size-1];
        for(int i=0;i<size-1;i++){
            bucketSmallest[i] = min+(i+1)*gap;
        }
        // put n-2 elements into n-1 buckets
        for(int n : nums){
            if(n==min || n==max) continue;
            int bucketNumber = (n-min)/gap;
            bucketLargest[bucketNumber] = Math.max(bucketLargest[bucketNumber], n);
            bucketSmallest[bucketNumber] = Math.min(bucketSmallest[bucketNumber], n);
        }
        // loop over buckets to get the possible maxGap candidates
        int lowerBound = min;
        int maxGap = 0;
        boolean gapApeared = false;
        for(int i=0;i<bucketLargest.length;i++){
            if(bucketLargest[i]==0) continue;
            maxGap = Math.max(maxGap, bucketSmallest[i]-lowerBound);
            lowerBound = bucketLargest[i];
        }
        // don't forget the max value
        return Math.max(maxGap, max-lowerBound);
    }
}
