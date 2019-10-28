package LeetCode.lc_251_300;

import java.util.Arrays;

public class lc268_Missing_Number {
    public static void main(String[] args) {
        lc268_Missing_Number lc268_missing_number = new lc268_Missing_Number();
        System.out.println(lc268_missing_number.missingNumber1(new int[]{3,0,1})); //2
        System.out.println(lc268_missing_number.missingNumber1(new int[]{9,6,4,2,3,5,7,0,1})); //8
        System.out.println(lc268_missing_number.missingNumber2(new int[]{3,0,1})); //2
        System.out.println(lc268_missing_number.missingNumber2(new int[]{9,6,4,2,3,5,7,0,1})); //8
    }

    /*
    xor on value and index
     */
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for (int i = 0 ; i < nums.length ; i++) {
            res ^= i;
            res ^= nums[i];
        }
        return res;
    }

    /*
    calculate sum and minus
     */
    public int missingNumber2(int[] nums) {
        int sum = ( 0 + nums.length ) * (nums.length + 1) / 2;
        return sum - Arrays.stream(nums).sum();
    }

    /*
    sort
     */
    public int missingNumber1(int[] nums) {
        Arrays.sort(nums);
        for (int i=0 ; i<nums.length ; i++) {
            if (nums[i] != i) return i;
        }
        return nums.length;
    }
}
