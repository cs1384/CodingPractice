package LeetCode.lc_051_100;

/**
 * Created by Tin on 6/17/16.
 */
public class lc070_Climbing_Stairs {
    public static void main(String[] args) {
        System.out.println(climbStairs(3));
    }
    /*
    totally DP. Nth stair is based on N-1 and N-2
     */
    public static int climbStairs(int n) {
        if(n<=2) return n;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for(int i=2;i<n;i++){
            dp[i] = dp[i-2] + dp[i-1];
        }
        return dp[n-1];
    }
}
