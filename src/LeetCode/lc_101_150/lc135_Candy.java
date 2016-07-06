package LeetCode.lc_101_150;

import LeetCode.util.Printer;

/**
 * Created by Tin on 7/5/16.
 */
public class lc135_Candy {
    public static void main(String[] args) {
        System.out.println(candy(new int[]{1,2,2})); //4
        System.out.println(candy(new int[]{1,1,4,4,3,2,2,1})); //13
        System.out.println(candy(new int[]{4,2,3,4,1})); //9
        System.out.println(candy(new int[]{1,3,4,3,2,1})); //13
    }
    /*
    two passes: one from left and another from right. To know how many level we
    need from each direction.
     */
    public static int candy(int[] ratings) {
        int len = ratings.length;
        int[] dp = new int[len];
        dp[0]=1;
        for(int i=1;i<len;i++){
            dp[i] = ratings[i]>ratings[i-1]?dp[i-1]+1:1;
        }
        int total = dp[len-1];
        for(int i=len-2;i>=0;i--){
            int fromRight = ratings[i]>ratings[i+1]?dp[i+1]+1:1;
            total += Math.max(fromRight, dp[i]);
            dp[i] = fromRight;
        }
        return total;
    }
    /*
    Does not work
     */
    public static int candy1(int[] ratings) {
        int peakIndex = 0;
        int give = 1;
        int res = give;
        for(int i=1;i<ratings.length;i++){
            if(ratings[i-1]<ratings[i]){
                peakIndex = i;
                give++;
            }else if(ratings[i-1]>ratings[i]){
                give = give>1?1:0;
            }else{
                peakIndex = i;
                give = 1;
            }
            if(give<1){
                res += (i-peakIndex);
                give = 1;
            }
            res += give;
        }
        return res;
    }
}
