package LeetCode.lc_051_100;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tin on 6/20/16.
 */
public class lc76_Minimum_Window_Substring {
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow("a", "a"));

    }
    public static String minWindow(String s, String t) {
        int[] required = new int[256];
        int[] have = new int[256];
        for(char c :t.toCharArray()) required[c]++;
        int start = 0, end = s.length()-1;
        String res = s.substring(start, end+1);
        int found = 0;
        for(int i=0;i<s.length();i++){
            int index = s.charAt(i);
            if(required[index]>0){
                if(have[index]<required[index]) found++;
                have[index]++;
                if(found==t.length()){
                    end = i;
                    int head = s.charAt(start);
                    while(required[head]==0 || have[head]>required[head]){
                        have[head]--;
                        head = s.charAt(++start);
                    }
                    if((end-start+1)<res.length()){
                        res = s.substring(start, end+1);
                    }
                }
            }
        }
        if(found<t.length()) return "";
        return res;
    }
    public static String minWindow1(String s, String t) {
        Map<Character, Integer> char_count = new HashMap<>();
        for(char c :t.toCharArray()){
            if(!char_count.containsKey(c)) char_count.put(c,-1);
            else char_count.put(c, char_count.get(c)-1);
        }
        int start = 0, end = s.length()-1;
        String res = s.substring(start, end+1);
        int matched = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(char_count.containsKey(c)){
                if(char_count.get(c)<0) matched++;
                char_count.put(c, char_count.get(c)+1);
                if(matched>=t.length()){
                    end = i;
                    char first = s.charAt(start);
                    while(!char_count.containsKey(first) || char_count.get(first)>0){
                        if(char_count.containsKey(first)) char_count.put(first, char_count.get(first)-1);
                        first = s.charAt(++start);
                    }
                    if((end-start+1)<res.length()){
                        res = s.substring(start, end+1);
                    }
                }
            }
        }
        if(matched<t.length()) return "";
        return res;
    }

}
