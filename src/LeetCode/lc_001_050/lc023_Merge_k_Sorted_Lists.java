package LeetCode.lc_001_050;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import LeetCode.util.ListNode;

/**
 * Created by ytliu on 4/21/16.
 */
public class lc023_Merge_k_Sorted_Lists {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode[] listNodes = new ListNode[3];
        listNodes[0] = listNode1;
        listNode2.next = listNode3;
        listNodes[1] = listNode2;
        listNode4.next = listNode5;
        listNodes[2] = listNode4;
        mergeKLists(listNodes).print();
    }
    /*
    TC:O(M), SC:O(1), M==number of nodes
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if(len==0) return null;
        return mergeKListsHelper(lists, 0, len-1);
    }
    private static ListNode mergeKListsHelper(ListNode[] lists, int left, int right){
        if(left==right){
            return lists[left];
        }else {
            int mid = (left+right)/2;
            return lc021_Merge_Two_Sorted_Lists.mergeTwoLists(mergeKListsHelper(lists, left, mid),
                    mergeKListsHelper(lists, mid+1, right));
        }
    }
    /*
    TC: O(NlogN), SC: O(N), N==number of lists
     */
    public static ListNode mergeKLists1(ListNode[] lists) {
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(((ListNode)o1).val > ((ListNode)o2).val){
                    return 1;
                }else if(((ListNode)o1).val < ((ListNode)o2).val){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        for(ListNode listNode : lists){
            if(listNode!=null) priorityQueue.offer(listNode);
        }
        ListNode dummy = new ListNode(-1);
        ListNode op = dummy;
        while(!priorityQueue.isEmpty()){
            ListNode node = priorityQueue.poll();
            op.next = node;
            if(node.next!=null) priorityQueue.offer(node.next);
            op = op.next;
        }
        return dummy.next;
    }
}
