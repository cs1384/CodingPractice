package LeetCode.lc_201_250;

import LeetCode.util.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Tin on 7/5/16.
 */
public class lc235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree {
    public static void main(String[] args) {
        TreeNode n6 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2);
        TreeNode n8 = new TreeNode(8);
        TreeNode n0 = new TreeNode(0);
        TreeNode n4 = new TreeNode(4);
        TreeNode n7 = new TreeNode(7);
        TreeNode n9 = new TreeNode(9);
        TreeNode n3 = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        n6.left = n2;n6.right = n8;
        n2.left = n0;n2.right = n4;
        n8.left = n7;n8.right = n9;
        n4.left = n3;n4.right = n5;
        System.out.println(lowestCommonAncestor(n6, n2, n8));
        System.out.println(lowestCommonAncestor(n6, n2, n4));
    }
    /*
    Take advantage of the fact of Binary Search Tree
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val>q.val) return lowestCommonAncestor(root, q, p);
        while(root.val>q.val || root.val<p.val)
            root = root.val>q.val?root.left:root.right;
        return root;
    }
    public static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return null;
        if(root.val==p.val || root.val==q.val) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left!=null && right!=null) return root;
        return left==null?right:left;
    }
}
