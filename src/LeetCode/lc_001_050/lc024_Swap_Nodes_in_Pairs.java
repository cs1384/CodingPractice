package LeetCode.lc_001_050;

import LeetCode.util.ListNode;

/**
 * Created by ytliu on 4/22/16.
 */
public class lc024_Swap_Nodes_in_Pairs {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        listNode1.next = listNode2;
        ListNode listNode3 = new ListNode(3);
        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(4);
//        listNode3.next = listNode4;
        swapPairs(listNode1).print();
    }
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode op = head;
        while(op!=null && op.next!=null){
            ListNode temp = op.next;
            // change connection
            pre.next = temp;
            op.next = temp.next;
            temp.next = op;
            //prepare for next round
            pre = op;
            op = op.next;
        }
        return dummy.next;
    }
}
