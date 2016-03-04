package LeetCode.lc_301_350;

/**
 * Created by ytliu on 3/4/16.
 */
public class lc309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1,2,3,0,2})); //3
    }
    public static int maxProfit(int[] prices) {
        int len = prices.length;
        if(len==0) return 0;
        int[] buy = new int[len];buy[0] = -prices[0];
        int[] sell = new int[len];sell[0] = -1; //cant sell before buy
        int[] cool = new int[len];cool[0] = 0;
        for(int i=1;i<len;i++){
            buy[i] = Math.max(cool[i-1]-prices[i], buy[i-1]); //restriction1: cool before buy -> OK
            sell[i] = Math.max(buy[i-1]+prices[i], sell[i-1]); // restriction2: buy before sell -> OK
            // what about restriction3: sell before buy?
            cool[i] = Math.max(Math.max(sell[i-1], buy[i-1]),cool[i-1]);
        }
        return Math.max(Math.max(buy[len-1], sell[len-1]), cool[len-1]);
    }
}
