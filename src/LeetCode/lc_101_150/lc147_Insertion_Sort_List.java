package LeetCode.lc_101_150;

import LeetCode.util.ListNode;

import java.util.List;

/**
 * Created by Tin on 7/25/16.
 */
public class lc147_Insertion_Sort_List {
    public static void main(String[] args) {

    }
    public ListNode insertionSortList(ListNode head) {
        if(head==null) return null;
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode rear = head;
        while(rear.next!=null){
            ListNode move = rear.next;
            if(move.val>=rear.val) rear = move;
            else{
                rear.next = move.next;
                ListNode pre = dummy;
                ListNode op = dummy.next;
                while(pre!=rear){
                    if(pre.val<=move.val && move.val<=op.val){
                        pre.next = move;
                        move.next = op;
                        break;
                    }
                    pre = op;
                    op = op.next;
                }
            }
        }
        return dummy.next;
    }
}
