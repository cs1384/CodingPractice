package LeetCode.lc_101_150;

import LeetCode.util.TreeNode;

import java.util.Stack;

/**
 * Created by Tin on 7/13/16.
 */
public class lc114_Flatten_Binary_Tree_to_Linked_List {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);

        // test 1
//        TreeNode t4 = new TreeNode(4);
//        TreeNode t5 = new TreeNode(5);
//        TreeNode t6 = new TreeNode(6);
//        t1.left = t2;t1.right = t5;
//        t2.left = t3;t2.right = t4;
//        t5.right = t6;

        // test 3
        t1.left = t2;


//        // test 2
//        t1.left = t2;
//        t2.left = t3;

        System.out.println(t1);
        flatten(t1);
        System.out.println(t1);
    }
    public static void flattenIterative(TreeNode root) {
        if(root==null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode treeNode = stack.pop();
            if(treeNode.right!=null) stack.push(treeNode.right);
            if(treeNode.left!=null) stack.push(treeNode.left);
            if(!stack.isEmpty())treeNode.right = stack.peek();
            treeNode.left = null;
        }
    }
    public static void flattenIterative1(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while(root!=null){
            if(root.left!=null){
                stack.push(root);
                root = root.left;
            }else if(root.right!=null){
                stack.push(root);
                root = root.right;
            }else if(!stack.isEmpty()){
                TreeNode end = root;
                while(!stack.isEmpty() && (stack.peek().left==null || (stack.peek().left!=null && stack.peek().left!=root))){
                    root = stack.pop();
                }
                if(stack.isEmpty()) root = root.right;
                else {
                    TreeNode top = stack.peek();
                    TreeNode temp = top.right;
                    top.right = root;
                    top.left = null;
                    end.right = temp;
                    root = (end.left != null || temp == null) ? end : temp;
                }
            }else root = root.right;
        }
    }

    /*
    From discussion
     */
    private static TreeNode prev = null;
    public static void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
    public static void flatten1(TreeNode root) {
        while(root!=null) {
            if (root.left != null) flatten1(root.left);
            TreeNode temp = root.right;
            root.right = root.left;
            root.left = null;
            while (root.right != null) root = root.right;
            root.right = temp;
            root = root.right;
        }
    }
}
