package LeetCode.lc_101_150;

import LeetCode.util.ListNode;

/**
 * Created by Tin on 7/19/16.
 */
public class lc141_Linked_List_Cycle {
    public static void main(String[] args) {

    }
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast!=null){
            if(fast.next!=null) fast = fast.next.next;
            else return false;
            slow = slow.next;
            if(slow==fast) return true;
        }
        return false;
    }
}
