package LeetCode.lc_101_150;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tin on 7/24/16.
 */
public class lc138_Copy_List_with_Random_Pointer {
    static class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    };
    public static void main(String[] args) {
        RandomListNode n1 = new RandomListNode(-1);
        RandomListNode n11 = new RandomListNode(1);
        n1.next = n11;
        RandomListNode ans = copyRandomList(n1);
    }
    public static RandomListNode copyRandomList(RandomListNode head) {
        if(head==null) return null;
        RandomListNode op = head;
        // clone to next
        while(op!=null){
            RandomListNode cloned = new RandomListNode(op.label);
            cloned.next = op.next;
            op.next = cloned;
            op = op.next.next;
        }
        // assign next, randon
        op = head;
        while(op!=null){
            RandomListNode cloned = op.next;
//            RandomListNode oriNext = cloned.next;
//            if(op.next.next!=null) cloned.next = op.next.next.next;
            if(op.random!=null) cloned.random = op.random.next;
            op = cloned.next;
        }
        // detach cloned nodes
        RandomListNode res = head.next;
        op = head;
        while(op!=null){
            RandomListNode cloned = op.next;
            op.next = cloned.next;
            if(op.next!=null) cloned.next = op.next.next;
            op = op.next;
        }
        return res;
    }
    public static RandomListNode copyRandomList1(RandomListNode head) {
        RandomListNode op = head;
        Map<RandomListNode, RandomListNode> toCloned = new HashMap<>();
        while(op!=null){
            toCloned.put(op, new RandomListNode(op.label));
            op = op.next;
        }
        op = head;
        while(op!=null){
            RandomListNode cloned = toCloned.get(op);
            if(op.next!=null) cloned.next = toCloned.get(op.next);
            if(op.random!=null) cloned.random = toCloned.get(op.random);
            op = op.next;
        }
        return toCloned.get(head);
    }
}
