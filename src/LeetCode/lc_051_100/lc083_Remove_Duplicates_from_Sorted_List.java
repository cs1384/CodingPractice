package LeetCode.lc_051_100;

import LeetCode.util.ListNode;

/**
 * Created by Tin on 6/22/16.
 */
public class lc083_Remove_Duplicates_from_Sorted_List {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        deleteDuplicates(head).print();
    }
    public static ListNode deleteDuplicates(ListNode head) {
        if(head==null) return head;
        ListNode dummy = new ListNode(head.val==-1?0:-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode op = head;
        while(op!=null){
            if(op.val==pre.val){
                pre.next = op.next;
            }else{
                pre = op;
            }
            op = op.next;
        }
        return dummy.next;
    }
}
