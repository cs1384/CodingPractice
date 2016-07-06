package LeetCode.lc_101_150;

/**
 * Created by Tin on 7/4/16.
 */
public class lc122_Best_Time_to_Buy_and_Sell_Stock_II {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1,10,1,100}));
    }
    public static int maxProfit(int[] prices) {
        int len = prices.length;
        if(len<2) return 0;
        int profit = 0;
        int max = prices[len-1];
        for(int i=len-2;i>=0;i--){
            if(prices[i]>max) max = prices[i];
            else if(i==0 || prices[i-1]>prices[i]){
                profit += max-prices[i];
                max = prices[i];
            }
        }
        return profit;
    }
}
