package LeetCode.lc_151_200;

import LeetCode.util.ListNode;

/**
 * Created by Tin on 7/28/16.
 */
public class lc160_Intersection_of_Two_Linked_Lists {
    public static void main(String[] args) {

    }
    /*
    Could count the nodes of each path and let the longer one go first till
    they are even length, then go together. But I tried different approach here.
     Faster!!
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode opA = headA;
        ListNode opB = headB;
        while(opA!=null && opB!=null){
            opA = opA.next;
            opB = opB.next;
        }
        ListNode goFirst = opA==null?headB:headA;
        ListNode goSecond = opA==null?headA:headB;
        opA = opA==null?opB:opA;
        while(opA!=null){
            opA = opA.next;
            goFirst = goFirst.next;
        }
        while(goFirst!=goSecond){
            goFirst = goFirst.next;
            goSecond = goSecond.next;
        }
        return goFirst;
    }
}
