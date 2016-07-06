package LeetCode.lc_101_150;

import LeetCode.util.Printer;
import LeetCode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Tin on 7/1/16.
 */
public class lc103_Binary_Tree_Zigzag_Level_Order_Traversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        Printer.printList(zigzagLevelOrder(root));
    }
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        boolean leftStart = true;
        while(!stack.isEmpty()){
            Stack<TreeNode> newStack = new Stack<>();
            List<Integer> list = new ArrayList<>();
            while(!stack.isEmpty()){
                TreeNode op = stack.pop();
                list.add(op.val);
                if(op.left!=null && op.right!=null){
                    newStack.push(leftStart?op.left:op.right);
                    newStack.push(leftStart?op.right:op.left);
                }else if(op.left==null ^ op.right==null){
                    newStack.push(op.left==null?op.right:op.left);
                }
            }
            res.add(list);
            stack = newStack;
            leftStart = !leftStart;
        }
        return res;
    }
}
