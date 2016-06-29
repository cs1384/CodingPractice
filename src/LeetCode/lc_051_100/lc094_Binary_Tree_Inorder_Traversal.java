package LeetCode.lc_051_100;

import LeetCode.util.Printer;
import LeetCode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Tin on 6/28/16.
 */
public class lc094_Binary_Tree_Inorder_Traversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        Printer.printList(inorderTraversal(root));
    }
    public static List<Integer> inorderTraversal(TreeNode root) {
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
    not work
     */
    public static List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(root!=null){
            if(root.left!=null){
                stack.push(root);
                root = root.left;
            }else if(root.right!=null) {
                res.add(root.val);
                root = root.right;
            }else{
                res.add(root.val);
                if(!stack.isEmpty()) root = stack.pop();
                else return res;
            }
        }
        return res;
    }
}
