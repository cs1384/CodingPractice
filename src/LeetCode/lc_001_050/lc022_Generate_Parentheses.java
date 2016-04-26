package LeetCode.lc_001_050;

import java.util.LinkedList;
import java.util.List;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 4/19/16.
 */
public class lc022_Generate_Parentheses {
    public static void main(String[] args) {
        Printer.printList(generateParenthesis(3));
    }
    public static List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        if(n<=0) return res;
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        generateParenthesisDFS(sb, 1, 0, n, res);
        return res;
    }
    private static void generateParenthesisDFS(StringBuilder sb, int open, int close, int need, List<String> res){
        if(open==need && close==need) {
            res.add(sb.toString());
            return;
        }
        if(open<need){
            sb.append('(');
            generateParenthesisDFS(sb, open+1, close, need, res);
            sb.deleteCharAt(sb.length()-1);
        }
        if(close<open){
            sb.append(')');
            generateParenthesisDFS(sb, open, close+1, need, res);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
