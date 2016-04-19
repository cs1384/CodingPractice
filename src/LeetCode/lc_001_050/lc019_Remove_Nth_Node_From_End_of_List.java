package LeetCode.lc_001_050;

import LeetCode.util.ListNode;

/**
 * Created by ytliu on 4/18/16.
 */
public class lc019_Remove_Nth_Node_From_End_of_List {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;

        removeNthFromEnd(node1, 0).print();
    }
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(n<=0) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        for(int i=0;i<=n;i++){
            if(fast==null) return dummy.next;
            fast = fast.next;
        }
        while(fast!=null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
