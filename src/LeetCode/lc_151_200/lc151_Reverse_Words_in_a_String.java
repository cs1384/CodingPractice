package LeetCode.lc_151_200;

import LeetCode.util.Printer;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Tin on 7/27/16.
 */
public class lc151_Reverse_Words_in_a_String {
    public static void main(String[] args) {
        System.out.println(reverseWords("the sky is blue"));
        System.out.println(reverseWords("   a   b "));
    }
    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int i = s.length()-1;
        while(i>=0){
            if(s.charAt(i)==' ') i--;
            else{
                int op = i-1;
                while(op>=0 && s.charAt(op)!=' ') op--;
                sb.append(s.substring(op+1, i+1));sb.append(' ');
                i = op-1;
            }
        }
        return sb.length()>=1?sb.deleteCharAt(sb.length()-1).toString():"";
    }
    /*
    Use every builtin methods, but still not fast enough
     */
    public static String reverseWords2(String s) {
        String[] words = s.trim().split(" +");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }
    /*
    slow but cool
     */
    public static String reverseWords1(String s) {
        char[] arr = s.trim().replaceAll("\\s+"," ").toCharArray();
        int len = arr.length;
        swapCharacters(arr, 0, len-1);
        int from = 0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]!=' ' && from==-1) from = i;
            if(from!=-1 && (i+1==len || arr[i+1]==' ')){
                swapCharacters(arr, from, i);
                from = -1;
            }
        }
        return new String(arr);
    }
    private static void swapCharacters(char[] arr, int from, int to){
        while(from<to){
            char temp = arr[from];
            arr[from] = arr[to];
            arr[to] = temp;
            from++;to--;
        }
    }
}
