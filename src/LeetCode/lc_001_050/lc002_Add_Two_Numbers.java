package LeetCode.lc_001_050;

import java.util.List;

import LeetCode.util.ListNode;
import LeetCode.util.Printer;

/**
 * Created by ytliu on 3/12/16.
 */
public class lc002_Add_Two_Numbers {
    public static void main(String[] args) {
        ListNode num1 = new ListNode(2);
        num1.next = new ListNode(4);
        num1.next.next = new ListNode(3);

        ListNode num2 = new ListNode(5);
        num2.next = new ListNode(6);
        num2.next.next = new ListNode(4);

        Printer.printListNode(addTwoNumbers(num1, num2));
    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode op = dummy;
        boolean carry = false;
        while(l1!=null || l2!=null){
            int n1 = l1==null?0:l1.val;
            int n2 = l2==null?0:l2.val;
            int sum = n1+n2+(carry?1:0);
            if(sum>=10){
                op.next = new ListNode(sum-10);
                carry = true;
            }else {
                op.next = new ListNode(sum);
                carry = false;
            }
            op = op.next;
            l1 = l1==null?null:l1.next;
            l2 = l2==null?null:l2.next;
        }
        if(carry) op.next = new ListNode(1);
        return dummy.next;
    }

}
