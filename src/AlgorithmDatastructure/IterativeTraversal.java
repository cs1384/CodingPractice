package AlgorithmDatastructure;

import LeetCode.util.TreeNode;
import Library.util.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Tin on 7/6/16.
 */
public class IterativeTraversal {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        n1.left = n2;n1.right = n3;
        n2.left = n4;n2.right = n5;
        n3.left = n6;n3.right = n7;
//        Printer.printList(postorderTraversal1(n1));
//        Printer.printList(postorderTraversal2(n1));
//        Printer.printList(inorderTraversal1(n1));
        Printer.printList(preorderTraversal1(n1));
    }
    public static List<Integer> preorderTraversal1(TreeNode root) {
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
    /*
    came up myself
     */
    public static List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(root!=null){
            if(root.left!=null){
                stack.push(root);
                root = root.left;
            }else{
                res.add(root.val);
                while(root.right==null){
                    if(stack.isEmpty()) return res;
                    root = stack.pop();
                    res.add(root.val);
                }
                root = root.right;
            }
        }
        return res;
    }
    /*
    With one stack
     */
    public static List<Integer> postorderTraversal2(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        Stack<TreeNode> stack = new Stack<>();
        while(root!=null || !stack.isEmpty()){
            if(root==null){
                root = stack.pop();
                if(!stack.isEmpty() && root.right!=null && root.right==stack.peek()){
                    stack.pop();
                    stack.push(root);
                    root = root.right;
                }else{
                    res.add(root.val);
                    root = null;
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
    with two stacks
     */
    public static List<Integer> postorderTraversal1(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        Stack<TreeNode> process = new Stack<>();
        Stack<TreeNode> postorder = new Stack<>();
        process.push(root);
        while(!process.isEmpty()){
            TreeNode op = process.pop();
            if(op.left!=null) process.push(op.left);
            if(op.right!=null) process.push(op.right);
            postorder.push(op);
        }
        while(!postorder.isEmpty()){
            res.add(postorder.pop().val);
        }
        return res;
    }
}
