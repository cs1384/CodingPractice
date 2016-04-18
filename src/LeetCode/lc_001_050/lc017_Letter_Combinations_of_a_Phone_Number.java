package LeetCode.lc_001_050;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 4/17/16.
 */
public class lc017_Letter_Combinations_of_a_Phone_Number {
    public static void main(String[] args) {
        Printer.printList(letterCombinations(""));
        Printer.printList(letterCombinations("23"));
    }
    public static List<String> letterCombinations(String digits){
        if(digits==null || digits.length()==0) return Collections.emptyList();
        Map<Integer, String> numberToChars = new HashMap<>();
        numberToChars.put(2, "abc");
        numberToChars.put(3, "def");
        numberToChars.put(4, "ghi");
        numberToChars.put(5, "jkl");
        numberToChars.put(6, "mno");
        numberToChars.put(7, "pqrs");
        numberToChars.put(8, "tuv");
        numberToChars.put(9, "wxyz");
        List<String> res = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        letterCombinationsDFS(digits, 0, numberToChars, sb, res);
        return res;
    }
    private static void letterCombinationsDFS(String digits, int index, Map<Integer, String> ref, StringBuilder op, List<String> res){
        if(index==digits.length()){
            res.add(op.toString());
            return;
        }
        String chars = ref.get(digits.charAt(index)-'0');
        for(char c : chars.toCharArray()){
            op.append(c);
            letterCombinationsDFS(digits, index+1, ref, op, res);
            op.deleteCharAt(op.length()-1);
        }
    }
 }
