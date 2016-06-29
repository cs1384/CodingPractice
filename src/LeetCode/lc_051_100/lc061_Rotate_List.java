package LeetCode.lc_051_100;

import LeetCode.util.ListNode;

/**
 * Created by Tin on 6/15/16.
 */
public class lc061_Rotate_List {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        rotateRight(head,7).print();
    }
    public static ListNode rotateRight(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        int count = 0;
        ListNode last = dummy;
        while(last.next !=null){
            last = last.next;
            count++;
        }
        if(count==0 || count==1) return dummy.next;
        k = k%count;
        count -= k;
        ListNode newLast = dummy;
        while(count>0){
            newLast = newLast.next;
            count--;
        }
        last.next = dummy.next;
        dummy.next = newLast.next;
        newLast.next = null;
        return dummy.next;
    }
}
