package LeetCode.lc_101_150;

import LeetCode.util.Printer;

/**
 * Created by Tin on 7/13/16.
 */
public class lc115_Distinct_Subsequences {
    public static void main(String[] args) {
        System.out.println(numDistinct("rabbbit", "rabbit"));//3
        System.out.println(numDistinct("", "a"));//0
        System.out.println(numDistinct("ccc", "c"));//3
        System.out.println(numDistinct("aacaacca", "ca"));//5
    }
    /*
    Failed numDistinct("aacaacca", "ca")

        '' a a c a a c c a
     '' 1  1 1 1 1 1 1 1 1
     c  0  0 0 1 1 1 2 3 3
     a  0  0 0 0 1 2 2 2 5
     5 = (matching 'a' at this index -> 3) + (not matching 'a' here -> we got 2 ways before)
     */
    public static int numDistinct(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        int[][] dp = new int[tLen+1][sLen+1];
        for(int j=0;j<=sLen;j++) dp[0][j] = 1;
        for(int i=1;i<=tLen;i++){
            for(int j=1;j<=sLen;j++){
                dp[i][j] = dp[i][j-1] + (t.charAt(i-1)==s.charAt(j-1)?dp[i-1][j-1]:0);
            }
        }
        Printer.printMatrix(dp);
        return dp[tLen][sLen];
    }
    /*
    Failed numDistinct("aacaacca", "ca")
     */
    public static int numDistinct1(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if(sLen<tLen) return 0;
        boolean[] required = new boolean[sLen];
        int op = 0;
        for(int i=0;i<sLen;i++){
            if(s.charAt(i)==t.charAt(op)){
                required[i] = true;
                if(++op==tLen) break;
            }
        }
        if(op!=tLen) return 0;
        int res = 1;
        int i = sLen-1;
        while(i>=0){
            if(!required[i]){
                char c = s.charAt(i);
                int num = 1, outOf = 1;
                while(i>0 && s.charAt(--i)==c){
                    if(!required[i]) num++;
                    outOf++;
                }
                if(outOf!=1) res *= combination(outOf, num);
            }else i--;
        }
        return res;
    }
    private static int combination(int outOf, int num){
        num = Math.max(outOf-num, num);
        int numerator = 1;
        for(int i=outOf;i>num;i--) numerator *= i;
        return numerator/factorial(outOf-num);
    }
    private static int factorial(int n){
        int res = 1;
        for(int i=1;i<=n;i++) res *= i;
        return res;
    }
}
