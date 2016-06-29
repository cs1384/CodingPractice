package LeetCode.lc_051_100;

import LeetCode.util.Printer;

/**
 * Created by Tin on 6/28/16.
 */
public class lc096_Unique_Binary_Search_Trees {
    public static void main(String[] args) {
//        System.out.println(numTrees(3));
//        System.out.println(numTrees(4));
        System.out.println(numTrees(6));
    }
    public static int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int i=1;i<=n;i++){
            int num = 0;
            int excepSelf = i-1;
            // 0,4 1,3 2,2
            for(int j=0;j<=excepSelf/2;j++){
                int left = j, right = excepSelf-j;
                num += dp[left]*dp[right]*(left==right?1:2);
            }
            dp[i] = num;
        }
        Printer.printArray(dp);
        return dp[n];
    }
}
