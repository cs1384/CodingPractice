package LeetCode.lc_001_050;

import java.util.Stack;

/**
 * Created by ytliu on 4/19/16.
 */
public class lc020_Valid_Parentheses {
    public static void main(String[] args) {
        System.out.println(isValid("()[{}]"));
        System.out.println(isValid("()[{}"));
        System.out.println(isValid("()[{]}"));
    }
    public static boolean isValid(String s) {
        Stack<Character> open = new Stack<Character>();
        for(char c : s.toCharArray()){
            if(c=='(' || c=='[' || c=='{'){
                open.push(c);
            }else{
                if(open.isEmpty()) return false;
                char last = open.pop();
                switch (c){
                    case ')':
                        if(last!='(') return false;
                        break;
                    case ']':
                        if(last!='[') return false;
                        break;
                    case '{':
                        if(last!='}') return false;
                        break;
                }
            }
        }
        if(!open.isEmpty()) return false;
        return true;
    }
}
