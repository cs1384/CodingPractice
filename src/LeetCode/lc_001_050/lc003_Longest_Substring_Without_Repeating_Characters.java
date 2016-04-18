package LeetCode.lc_001_050;

import java.util.Arrays;

/**
 * Created by ytliu on 3/12/16.
 */
public class lc003_Longest_Substring_Without_Repeating_Characters {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); //3
        System.out.println(lengthOfLongestSubstring("bbbbb")); //1
        System.out.println(lengthOfLongestSubstring("abba")); //2
    }
    public static int lengthOfLongestSubstring(String s) {
        // use this as a map
        int[] indices = new int[256];
        String res = "";
        int start = 0;
        Arrays.fill(indices, -1);
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(indices[c]!=-1){
                start = Math.max(indices[c]+1,start);
                indices[c] = i;
                if(i+1-start>res.length()) res = s.substring(start, i+1);
            }else{
                indices[c]=i;
                if(i+1-start>res.length()) res = s.substring(start, i+1);
            }
        }
        return res.length();
    }
}
