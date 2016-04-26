package LeetCode.lc_001_050;

import LeetCode.util.ListNode;

/**
 * Created by ytliu on 4/19/16.
 */
public class lc021_Merge_Two_Sorted_Lists {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        mergeTwoLists(n1, null).print();
    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode op = dummy;
        while(l1!=null && l2!=null){
            if(l1.val<l2.val) {
                op.next = l1;
                l1 = l1.next;
            } else {
                op.next = l2;
                l2 = l2.next;
            }
            op = op.next;
        }
        op.next = l1==null?l2:l1;
        return dummy.next;
    }
}
