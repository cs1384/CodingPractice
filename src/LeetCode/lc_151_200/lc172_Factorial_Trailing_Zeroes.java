package LeetCode.lc_151_200;

/**
 * Created by Tin on 6/27/16.
 */
public class lc172_Factorial_Trailing_Zeroes {
    public static void main(String[] args) {
        System.out.println(trailingZeroes(5));
        System.out.println(trailingZeroes(9));
        System.out.println(trailingZeroes(10));
        System.out.println(trailingZeroes(50));
    }
    public static int trailingZeroes(int n) {
        int nZeros = 0;
        while(n>=5){
            n /= 5;
            nZeros += n;
        }
        return nZeros;
    }
}
