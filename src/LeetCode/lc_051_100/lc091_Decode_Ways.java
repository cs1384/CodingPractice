package LeetCode.lc_051_100;

import LeetCode.util.Printer;

/**
 * Created by Tin on 6/26/16.
 */
public class lc091_Decode_Ways {
    public static void main(String[] args) {
        System.out.println(numDecodings("12"));//2
        System.out.println(numDecodings("126"));//3
        System.out.println(numDecodings("0"));//0
        System.out.println(numDecodings("10"));//1
        System.out.println(numDecodings("20"));//1
        System.out.println(numDecodings("30"));//0
        System.out.println(numDecodings("100"));//0
        System.out.println(numDecodings("101"));//1
    }
    public static int numDecodings(String s) {
        int len = s.length();
        if(len==0) return 0;
        if(s.charAt(0)=='0') return 0;
        int[] dp = new int[len];
        dp[0] = 1;
        for(int i=1;i<len;i++){
            boolean selfLegit = s.charAt(i)!='0';
            int twoDigitNum = Integer.parseInt(s.substring(i-1,i+1));
            boolean withPreLegit = s.charAt(i-1)=='0'?false:(twoDigitNum<=26);
            dp[i] = (selfLegit?dp[i-1]:0) + (withPreLegit?(i>=2?dp[i-2]:1):0);
            if(dp[i]==0) return 0;
        }
        return dp[len-1];
    }
}
