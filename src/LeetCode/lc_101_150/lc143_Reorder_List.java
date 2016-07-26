package LeetCode.lc_101_150;

import LeetCode.util.ListNode;

/**
 * Created by Tin on 7/25/16.
 */
public class lc143_Reorder_List {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        reorderList(l1);
        l1.print();
    }
    /*
    It is faster to find middle by two pointers
     */
    public static void reorderList(ListNode head) {
        if(head==null) return;
        // find middle point: slow
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        if(slow.next==null) return;
        // reverse the second half
        ListNode op = slow.next;
        while(op.next!=null){
            ListNode move = op.next;
            op.next = move.next;
            move.next = slow.next;
            slow.next = move;
        }
        // alternatively connect two parts
        ListNode first = head;
        ListNode second = slow.next;
        slow.next = null;
        while(first!=null){
            ListNode temp = first.next;
            first.next = second;
            first = temp;
            if(second==null) break;
            temp = second.next;
            second.next = first;
            second = temp;
        }
    }
    public static void reorderList1(ListNode head) {
        if(head==null) return;
        // count number of nodes
        int nNode = 0;
        ListNode op = head;
        while(op!=null){
            nNode++;
            op = op.next;
        }
        if(nNode==1) return;
        // reverse the second half
        ListNode dummy = new ListNode(-1);
        op = head;
        for(int i=0;i<nNode/2+(nNode%2==0?0:1);i++) op = op.next;
        dummy.next = op;
        while(op.next!=null){
            ListNode move = op.next;
            op.next = move.next;
            move.next = dummy.next;
            dummy.next = move;
        }
        dummy.print();
        // alternatively connect two parts
        ListNode first = head;
        ListNode second = dummy.next;
        while(first!=null){
            ListNode temp = first.next;
            first.next = second;
            first = temp;
            if(second==null) break;
            temp = second.next;
            second.next = first;
            second = temp;
        }
    }
}
