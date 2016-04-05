package LeetCode.lc_001_050;

import java.util.Map;

/**
 * Created by ytliu on 4/3/16.
 */
public class lc009_Palindrome_Number {
    public static void main(String[] args) {
        System.out.println(isPalindrome1(12321));
        System.out.println(isPalindrome(100001));
        System.out.println(isPalindrome(10));
        System.out.println(isPalindrome(1));
    }
    public static boolean isPalindrome1(int x) {
        int numDigit = 0;
        int op = x;
        while(op>0){
            numDigit++;
            op /= 10;
        }
        int i = 0;
        while(i<(numDigit/2)){
//            System.out.println(x);
            int head = x/(int)(Math.pow(10.0, numDigit-2*i-1))%10;
            int tail = x%10;
//            System.out.println("head:"+head+", tail:"+tail);
            if(head!=tail) return false;
            x /= 10;
            i++;
        }
        return true;
    }
    public static boolean isPalindrome(int x) {
        if(x<0) return false;
        if(x<10) return true;
        if(x%10==0) return false;
        int reverse = 0;
        while(x>=10 && reverse<x){
            reverse *= 10;
            reverse += x%10;
            x /= 10;
        }
        return reverse==x || reverse/10==x;
    }
}
