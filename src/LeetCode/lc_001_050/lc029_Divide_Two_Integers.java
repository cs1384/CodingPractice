package LeetCode.lc_001_050;

/**
 * Created by ytliu on 4/25/16.
 */
public class lc029_Divide_Two_Integers {
    public static void main(String[] args) {
        System.out.println(divide(-2147483648,-1));
        System.out.println(divide(-2147483648,1));
        System.out.println(divide(2147483647,-1));
    }
    public static int divide(int dividend, int divisor) {
        if(divisor==0) return Integer.MAX_VALUE; //illegal
        if(dividend==0) return 0;

        if(divisor==1) return dividend; //speed up

        boolean negative = (dividend>0 && divisor<0) || (dividend<0 && divisor>0);

        // treat them as negative numbers to avoid overflow
        if(dividend>0) dividend = -dividend;
        if(divisor>0) divisor = -divisor;
        int res = 0;

        int remain = dividend;
        while(remain<=divisor){
            int op = divisor;
            int shift = -1; // again, use negative number to avoid overflow
            while(remain/2<=op){
                op <<= 1;
                shift <<= 1;
            }
            remain -= op; // -2147483648 -(-2147483648) will be 0
            res += shift;
        }
//        first thought, but too slow
//        while(dividend<=divisor){
//            dividend -= divisor;
//            res++;
//        }
        if(!negative && res==Integer.MIN_VALUE) return Integer.MAX_VALUE; //overflow
        return negative?res:-res;
    }
}
