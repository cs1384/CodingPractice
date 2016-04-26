package LeetCode.lc_001_050;

import LeetCode.util.ListNode;

/**
 * Created by ytliu on 4/22/16.
 */
public class lc025_Reverse_Nodes_in_kGroup {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        listNode1.next = listNode2;
        ListNode listNode3 = new ListNode(3);
        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(4);
        listNode3.next = listNode4;
        ListNode listNode5 = new ListNode(5);
        listNode4.next = listNode5;
        reverseKGroup(listNode1, 3).print();
    }
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        while(pre.next!=null){
            ListNode op = pre.next;
            int res = reverse(pre, k);
            if(res!=k){
                reverse(pre, res);
                break;
            }
            pre = op;
        }
        return dummy.next;
    }
    private static int reverse(ListNode pre, int times){
        ListNode op = pre.next;
        int i = 1;
        while(i<times && op.next!=null){
            ListNode node = op.next;
            op.next = node.next;
            node.next = pre.next;
            pre.next = node;
            i++;
        }
        return i;
    }
}
