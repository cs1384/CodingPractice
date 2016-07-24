package LeetCode.lc_201_250;

import java.util.Stack;

/**
 * Created by Tin on 7/19/16.
 */
public class lc232_Implement_Queue_using_Stacks {
    public static void main(String[] args) {

    }
    class MyQueue {
        Stack<Integer> main = new Stack<>();
        Stack<Integer> reverse = new Stack<>();
        // Push element x to the back of queue.
        public void push(int x) {
            main.push(x);
        }

        // Removes the element from in front of queue.
        public void pop() {
            peek();
            reverse.pop();
        }

        // Get the front element.
        public int peek() {
            if(reverse.isEmpty()){
                while(!main.isEmpty()){
                    reverse.push(main.pop());
                }
            }
            return reverse.peek();
        }

        // Return whether the queue is empty.
        public boolean empty() {
            return main.isEmpty() && reverse.isEmpty();
        }
    }
}
