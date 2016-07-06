package LeetCode.lc_351_400;

/**
 * Created by Tin on 7/1/16.
 */
public class lc371_Sum_of_Two_Integers {
    public static void main(String[] args) {
        System.out.println(getSum(6,3));
    }
    public static int getSum(int a, int b) {
        return b==0?a:getSum(a^b, (a&b)<<1);
    }
}
