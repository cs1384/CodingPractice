package LeetCode.lc_101_150;

import LeetCode.util.ListNode;
import LeetCode.util.TreeNode;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

/**
 * Created by Tin on 7/9/16.
 */
public class lc109_Convert_Sorted_List_to_Binary_Search_Tree {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        ListNode l7 = new ListNode(7);
        l1.next = l2;
//        l2.next = l3;
//        l3.next = l4;
//        l4.next = l5;
//        l5.next = l6;
//        l6.next = l7;
        System.out.println(sortedListToBST(l1));

    }
    public static TreeNode sortedListToBST(ListNode head) {
        int nNode = 0;
        ListNode op = head;
        while(op!=null){
            op = op.next;
            nNode++;
        }
        ListNode getNode = new ListNode(-1);
        getNode.next = head;
        return sortedListToBSTHelper(getNode, 1, nNode);
    }
    private static TreeNode sortedListToBSTHelper(ListNode getNode, int from, int to){
        if(from>to) return null;
        int mid = (from+to)/2;
        TreeNode left = sortedListToBSTHelper(getNode, from, mid-1);
        TreeNode root = new TreeNode(getNode.next.val);
        getNode.next = getNode.next.next;
        TreeNode right = sortedListToBSTHelper(getNode, mid+1, to);
        root.left = left;
        root.right = right;
        return root;
    }
    private static TreeNode sortedListToBSTHelper1(ListNode getNode, int from, int to){
        if(from>to) return null;
        else if(from==to){
            int value = getNode.next.val;
            getNode.next = getNode.next.next;
            return new TreeNode(value);
        }else{
            int mid = (from+to)/2;
            TreeNode left = sortedListToBSTHelper1(getNode, from, mid-1);
            TreeNode root = new TreeNode(getNode.next.val);
            getNode.next = getNode.next.next;
            TreeNode right = sortedListToBSTHelper1(getNode, mid+1, to);
            root.left = left;
            root.right = right;
            return root;
        }
    }

}
