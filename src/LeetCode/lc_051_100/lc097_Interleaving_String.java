package LeetCode.lc_051_100;

import LeetCode.util.Printer;

/**
 * Created by Tin on 6/29/16.
 */
public class lc097_Interleaving_String {
    public static void main(String[] args) {
//        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
//        System.out.println(isInterleave("aabcc", "dbbca", "aadbbbaccc"));
//        System.out.println(isInterleave("", "abc", "abc"));
        System.out.println(isInterleave("a", "b", "a"));
    }
    /*
    though1, backtracking, try and error:
        if not match -> switch to another string or go backtracking
    thought2,
        for each string, create a bunch lists of positions on the third string
        and then check if any two from each strings can accomadate all positions
    though3,
        In fact, we have only two options on each char when we build up the third.
         for example, aab and dbbc, since we need to use up all of them, we either
         put 'b' or put 'c' for the last char, if 'c', then this based on can "aab"
         and "dbb" form interleaving string. Then this is a DP idea.
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length();
        if(len1+len2!=s3.length()) return false;
        int[][] dp = new int[len2+1][len1+1];

        for(int j=1;j<=len1;j++)
            if(j==1 || dp[0][j-1]!=0)
                dp[0][j] = s1.charAt(j-1)==s3.charAt(j-1)?dp[0][j-1]+1:0;
            else break;

        Printer.printMatrix(dp);

        for(int i=1;i<=len2;i++)
            if(i==1 || dp[i-1][0]!=0)
                dp[i][0] = s2.charAt(i-1)==s3.charAt(i-1)?dp[i-1][0]+1:0;
            else break;

        Printer.printMatrix(dp);

        for(int i=1;i<=len2;i++){
            for(int j=1;j<=len1;j++){
                if(dp[i-1][j]!=0 && s2.charAt(i-1)==s3.charAt(dp[i-1][j]))
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j]+1);
                if(dp[i][j-1]!=0 && s1.charAt(j-1)==s3.charAt(dp[i][j-1]))
                    dp[i][j] = Math.max(dp[i][j], dp[i][j-1]+1);
            }
            Printer.printMatrix(dp);
        }
        return dp[len2][len1]==s3.length();
    }
}
