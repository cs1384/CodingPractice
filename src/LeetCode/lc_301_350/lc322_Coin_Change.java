package LeetCode.lc_301_350;

import java.util.Arrays;

/**
 * Created by Tin on 7/14/16.
 */
public class lc322_Coin_Change {
    public static void main(String[] args) {
//        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
//        System.out.println(coinChange(new int[]{2}, 3));
        System.out.println(coinChange(new int[]{389,351,26,449,408,101}, 5528));
    }
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=0;i<dp.length;i++){
            for(int coin : coins){
                if(i-coin>=0 && dp[i-coin]!=Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[i-coin]+1, dp[i]);
                }
            }
        }
        return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];
    }
    /*
    TLE, but we can actually store the calculated amount in somewhere
     */
    public static int coinChange1(int[] coins, int amount) {
        Arrays.sort(coins);
        return coinsUsed(coins, coins.length-1, amount);
    }
    private static int coinsUsed(int[] coins, int index, int amount){
        if(amount==0) return 0;
        if(index<0) return -1;
        int min = Integer.MAX_VALUE;
        int value = coins[index];
        int used = amount/value;
        boolean possible = false;
        while(used>=0){
            int rest = coinsUsed(coins, index-1, amount-value*used);
            if(rest!=-1){
                min = Math.min(min, used+rest);
                possible = true;
            }
            used--;
        }
        return possible?min:-1;
    }
}
