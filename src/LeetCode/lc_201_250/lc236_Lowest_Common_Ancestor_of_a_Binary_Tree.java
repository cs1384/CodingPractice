package LeetCode.lc_201_250;

import LeetCode.util.TreeNode;

import java.util.Stack;

/**
 * Created by Tin on 7/5/16.
 */
public class lc236_Lowest_Common_Ancestor_of_a_Binary_Tree {
    public static void main(String[] args) {

    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode candidate = null;
        TreeNode op = null;
        while(op!=null){
            if(op.left!=null){
                stack.push(op);
                op = op.left;
            }else{
                if(op==p || op==q) {
                    if(candidate==null) candidate = stack.peek();
                    else {
                        while(!stack.isEmpty())
                            if(stack.pop()==candidate) return candidate;
                    }
                }
                while(stack.isEmpty() && stack.peek().right==null){
                    stack.pop();
                }
                if(!stack.isEmpty()) op = stack.peek().right;
                else break;
            }
        }
        return root;
    }
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return null;
        if(root==p || root==q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left!=null && right!=null) return root;
        return left==null?right:left;
    }
}
