package LeetCode.lc_001_050;

/**
 * Created by ytliu on 3/24/16.
 */
public class lc005_Longest_Palindromic_Substring {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("abba"));
        System.out.println(longestPalindrome("abbcba"));
    }
    // this is dp way, you could actually go over all characters and find the longest palindrome centered on that character O(N^2)
    public static String longestPalindrome(String s) {
        String res = "";
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for(int i=0;i<len;i++) dp[i][i] = true;
        for(int i=len-1;i>=0;i--){
            for(int j=i;j<len;j++){
                if(s.charAt(i)==s.charAt(j)){
                    if(j-i<=2 || dp[i+1][j-1]){
                        dp[i][j] = true;
                        if(j-i+1>res.length()){
                            res = s.substring(i,j+1);
                        }
                    }
                }
            }
        }
        return res;
    }
}
