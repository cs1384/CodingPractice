package LeetCode.lc_101_150;

import LeetCode.util.TreeNode;

/**
 * Created by Tin on 7/16/16.
 */
public class lc124_Binary_Tree_Maximum_Path_Sum {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(-1);
        TreeNode t2 = new TreeNode(-1);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;t1.right = t3;
        System.out.println(maxPathSum(t1));
    }
    private static int max = Integer.MIN_VALUE;
    public static int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        maxPathSumHelper(root);
        return max;
    }
    private static int maxPathSumHelper(TreeNode root){
        if(root==null) return 0;
        int leftMax = maxPathSumHelper(root.left);
        int rightMax = maxPathSumHelper(root.right);
        int nodeMax = root.val + (leftMax>0?leftMax:0) + (rightMax>0?rightMax:0);
        max = Math.max(max, nodeMax);
        int maxWithChild = root.val + Math.max(leftMax, rightMax);
        return Math.max(root.val, maxWithChild);
    }
}
