package LeetCode.lc_101_150;

import LeetCode.util.TreeNode;

/**
 * Created by Tin on 7/9/16.
 */
public class lc112_Path_Sum {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        System.out.println(hasPathSum(n1, 1));
        n1.left = n2;n1.right = n3;
        System.out.println(hasPathSum(n1, 4));
        n2.left = n4;
        System.out.println(hasPathSum(n1, 7));
        n4.right = n5;
        System.out.println(hasPathSum(n1, 13));
    }
    public static boolean hasPathSum(TreeNode root, int sum) {
        return hasPathSumHelper(root, sum, 0);
    }
    public static boolean hasPathSumHelper(TreeNode root, int sum, int cur) {
        if(root==null) return false;
        if(root.left==null && root.right==null) return sum==(cur+root.val);
        return hasPathSumHelper(root.left, sum, cur+root.val) ||
                hasPathSumHelper(root.right, sum, cur+root.val);
    }

}
