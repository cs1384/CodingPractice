package LeetCode.lc_101_150;

import LeetCode.util.Printer;

import java.util.Scanner;

/**
 * Created by Tin on 7/3/16.
 */
public class lc121_Best_Time_to_Buy_and_Sell_Stock {
    public static void main(String[] args) {
//        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
//        System.out.println(maxProfit(new int[]{7,6,4,3,1}));
        Scanner scanner = new Scanner(System.in);
        int nTestCase = scanner.nextInt();
        for(int i=0;i<nTestCase;i++){
            int nPrice = scanner.nextInt();
            int[] prices = new int[nPrice];
            for(int j=0;j<nPrice;j++)prices[j] = scanner.nextInt();
            int max = -1;
            long sum = 0l;
            for(int j=nPrice-1;j>=0;j--){
                if(prices[j]>max){
                    max = prices[j];
                }else{
                    sum += max-prices[j];
                }
            }
            System.out.println(sum);
        }
    }
    public static int maxProfit(int[] prices) {
        if(prices==null || prices.length==0) return 0;
        int min = prices[0], max = prices[0];
        int earning = max-min;
        for(int p : prices){
            if(p<min){ // then start over
                earning = Math.max(earning, max-min);
                min = p;
                max = p;
            }else if(p>max){
                max = p;
            }
        }
        return Math.max(earning, max-min);
    }
}
