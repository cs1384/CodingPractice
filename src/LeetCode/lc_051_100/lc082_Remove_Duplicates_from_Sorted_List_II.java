package LeetCode.lc_051_100;

import LeetCode.util.ListNode;

/**
 * Created by Tin on 6/23/16.
 */
public class lc082_Remove_Duplicates_from_Sorted_List_II {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next = new ListNode(4);
        deleteDuplicates(head).print();
    }
    public static ListNode deleteDuplicates1(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        while(head!=null){
            if(head.next!=null && head.val==head.next.val){
                while(head.next!=null && head.val==head.next.val){
                    head = head.next;
                }
                pre.next = head.next;
            }else {
                pre = head;
            }
            head = head.next;
        }
        return dummy.next;
    }
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        while(head!=null){
            while(head.next!=null && head.val==head.next.val){
                head = head.next;
            }
            if(pre.next!=head) pre.next = head.next;
            else pre = head;
            head = head.next;
        }
        return dummy.next;
    }
}
