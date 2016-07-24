package LeetCode.lc_101_150;

import java.util.Collections;
import java.util.Stack;

/**
 * Created by Tin on 7/16/16.
 */
public class lc125_Valid_Palindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome1("A man, a plan, a canal: Panama"));//t
        System.out.println(isPalindrome1("race a car"));//f
    }
    public static boolean isPalindrome(String s) {
        int i = 0, j = s.length()-1;
        while(i<j){
            if(!Character.isLetterOrDigit(s.charAt(i))) i++;
            else if(!Character.isLetterOrDigit(s.charAt(j))) j--;
            else {
                if(Character.toLowerCase(s.charAt(i++)) != Character.toLowerCase(s.charAt(j--))) return false;
            }
        }
        return true;
    }
    public static boolean isPalindrome3(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int i = 0, j = s.length()-1;
        while(i<j) if(s.charAt(i++)!=s.charAt(j--)) return false;
        return true;
    }
    public static boolean isPalindrome2(String s) {
        s = preProcess(s);
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        return s.equals(sb.toString());
    }
    public static boolean isPalindrome1(String s) {
        s = preProcess(s);
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()) stack.push(c);
        for(char c : s.toCharArray()){
            if(c!=stack.pop()) return false;
        }
        return true;
    }
    private static String preProcess(String s){
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()){
            if(Character.isLetterOrDigit(c)) sb.append(c);
        }
        return sb.toString().toLowerCase();
    }
}
