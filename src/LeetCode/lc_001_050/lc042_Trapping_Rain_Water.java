package LeetCode.lc_001_050;

/**
 * Created by ytliu on 6/5/16.
 */
public class lc042_Trapping_Rain_Water {
    public static void main(String[] args) {
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
    public static int trap(int[] height) {
        int len = height.length;
        int[] lowerBound = new int[len];
        int max = 0;
        // calculate the left wall
        for(int i=0;i<len;i++){
            lowerBound[i] = max;
            max = Math.max(max, height[i]);
        }
        max = 0;
        int trappedUnits = 0;
        int add = 0;
        // calculate the right wall and how much rain we can trap
        for(int i=len-1;i>=0;i--){
            lowerBound[i] = Math.min(lowerBound[i], max);
            max = Math.max(max, height[i]);
            // calculate rain units
            add = lowerBound[i]-height[i];
            trappedUnits += add>0?add:0;
        }
        return trappedUnits;
    }
}
