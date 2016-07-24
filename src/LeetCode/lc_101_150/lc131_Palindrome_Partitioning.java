package LeetCode.lc_101_150;

import LeetCode.util.Printer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tin on 7/23/16.
 */
public class lc131_Palindrome_Partitioning {
    public static void main(String[] args) {
        Printer.printListList(partition("aab"));
    }
    public static List<List<String>> partition(String s) {
        boolean[][] isPalindrom = palindromes(s);
        List<List<String>> res = new ArrayList<>();
        List<String> op = new ArrayList<>();
        partitionHelper(isPalindrom, s, 0, op, res);
        return res;
    }
    private static void partitionHelper(boolean[][] isPalindrom, String s, int start,
                                 List<String> op, List<List<String>> res){
        if(start==s.length()){
            res.add(new ArrayList<>(op));
            return;
        }
        for(int i=start;i<s.length();i++){
            if(isPalindrom[start][i]){
                op.add(s.substring(start, i+1));
                partitionHelper(isPalindrom, s, i+1, op, res);
                op.remove(op.size()-1);
            }
        }
    }
    private static boolean[][] palindromes(String s){
        int len = s.length();
        boolean[][] isPalindrom = new boolean[len][len];
        for(int i=len-1;i>=0;i--){
            for(int j=i;j<len;j++){
                if(i==j) isPalindrom[i][j] = true;
                else if(s.charAt(i)==s.charAt(j)){
                    if(j-i==1) isPalindrom[i][j] = isPalindrom[i][j-1];
                    else isPalindrom[i][j] = isPalindrom[i+1][j-1];
                }
            }
        }
        return isPalindrom;
    }
}
