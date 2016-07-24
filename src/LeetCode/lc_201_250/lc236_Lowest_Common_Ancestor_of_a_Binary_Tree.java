package LeetCode.lc_201_250;

import LeetCode.util.TreeNode;

import java.util.*;

/**
 * Created by Tin on 7/5/16.
 */
public class lc236_Lowest_Common_Ancestor_of_a_Binary_Tree {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        n1.left = n2;
        System.out.println(lowestCommonAncestor(n1, n1, n2));
    }
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> toParent = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode treeNode = stack.pop();
            if(treeNode.left!=null){
                toParent.put(treeNode.left, treeNode);
                stack.push(treeNode.left);
            }
            if(treeNode.right!=null){
                toParent.put(treeNode.right, treeNode);
                stack.push(treeNode.right);
            }
        }
        Set<TreeNode> ancestors = new HashSet<>();
        while(p!=null){
            ancestors.add(p);
            p = toParent.get(p);
        }
        while(q!=null){
            if(ancestors.contains(q)) return q;
            q = toParent.get(q);
        }
        return null;
    }
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return null;
        if(root==p || root==q) return root;
        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);
        if(left!=null && right!=null) return root;
        return left==null?right:left;
    }
}
