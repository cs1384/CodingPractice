package LeetCode.lc_101_150;

import LeetCode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Tin on 7/6/16.
 */
public class lc144_Binary_Tree_Preorder_Traversal {
    public static void main(String[] args) {

    }
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode treeNode = stack.pop();
            res.add(treeNode.val);
            if(treeNode.right!=null) stack.push(treeNode.right);
            if(treeNode.left!=null) stack.push(treeNode.left);
        }
        return res;
    }
    public static List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        preorder(root, result);
        return result;
    }
    public static void preorder(TreeNode rt, List<Integer> result){
        if(rt==null) return;
        result.add(rt.val);
        preorder(rt.left, result);
        preorder(rt.right, result);
    }
}
