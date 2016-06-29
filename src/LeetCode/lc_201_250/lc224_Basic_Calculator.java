package LeetCode.lc_201_250;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Tin on 6/17/16.
 */
public class lc224_Basic_Calculator {
    public static void main(String[] args) {
        System.out.println(calculate3("1 + 1"));
        System.out.println(calculate3(" 2-1 + 2 "));
        System.out.println(calculate3("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(calculate3("4+4-4-(4+3+(4+5))+3"));
    }
    /*
    but actually we can add sign effect by multiplying 1/-1
    https://leetcode.com/discuss/77406/java-easy-version-to-understand
     */
    public static int calculate3(String s) {
        int sign = 1, result = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = c-'0';
                while(i+1<s.length() && Character.isDigit(s.charAt(i+1))) {
                    num *= 10;
                    num += s.charAt(++i) - '0';
                }
                result += num * sign;
            }else if(c!=' '){
                switch (c) {
                    case '(':
                        stack.push(result);
                        stack.push(sign);
                        result = 0;
                        sign = 1;
                        break;
                    case ')':
                        result = result*stack.pop()+stack.pop();
                        break;
                    default:
                        sign = c=='+'?1:-1;
                }
            }
        }
        return result;
    }
    /*
    based on current effect, decide to add/minus num along the way
     */
    public static int calculate2(String s) {
        Stack<Boolean> effect = new Stack<>();
        effect.push(true);
        int i = 0;
        int res = 0;
        boolean op = true; // +:true, -:false
        while(i<s.length()){
            if(s.charAt(i)==' '){
                i++;
            }else if(Character.isDigit(s.charAt(i))) {
                int num = 0;
                while(i<s.length() && Character.isDigit(s.charAt(i))) {
                    num *= 10;
                    num += s.charAt(i++) - '0';
                }
                if(!effect.peek()) res -= op?num:-num;
                else res += op?num:-num;
            }else{
                char c = s.charAt(i++);
                switch (c) {
                    case '(':
                        // f + f -> t
                        // f + t -> f
                        // t + f -> f
                        // t + t -> t
                        effect.push(!(effect.peek()^op));
                        op = true;
                        break;
                    case ')':
                        effect.pop();
                        break;
                    default:
                        op = c=='+';
                }
            }
        }
        return res;
    }
    /*
    basic intuitive solution
     */
    public static int calculate(String s) {
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        s = "("+s+")";
        int i = 0;
        while(i<s.length()){
            if(s.charAt(i)==' '){
                i++;
            }else if(Character.isDigit(s.charAt(i))){
                int num = 0;
                while(Character.isDigit(s.charAt(i))){
                    num *= 10;
                    num += s.charAt(i++)-'0';
                }
                nums.push(num);
            }else{
                char c = s.charAt(i);
                if(c==')') calculateParentheses(nums, ops);
                else ops.push(c);
                i++;
            }
        }
        return nums.pop();
    }
    private static void calculateParentheses(Stack<Integer> nums, Stack<Character> ops){
        int res = 0;
        char op = ')';
        while(op!='('){
            int num = nums.pop();
            op = ops.pop();
            if(op=='-'){
                res -= num;
            }else{
                res += num;
            }
        }
        nums.push(res);
    }
}
