package LeetCode.lc_201_250;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Tin on 7/7/16.
 */
public class lc225_Implement_Stack_using_Queues {
    public static void main(String[] args) {

    }

    class MyStack {
        Queue<Integer> queue = new LinkedList<>();
        // Push element x onto stack.
        public void push(int x) {
            int stayTheSame = queue.size();
            queue.offer(x);
            for(int i=0;i<stayTheSame;i++){
                queue.offer(queue.poll());
            }
        }
        // Removes the element on top of the stack.
        public void pop() {
            queue.poll();
        }
        // Get the top element.
        public int top() {
            return queue.peek();
        }
        // Return whether the stack is empty.
        public boolean empty() {
            return queue.size()==0;
        }
    }
    class MyStack1 {
        Queue<Integer> queue = new LinkedList<>();
        Integer lastPush = null;
        // Push element x onto stack.
        public void push(int x) {
            queue.offer(x);
            lastPush = x;
        }
        // Removes the element on top of the stack.
        public void pop() {
            int putBack = queue.size()-1;
            for(int i=0;i<putBack;i++){
                lastPush = queue.poll();
                queue.offer(lastPush);
            }
            queue.poll();
        }
        // Get the top element.
        public int top() {
            return lastPush;
        }
        // Return whether the stack is empty.
        public boolean empty() {
            return queue.size()==0;
        }
    }

}
