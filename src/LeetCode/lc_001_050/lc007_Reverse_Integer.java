package LeetCode.lc_001_050;

/**
 * Created by ytliu on 3/28/16.
 */
public class lc007_Reverse_Integer {
    public static void main(String[] args) {
        System.out.println(reverse(123));
        System.out.println(reverse(-123));
        System.out.println(reverse(-103));
        System.out.println(reverse(-100));
        System.out.println(reverse(1534236469));
        System.out.println(reverse(-2147483648));
    }
    public static int reverse(int x) {
        int res = 0;
        int op = x>0?10:-10;
        while(x!=0){
            // check upper and lower bound
            if(res<0 && Integer.MIN_VALUE/10>res) return 0;
            if(res>0 && Integer.MAX_VALUE/10<res) return 0;
            res *= 10;
            res += x%op;
            x /= 10;
        }
        return res;
    }
}
