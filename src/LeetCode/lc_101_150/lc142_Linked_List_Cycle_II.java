package LeetCode.lc_101_150;

import LeetCode.util.ListNode;

/**
 * Created by Tin on 7/19/16.
 */
public class lc142_Linked_List_Cycle_II {
    public static void main(String[] args) {

    }
    /*
    Assume the cycle has length = K and the rest part has length = L.
    When slow pointer first enters the cycle:
    if K>L:
        fast has traveled 2L and thus the distance between fast and slow is L
        or you can say K-L.
        In the cycle: slow/start -> L -> fast -> K-L -> slow/start
        if slow goes K-L further, fast will go 2(K-L) and catch up with slow
        In the cycle: start -> K-L -> slow/fast -> L -> start
        Put slow back to head and make both pointers travel at the same pace,
        they will meet at start.
    if L>K:
        slow enters cycle
        In the cycle: slow/start -> L%K -> fast -> K-L%K -> slow/start
        fast catches up with slow
        In the cycle: start -> K-L%K -> slow/fast -> L%K -> start
        L = X*K + L%K, so if you put slow back to head and travel then at same
        pace, they will meet at start
        fast: fast -> L%K -> start -> X*K -> start
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while(fast!=null){
            if(fast.next!=null) fast = fast.next.next;
            else return null;
            slow = slow.next;
            if(slow==fast) break;
        }
        if(fast==null) return null;
        slow = head;
        while(slow!=fast){
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
}
