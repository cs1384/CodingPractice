package LeetCode.lc_101_150;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Tin on 7/26/16.
 */
public class lc150_Evaluate_Reverse_Polish_Notation {
    public static void main(String[] args) {
        char c = 'h';
        switch (c){


        }
    }
    public int evalRPN(String[] tokens) {
        Stack<Integer> operands = new Stack<>();
        int a = 0, b = 0;
        for(String s : tokens){
            switch (s){
                case "+":
                    b = operands.pop(); a = operands.pop();
                    operands.push(a+b);
                    break;
                case "-":
                    b = operands.pop(); a = operands.pop();
                    operands.push(a-b);
                    break;
                case "*":
                    b = operands.pop(); a = operands.pop();
                    operands.push(a*b);
                    break;
                case "/":
                    b = operands.pop(); a = operands.pop();
                    operands.push(a/b);
                    break;
                default:
                    operands.push(Integer.parseInt(s));
            }
        }
        return operands.pop();
    }
    public int evalRPN1(String[] tokens) {
        Set<String> operators = new HashSet<>();
        operators.add("+");operators.add("-");
        operators.add("*");operators.add("/");
        Stack<Integer> operands = new Stack<>();
        int a = 0, b = 0;
        for(String s : tokens){
            if(operators.contains(s)){
                b = operands.pop();
                a = operands.pop();
            }
            switch (s){
                case "+":
                    operands.push(a+b);
                    break;
                case "-":
                    operands.push(a-b);
                    break;
                case "*":
                    operands.push(a*b);
                    break;
                case "/":
                    operands.push(a/b);
                    break;
                default:
                    operands.push(Integer.parseInt(s));
            }
        }
        return operands.pop();
    }
}
