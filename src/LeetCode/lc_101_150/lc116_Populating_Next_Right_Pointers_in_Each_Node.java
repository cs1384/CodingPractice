package LeetCode.lc_101_150;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Tin on 7/13/16.
 */
public class lc116_Populating_Next_Right_Pointers_in_Each_Node {
    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }
    public static void main(String[] args) {

    }
    /*
    Recursive
     */
    public void connect(TreeLinkNode root) {
        if(root==null) return;
        if(root.left!=null) root.left.next = root.right;
        if(root.right!=null) root.right.next = root.next==null?null:root.next.left;
        connect(root.left);
        connect(root.right);
    }
    /*
    Iterative
     */
    public void connect1(TreeLinkNode root) {
        if(root==null) return;
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeLinkNode treeLinkNode = queue.poll();
            if(treeLinkNode.left!=null) {
                treeLinkNode.left.next = treeLinkNode.right;
                queue.offer(treeLinkNode.left);
            }
            if(treeLinkNode.right!=null) {
                treeLinkNode.right.next = treeLinkNode.next == null ? null : treeLinkNode.next.left;
                queue.offer(treeLinkNode.right);
            }
        }
    }
}
