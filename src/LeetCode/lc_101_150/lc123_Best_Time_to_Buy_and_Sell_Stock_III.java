package LeetCode.lc_101_150;

import java.util.PriorityQueue;

/**
 * Created by Tin on 7/15/16.
 */
public class lc123_Best_Time_to_Buy_and_Sell_Stock_III {
    public static void main(String[] args) {
//        System.out.println(maxProfit(new int[]{1,2,3,2,3,4,3,2,3,4,5})); //5
        System.out.println(maxProfit(new int[]{1,2,4,2,5,7,2,4,9,0})); //13

    }
    public static int maxProfit(int[] prices) {
        int len = prices.length;
        if(len<=1) return 0;

        int[] earningByNotHoldingAfter = new int[len];
        int peak = prices[len-1];
        int earning = 0;
        for(int i=len-2;i>=0;i--){
            earning = Math.max(earning, peak-prices[i]);
            earningByNotHoldingAfter[i] += earning;
            peak = Math.max(peak, prices[i]);
        }

        int[] earningByHoldingTo = new int[len];
        int bottom = prices[0];
        earning = 0;
        for(int i=1;i<len;i++){
            earning = Math.max(earning, prices[i]-bottom);
            earningByHoldingTo[i] += earning;
            bottom = Math.min(bottom, prices[i]);
        }

        int max = 0;
        for(int i=0;i<len;i++){
            int profit = earningByHoldingTo[i]+(i+1<len?earningByNotHoldingAfter[i+1]:0);
            max = Math.max(max, profit);
        }
        return max;
    }
    /*
    Failed
    System.out.println(maxProfit(new int[]{1,2,4,2,5,7,2,4,9,0})); //13
     */
    public static int maxProfit1(int[] prices) {
        int len = prices.length;
        int lowIndex = -1;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(int i=0;i<len;i++){
            System.out.println("at index "+i+" : "+prices[i]);
            if(lowIndex==-1 && i!=len-1 && prices[i]<prices[i+1]) lowIndex = i;
            else if(lowIndex!=-1 && (i==len-1 || prices[i]>prices[i+1])){
                int earning = prices[i]-prices[lowIndex];
                System.out.println("earning:"+earning);
                priorityQueue.offer(earning);
                if(priorityQueue.size()>2) priorityQueue.poll();
                lowIndex = -1;
            }
            System.out.println("lowIndex:"+lowIndex);
            System.out.println(priorityQueue);
        }
        int profit = 0;
        while(!priorityQueue.isEmpty()) profit += priorityQueue.poll();
        return profit;
    }
}
