package LeetCode.lc_101_150;

import LeetCode.util.TreeNode;

import java.util.Map;

/**
 * Created by Tin on 7/9/16.
 */
public class lc110_Balanced_Binary_Tree {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;n1.right = n3;
        n2.left = n4;
        System.out.println(isBalanced(n1));
        n4.right = n5;
        System.out.println(isBalanced(n1));
    }
    public static boolean isBalanced(TreeNode root) {
        return balancedHeight(root)==-1?false:true;
    }
    private static int balancedHeight(TreeNode root){
        if(root==null) return 0;
        int leftH = balancedHeight(root.left);
        int rightH = balancedHeight(root.right);
        if(leftH==-1 || rightH==-1) return -1;
        if(Math.abs(leftH-rightH)>1) return -1;
        return Math.max(leftH, rightH)+1;
    }
}
