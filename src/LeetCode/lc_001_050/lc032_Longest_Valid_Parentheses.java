package LeetCode.lc_001_050;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 5/1/16.
 */
public class lc032_Longest_Valid_Parentheses {
    public static void main(String[] args) {
        System.out.println(longestValidParentheses("(()")); //2
        System.out.println(longestValidParentheses(")()())")); //4
        System.out.println(longestValidParentheses("()(()")); //2
        System.out.println(longestValidParentheses(")(((((()())()()))()(()))(")); //22
        System.out.println(longestValidParentheses(")()(((())))(")); //10
        System.out.println(longestValidParentheses("))))())()()(()")); //4
    }
    /*
    Original solution
    In longestValidParentheses1, we need a stack to double check backward to see if we can form exactly what we formed by
    creating based on '(', so I wonder why wouldn't we check from both ends directly.
     */
    public static int longestValidParentheses(String s) {
        int[] track = new int[s.length()];
        int open = 0, close = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='('){
                open++;
            }else{
                if(open>close){
                    close++;
                    track[i] = close;
                }else{
                    // if more close parenthesis, then start over, track[i] = 0
                    close=0; open=0;
                }
            }
        }
        open = 0; close = 0;
        for(int i=s.length()-1;i>=0;i--){
            char c = s.charAt(i);
            if(c==')'){
                close++;
            }else{
                if(close>open){
                    open++;
                    track[i] = open;
                }else{
                    // if more open parenthesis, then start over, track[i] = 0
                    close=0; open=0;
                }
            }
        }
        open = 0; close = 0;
        int max = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            // 0 marks the end of non-zero range, so we check how many complete parentheses we can have and start over
            if(track[i]==0){
                max = Math.max(max, Math.min(open, close));
                open = 0; close = 0;
            }else{
                // if in the non-zero range, we track the number of complete parentheses formed based on the finish of open
                // and close parenthesis.
                if(c=='('){
                    open = Math.max(open, track[i]);
                }else{
                    close = Math.max(close, track[i]);
                }
            }
        }
        // last check
        max = Math.max(max, Math.min(open, close));
//        Printer.printArray(track);
        return max*2;
    }
    public static int longestValidParentheses1(String s) {
        int numOpen = 0;
        int numClose = 0;
        int longest = 0;
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            switch (c){
                case '(':
                    numOpen++;
                    // it is likely there's no close parenthesis for this open, so need to check later
                    stack.push(c);
                    break;
                case ')':
                    // if enough open, then keep adding up
                    if(numOpen>numClose){
                        numClose++;
                        stack.push(c);
                    // if no enough open, then we faced a deadend, save candidate longest and start over
                    } else if(numOpen==numClose){
                        longest = Math.max(numOpen, longest);
                        numClose = 0;
                        numOpen = 0;
                        stack.clear();
                    }else{
                        System.out.println("should never be here");
                    }
                    break;
            }
        }
        // only if open and close do not match and potential length is worth trying
        if(numOpen>numClose && numClose>longest){
            int close = 0;
            int max = 0;
            int op = 0;
            // use stach to allow checking backward
            while(!stack.isEmpty()){
                char c = stack.pop();
                if(c=='('){
                    if(close>0){
                        close--;
                        op++;
                    }else{
                        max = Math.max(max, op);
                        op = 0;
                    }
                }else{
                    close++;
                }
            }
            max = Math.max(max, op);
            longest = Math.max(max, longest);
        }else{
            // final check
            longest = Math.max(numClose, longest);
        }
        return longest*2;
    }
}
