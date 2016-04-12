package LeetCode.lc_001_050;

/**
 * Created by ytliu on 4/11/16.
 */
public class lc011_Container_With_Most_Water {
    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{6,3,5,8}));
    }
    public static int maxArea(int[] height) {
        int i = 0;
        int j = height.length-1;
        int max = 0;
        while(i<j){
            max = Math.max(max, Math.min(height[i],height[j])*(j-i));
            if(height[i]<height[j]) i++;
            else j--;
        }
        return max;
    }
}
