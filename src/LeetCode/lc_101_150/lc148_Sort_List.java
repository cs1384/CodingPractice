package LeetCode.lc_101_150;

import LeetCode.util.ListNode;
import com.sun.tools.javac.util.List;

/**
 * Created by Tin on 7/26/16.
 */
public class lc148_Sort_List {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        l3.next = l1;
        l1.next = l2;
        l2.next = l4;
        sortList(l3).print();

        ListNode head = new ListNode(4);
        head.next = new ListNode(19);
        head.next.next = new ListNode(14);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(-3);
        head.next.next.next.next.next = new ListNode(1);
        head.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next.next.next = new ListNode(11);
        head.next.next.next.next.next.next.next.next.next = new ListNode(15);
        sortList(head).print();
    }
    /*
    Merge sort -> O(NlogN)
     */
    public static ListNode sortList(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode a = sortList(slow.next);
        slow.next = null;
        ListNode b = sortList(head);
        ListNode dummy = new ListNode(-1);
        ListNode op = dummy;
        while(a!=null && b!=null){
            if(a.val<b.val){
                op.next = a;
                a = a.next;
            }else{
                op.next = b;
                b = b.next;
            }
            op = op.next;
        }
        op.next = a==null?b:a;
        return dummy.next;
    }
    /*
    Quick Sort, worst case O(N^2) -> TLE
     */
    public static ListNode sortList1(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        sortListHelper(dummy, null);
        return dummy.next;
    }
    private static void sortListHelper(ListNode dummy, ListNode end){
        ListNode pivot = dummy.next;
        if(pivot==end) return;
        ListNode op = pivot;
        while(op.next!=end){
            ListNode listNode = op.next;
            if(listNode!=null && listNode.val<=pivot.val){
                op.next = listNode.next;
                listNode.next = dummy.next;
                dummy.next = listNode;
            }else{
                op = op.next;
            }
        }
        dummy.print();
        pivot.print();
        System.out.println("recusive");
        sortListHelper(dummy, pivot);
        sortListHelper(pivot, end);
    }

}
