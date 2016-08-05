package LeetCode.lc_151_200;

import LeetCode.util.Printer;

/**
 * Created by Tin on 8/3/16.
 */
public class lc188_Best_Time_to_Buy_and_Sell_Stock_IV {
    public static void main(String[] args) {
        System.out.println(maxProfit(2, new int[]{3,5,2,3,6,8})); //8
        System.out.println(maxProfit(4, new int[]{1,2,4,2,5,7,2,4,9,0})); //15
        System.out.println(maxProfit(6, new int[]{8,3,6,2,8,8,8,4,2,0,7,2,9,4,9})); //28
    }
    /*
    Each step we can either do nothing (take max from maxAtJByITransaction[i][j-1])
    or sell the stock to finish one more transaction based on a max from i-1 round.
    The profit for second option is prices[j]-prices[x]+maxAtJByITransaction[i-1][x-1]
    we want this part (-prices[x]+maxAtJByITransaction[i-1][x-1]) to be as much as posible
     */
    public static int maxProfit(int k, int[] prices) {
        int len = prices.length;
        // same as lc122_Best_Time_to_Buy_and_Sell_Stock_II
        if(k>len/2) return trivialSolution(prices);
        int[][] maxAtJByITransaction = new int[k+1][len];
        for(int i=1;i<=k;i++){
            int earnedAtHolding = maxAtJByITransaction[i-1][0]-prices[0];
            for(int j=1;j<len;j++){
                maxAtJByITransaction[i][j] =
                        Math.max(maxAtJByITransaction[i][j-1], prices[j]+earnedAtHolding);
                earnedAtHolding = Math.max(earnedAtHolding, maxAtJByITransaction[i-1][j-1]-prices[j]);
            }
        }
        return maxAtJByITransaction[k][len-1];
    }
    public static int maxProfit2(int k, int[] prices) {
        int len = prices.length;
        // same as lc122_Best_Time_to_Buy_and_Sell_Stock_II
        if(k>len/2) return trivialSolution(prices);
        int[][] holding = new int[k+1][len];
        int[][] notHolding = new int[k+1][len];
        for(int i=0;i<k+1;i++) holding[i][0] = -prices[0];
        for(int j=1;j<len;j++) holding[0][j] = Math.max(holding[0][j-1], -prices[j]);
        for(int i=1;i<k+1;i++){
            for(int j=1;j<len;j++){
                /*
                Either take the max from previous date, or finished the transaction from
                 holding in k-1, so the profit would be only from k transactions at top
                 */
                notHolding[i][j] = Math.max(notHolding[i][j-1], holding[i-1][j-1]+prices[j]);
                /*
                Basically prepare for the transaction in the i+1 round
                 */
                holding[i][j] = Math.max(holding[i][j-1], notHolding[i][j-1]-prices[j]);
            }
        }
        /*
        No one will spend money on the last date, so holding[k][len-1] is not taken
        into account
         */
        return notHolding[k][len-1];
//        return Math.max(holding[k][len-1], notHolding[k][len-1]);
    }
    /*
    make transaction anytime you want, so you can earn all the arbitrary gaps
     */
    private static int trivialSolution(int[] prices){
        int profit = 0;
        for(int i=1;i<prices.length;i++){
            if(prices[i]>prices[i-1]) profit += prices[i]-prices[i-1];
        }
        return profit;
    }
    /*
    Think too complicated, not working
     */
    public static int maxProfit1(int k, int[] prices) {
        int len = prices.length;
        if(len<2) return 0;
        int[] earningByNotHoldingAfter = new int[len];
        int earning = 0, peak = prices[len-1];
        for(int i=len-2;i>=0;i--){
            earning = Math.max(earning, peak-prices[i]);
            earningByNotHoldingAfter[i] = earning;
            peak = Math.max(peak, prices[i]);
        }
        int[] temp = new int[len];
        for(int i=2;i<=k;i++){
            peak = prices[len-1];
            int cut = len-1;
            for(int j=len-2;j>=0;j--){
                if(peak-prices[j]>0){
                    temp[j] = peak-prices[j]+earningByNotHoldingAfter[cut];
                    if(temp[j]<earningByNotHoldingAfter[j+1]){
                        peak = prices[j];
                        cut = j+1;
                    }
                }else{
                    peak = prices[j];
                    cut = j+1;
                    temp[j] = earningByNotHoldingAfter[cut];
                }
                earning= Math.max(earning, temp[j]);
            }
            for(int j=0;j<len;j++) earningByNotHoldingAfter[j] = temp[j];
        }
        return earning;
    }
}
