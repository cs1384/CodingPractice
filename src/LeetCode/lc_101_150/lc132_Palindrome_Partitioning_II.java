package LeetCode.lc_101_150;

import LeetCode.util.Printer;

import java.util.Arrays;

/**
 * Created by Tin on 7/23/16.
 */
public class lc132_Palindrome_Partitioning_II {
    public static void main(String[] args) {
        System.out.println(minCut("aab"));//1
        System.out.println(minCut("abbab"));//1
        System.out.println(minCut("a"));//0
        System.out.println(minCut("ab"));//1
    }
    public static int minCut(String s) {
        int len = s.length();
        int[] cut = new int[len+1];
        for(int i=0;i<=len;i++) cut[i] = i-1;
        for(int i=0;i<len;i++){
            for(int j=0;i-j>=0&&i+j<len&&s.charAt(i-j)==s.charAt(i+j);j++){
                cut[i+j+1] = Math.min(cut[i+j+1], cut[i-j]+1);
            }
            for(int j=1;i+1-j>=0&&i+j<len&&s.charAt(i+1-j)==s.charAt(i+j);j++){
                cut[i+j+1] = Math.min(cut[i+j+1], cut[i+1-j]+1);
            }
        }
        return cut[len];
    }
    public static int minCut2(String s) {
        int len = s.length();
        int[] cut = new int[len];
        boolean[][] isPalindrome = new boolean[len][len];
        for(int i=0;i<len;i++){
            int min = Integer.MAX_VALUE;
            for(int j=0;j<=i;j++){
                if(s.charAt(i)==s.charAt(j) && (i-j<=1 || isPalindrome[j+1][i-1])){
                    isPalindrome[j][i] = true;
                    min = Math.min(min, 1+(j==0?0:cut[j-1]));
                }
            }
            cut[i] = min;
        }
        return cut[len-1]-1;
    }
    public static int minCut1(String s) {
        boolean[][] isPalindrome = palindromes(s);
        int len = s.length();
        int[] minCut = new int[len+1];
        Arrays.fill(minCut, Integer.MAX_VALUE);
        minCut[0] = 0;
        for(int i=1;i<=len;i++){
            if(isPalindrome[0][i-1]){
                minCut[i] = 1;
                continue;
            }
            for(int j=1;j<i;j++){
                if(minCut[j]!=Integer.MAX_VALUE && isPalindrome[j][i-1]){
                    minCut[i] = Math.min(minCut[i], minCut[j]+1);
                }
            }
        }
        return minCut[len]-1;
    }
    private static boolean[][] palindromes(String s){
        int len = s.length();
        boolean[][] isPalindrome = new boolean[len][len];
        for(int i=len-1;i>=0;i--){
            for(int j=i;j<len;j++){
                if(i==j) isPalindrome[i][j] = true;
                else if(s.charAt(i)==s.charAt(j)){
                    if(j-i==1) isPalindrome[i][j] = isPalindrome[i][j-1];
                    else isPalindrome[i][j] = isPalindrome[i+1][j-1];
                }
            }
        }
        return isPalindrome;
    }
}
