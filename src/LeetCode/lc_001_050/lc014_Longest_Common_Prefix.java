package LeetCode.lc_001_050;

import java.util.Arrays;

/**
 * Created by ytliu on 4/13/16.
 */
public class lc014_Longest_Common_Prefix {
    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"abcdef", "abcefr", "abctlk", "abczfd"}));
        System.out.println(longestCommonPrefix(new String[]{"aa","a"}));
//        Arrays.sort(strs);
//        System.out.println(strs);

    }

    public static String longestCommonPrefix(String[] strs) {
        if(strs.length==0) return "";
        int cutPoint = strs[0].length();
        for(int i=1;i<strs.length;i++){
            int top = Math.min(strs[i-1].length(), strs[i].length());
            cutPoint = Math.min(top, cutPoint);
            for(int j=0;j<cutPoint;j++){
                if(strs[i-1].charAt(j)!=strs[i].charAt(j)){
                    cutPoint = j;
                    break;
                }
            }
        }
        return strs[strs.length-1].substring(0, cutPoint);
    }

    /*
    sort first and this does not work, for examle {"aca","cba"}
     */
    public static String longestCommonPrefix2(String[] strs) {
        if(strs.length==0) return "";
        Arrays.sort(strs);
        int index = strs[0].length()-1;
        for(int i=1;i<strs.length;i++){
            index = Math.min(index, strs[i].length()-1);
            while(index>=0){
                if(strs[i-1].charAt(index)==strs[i].charAt(index)) break;
                index--;
            }
        }
        return strs[strs.length-1].substring(0, index+1);
    }
    public String longestCommonPrefix1(String[] strs) {
        if(strs==null) return null;
        if(strs.length==0) return "";
        if(strs.length==1) return strs[0];
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<strs[0].length();i++){
            char temp = strs[0].charAt(i);
            boolean done = true;
            for(int j=1;j<strs.length;j++){
                if(i>=strs[j].length() || strs[j].charAt(i)!=temp){
                    done = false;
                    break;
                }
            }
            if(!done)
                break;
            sb.append(temp);
        }
        return sb.toString();
    }
}
