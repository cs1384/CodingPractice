package Puzzles;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 3/7/16.
 */
public class Guess_Number_Cost_Is_the_Number_You_Guess {
    public static void main(String[] args) {
        getStartNumberAndCost(1);
        getStartNumberAndCost(2);
        getStartNumberAndCost(3);
        getStartNumberAndCost(4);
        getStartNumberAndCost(5);
        getStartNumberAndCost(6);
        getStartNumberAndCost(7);
        getStartNumberAndCost(8);
        getStartNumberAndCost(9);
        getStartNumberAndCost(10);
        getStartNumberAndCost(100);
        getStartNumberAndCost(1000);
    }
    public static void getStartNumberAndCost(int n){
        int start = 1;
        int[][] dp = new int[n][n];
//        for(int i=0;i<n;i++) dp[i][i] = i+1;
        for(int j=1;j<n;j++){
            for(int i=n-1;i>=0;i--){
                if(i+j>=n) continue;
//                System.out.println("i:"+i+", j:"+j);
                int op = i;
                int top = i+j;
                int rangeMin = Integer.MAX_VALUE;
//                System.out.println("Working on ("+(i+1)+","+(top+1)+")");
                while(op<=top){
//                    System.out.println("Cut on "+(op+1));
                    int cut = (op+1);
                    int left = (op-1)>=0?dp[i][op-1]:0;
//                    System.out.println("Left:"+left);
                    int right = ((op+1)<n?dp[op+1][top]:0);
//                    System.out.println("right:"+right);
                    int cost = cut + (left>=right?left:right);
//                    System.out.println("cost:"+cost);

//                    System.out.println("Cut on "+(op+1)+", the cost is "+cost);
                    if(cost<rangeMin){
                        rangeMin = cost;
                        start = op+1;
                    }
                    op++;
                }
//                System.out.println("Decided:"+rangeMin);
                dp[i][top] = rangeMin;
            }
//            Printer.printMetrix(dp);
        }
        System.out.println("Start with "+start+", and you will spend only "+dp[0][n-1]);
    }
}
