package LeetCode.lc_051_100;

/**
 * Created by Tin on 6/13/16.
 */
public class lc053_Maximum_Subarray {
    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        // 4 -1 2 1 -> 6
    }

    /**
     * (-4 5 6) 2 you think dump -4 and add 2 would get better result now, but
     * in fact -4 would have been dumped before (5,6) should be the current package
     * @param A
     * @return
     */
    public static int maxSubArray(int[] A) {
        int newsum=A[0];
        int max=A[0];
        for(int i=1;i<A.length;i++){
            newsum=Math.max(newsum+A[i],A[i]);
            max= Math.max(max, newsum);
        }
        return max;
    }
}
