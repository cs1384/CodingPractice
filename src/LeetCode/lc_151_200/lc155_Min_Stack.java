package LeetCode.lc_151_200;

import java.util.Stack;

/**
 * Created by Tin on 7/8/16.
 */
public class lc155_Min_Stack {
    public static void main(String[] args) {

    }
    public class MinStack {
        Stack<Integer> main;
        Stack<Integer> min;
        public MinStack() {
            main = new Stack<>();
            min = new Stack<>();
        }
        public void push(int x) {
            if(min.isEmpty() || min.peek()>x) min.push(x);
            main.push(x);
        }
        public void pop() {
            int out = main.pop();
            if(min.peek()==out) min.pop();
        }
        public int top() {
            return main.peek();
        }
        public int getMin() {
            return min.peek();
        }
    }
}
