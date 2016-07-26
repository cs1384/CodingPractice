package LeetCode.lc_101_150;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tin on 7/24/16.
 */
public class lc139_Word_Break {
    public static void main(String[] args) {
        Set<String> wordDict = new HashSet<>();
        wordDict.add("leet");
        wordDict.add("code");
        System.out.println(wordBreak("leetcode", wordDict));
    }
    public static boolean wordBreak(String s, Set<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len+1];
        dp[0] = true;
        for(int i=1;i<=len;i++){
            for(int j=0;j<i;j++){
                if(dp[j] && wordDict.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
}
