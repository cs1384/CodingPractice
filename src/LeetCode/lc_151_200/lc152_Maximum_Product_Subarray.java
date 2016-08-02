package LeetCode.lc_151_200;

/**
 * Created by Tin on 7/28/16.
 */
public class lc152_Maximum_Product_Subarray {
    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2,3,-2,4})); //6
        System.out.println(maxProduct(new int[]{-2,0,0,-4})); //0
    }
    /*
    much clearer, but somehow slower
     */
    public static int maxProduct(int[] nums) {
        if(nums==null || nums.length==0) return Integer.MIN_VALUE;
        int op_min = nums[0];
        int op_max = nums[0];
        int res = nums[0];
        for(int i=1;i<nums.length;i++){
            // get possible values
            int a = nums[i]*op_min;
            int b = nums[i]*op_max;
            // see if we need to abandon previous sequence
            op_min = Math.min(Math.min(a, b), nums[i]);
            op_max = Math.max(Math.max(a, b), nums[i]);
            // update the result
            res = Math.max(op_max, res);
        }
        return res;
    }
    /*
    came up myself, fast!!
     */
    public static int maxProduct1(int[] nums) {
        int localMin = nums[0]<0?nums[0]:0;
        int localMax = nums[0]>0?nums[0]:0;
        int max = nums[0];
        for(int i=1;i<nums.length;i++){
            int n = nums[i];
            if(n==0){
                localMax = 0;
                localMin = 0;
                max = Math.max(max, 0);
            }else if(n>0){
                localMax = localMax==0?n:localMax*n;
                localMin = localMin*n;
                max = Math.max(max, localMax);
            }else{
                int temp = localMin;
                localMin = Math.min(n, localMax*n);
                localMax = temp*n;
                max = Math.max(max, localMax);
            }
        }
        return max;
    }
}
