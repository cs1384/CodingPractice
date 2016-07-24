package LeetCode.lc_101_150;

/**
 * Created by Tin on 7/6/16.
 */
public class lc117_Populating_Next_Right_Pointers_in_Each_Node_II {
    static class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }
    public static void main(String[] args) {
        TreeLinkNode n9 = new TreeLinkNode(9);
        TreeLinkNode n3 = new TreeLinkNode(3);
        TreeLinkNode n2 = new TreeLinkNode(2);
        TreeLinkNode n4 = new TreeLinkNode(4);
        TreeLinkNode n42 = new TreeLinkNode(42);
        TreeLinkNode n6 = new TreeLinkNode(6);
        TreeLinkNode n5 = new TreeLinkNode(5);
        n9.left = n3;n9.right = n2;
        n3.right = n4;
        n2.left = n42;
        n4.left = n6;
        n42.left = n5;
        connect(n9);
    }
    public static void connect(TreeLinkNode root) {
        TreeLinkNode start = root;
        while(start!=null){
            TreeLinkNode op = start;
            start = null;
            TreeLinkNode pre = null;
            while(op!=null){
                if(op.left!=null){
                    if(pre==null) start = op.left;
                    else pre.next = op.left;
                    pre = op.left;
                }
                if(op.right!=null){
                    if(pre==null) start = op.right;
                    else pre.next = op.right;
                    pre = op.right;
                }
                op = op.next;
            }
        }
    }
}
