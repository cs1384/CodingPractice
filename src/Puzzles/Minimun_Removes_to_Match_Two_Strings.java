package Puzzles;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 3/3/16.
 */
public class Minimun_Removes_to_Match_Two_Strings {
    public static void main(String[] args) {
        System.out.println(minRemove("dafbea", "aba"));
    }
    public static int minRemove(String a, String b){
        int[][] dp = new int[a.length()][b.length()];
        dp[0][0] = a.charAt(0)==b.charAt(0)?1:0;
        for(int i=1;i<b.length();i++) dp[0][i] = a.charAt(0)==b.charAt(i)?1:dp[0][i-1];
        for(int j=1;j<a.length();j++) dp[j][0] = a.charAt(j)==b.charAt(0)?1:dp[j-1][0];
        for(int i=1;i<a.length();i++){
            for(int j=1;j<b.length();j++){
                int preMax = Math.max(dp[i-1][j-1], Math.max(dp[i-1][j], dp[i][j-1]));
                dp[i][j] = preMax+(a.charAt(i)==b.charAt(j)?1:0);
            }
        }
        Printer.printMatrix(dp);
        return a.length()+b.length()-(dp[a.length()-1][b.length()-1]*2);
    }
}
