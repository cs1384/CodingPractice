package LeetCode.lc_051_100;

import LeetCode.util.ListNode;

/**
 * Created by Tin on 6/24/16.
 */
public class lc086_Partition_List {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        partition(head, 3).print();
    }
    public static ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode append = dummy;
        while(head!=null){
            if(head.val<x){
                if(append.next!=head) {
                    pre.next = head.next;
                    head.next = append.next;
                    append.next = head;
                    append = append.next;
                    head = pre.next;
                }else{
                    append = append.next; //head
                    pre = head;
                    head = head.next;
                }
            }else{
                pre = head;
                head = head.next;
            }
        }
        return dummy.next;
    }

}
