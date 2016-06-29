package LeetCode.lc_051_100;

import LeetCode.util.ListNode;

import java.util.List;

/**
 * Created by Tin on 6/26/16.
 */
public class lc092_Reverse_Linked_List_II {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        reverseBetween(head, 2, 4).print();
    }
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        for(int i=1;i<m;i++) pre = pre.next;
        ListNode op = pre.next;
        int times = n-m;
        for(int i=0;i<times;i++){
            ListNode swapToHead = op.next;
            op.next = swapToHead.next;
            swapToHead.next = pre.next;
            pre.next = swapToHead;
        }
        return dummy.next;
    }
}
