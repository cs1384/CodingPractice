package LeetCode.lc_001_050;

/**
 * Created by Tin on 6/13/16.
 */
public class lc050_Pow_x_n {
    public static void main(String[] args) {

    }

    /**
     * naive solution is to keep mutiplying the x, but we can use binary feature
     * to speed it up
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        if(n==0) return 1;
        double temp = myPow(x, n/2);
        if(n%2==0) return temp*temp;
        else if(n>0) return x*temp*temp;
        else return temp*temp/x;
    }
}
