package LeetCode.lc_101_150;

import LeetCode.util.Printer;
import LeetCode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Tin on 7/25/16.
 */
public class lc145_Binary_Tree_Postorder_Traversal {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        t1.left = t2; t1.right = t3;
        t3.left = t4;
        Printer.printList(postorderTraversal(t1));
    }
    /*
    See MorrisTraversal.java
    traverse the tree without extra space
     */
    public static List<Integer> postorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        TreeNode op = null;
        TreeNode dummy = new TreeNode(-1);
        dummy.left = root;
        TreeNode cur = dummy;
        while(cur!=null){
            if(cur.left==null){
                cur = cur.right;
            }else{
                op = cur.left;
                while(op.right!=null && op.right!=cur) op = op.right;
                if(op.right==null){
                    op.right = cur;
                    cur = cur.left;
                }else{
                    addAndReverse(res, cur.left, op);
                    op.right = null;
                    cur = cur.right;
                }
            }
        }
        return res;
    }
    private static void addAndReverse(List<Integer> res, TreeNode from, TreeNode to){
        int preLen = res.size();
        while(from!=to){
            res.add(from.val);
            from = from.right;
        }
        res.add(to.val);
        int i = preLen, j = res.size()-1;
        while(i<j){
            int temp = res.get(i);
            res.set(i, res.get(j));
            res.set(j, temp);
            i++;j--;
        }
    }
    /*
    one stack
     */
    public static List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(root!=null || !stack.isEmpty()){
            if(root==null){
                root = stack.pop();
                while(!stack.isEmpty() && (root.right==null || root.right!=stack.peek())){
                    res.add(root.val);
                    root = stack.pop();
                }
                if(stack.isEmpty()){
                    res.add(root.val);
                    return res;
                }else{
                    TreeNode temp = stack.pop();
                    stack.push(root);
                    root = temp;
                }
            }else{
                if(root.right!=null) stack.push(root.right);
                stack.push(root);
                root = root.left;
            }
        }
        return res;
    }
    /*
    two stacks solution
     */
    public static List<Integer> postorderTraversal2(TreeNode root) {
        Stack<TreeNode> preorderProcess = new Stack<>();
        Stack<TreeNode> postorder = new Stack<>();
        preorderProcess.push(root);
        while(!preorderProcess.isEmpty()){
            TreeNode treeNode = preorderProcess.pop();
            postorder.push(treeNode);
            if(treeNode.left!=null) preorderProcess.push(treeNode.left);
            if(treeNode.right!=null) preorderProcess.push(treeNode.right);
        }
        List<Integer> res = new ArrayList<>();
        while(!postorder.isEmpty()) res.add(postorder.pop().val);
        return res;
    }
    /*
    recursive solution
     */
    public static List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorderTraversalHelper(res, root);
        return res;
    }
    private static void postorderTraversalHelper(List<Integer> res, TreeNode root){
        if(root==null) return;
        postorderTraversalHelper(res, root.left);
        postorderTraversalHelper(res, root.right);
        res.add(root.val);
    }
}
