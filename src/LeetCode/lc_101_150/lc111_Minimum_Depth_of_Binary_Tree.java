package LeetCode.lc_101_150;

import LeetCode.util.TreeNode;

/**
 * Created by Tin on 7/9/16.
 */
public class lc111_Minimum_Depth_of_Binary_Tree {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        n1.left = n2;n1.right = n3;
        n2.left = n4;
        System.out.println(minDepth(n1));
    }
    public static int minDepth(TreeNode root) {
        if(root==null) return 0;
        int leftMD = minDepth(root.left);
        int rightMD = minDepth(root.right);
        if(leftMD!=0 && rightMD!=0) return Math.min(leftMD, rightMD)+1;
        return 1+(leftMD!=0?leftMD:rightMD);
    }
}
