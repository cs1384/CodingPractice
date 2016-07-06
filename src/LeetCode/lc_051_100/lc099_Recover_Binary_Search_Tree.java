package LeetCode.lc_051_100;

import LeetCode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Tin on 6/30/16.
 */
public class lc099_Recover_Binary_Search_Tree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);

        root.left = new TreeNode(7);
        root.right = new TreeNode(6);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(2);
        System.out.println(root);
        recoverTree(root);
        System.out.println(root);

        TreeNode root2 = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.right.left = new TreeNode(1);
        System.out.println(root2);
        recoverTree(root2);
        System.out.println(root2);

        TreeNode root3 = new TreeNode(3);
        root3.left = new TreeNode(2);
        root3.left.right = new TreeNode(1);
        System.out.println(root3);
        recoverTree(root3);
        System.out.println(root3);

        TreeNode root4 = new TreeNode(4);
        root4.left = new TreeNode(2);
        root4.left.left = new TreeNode(1);
        root4.left.left.right = new TreeNode(3);
        System.out.println(root4);
        recoverTree(root4);
        System.out.println(root4);
    }
    public static void recoverTree(TreeNode root) {
        TreeNode op = null;
        TreeNode pre = null;
        TreeNode first = null, second = null;
        while(root!=null){
            if(root.left==null){
                if(pre!=null && pre.val>root.val){
                    if(first==null) first = pre;
                    second = root;
                }
                pre = root;
                root = root.right;
            }else{
                op = root.left;
                while(op.right!=null && op.right!=root) op = op.right;
                if(op.right==null){
                    op.right = root;
                    root = root.left;
                }else{
                    op.right = null;
                    if(pre!=null && pre.val>root.val){
                        if(first==null) first = pre;
                        second = root;
                    }
                    pre = root;
                    root = root.right;
                }
            }
        }
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    private static TreeNode pre = null;
    private static TreeNode first = null;
    private static TreeNode second = null;
    public static void recoverTree2(TreeNode root) {
        pre = null; first = null; second = null;
        inorderTraversal(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    private static void inorderTraversal(TreeNode root){
        if(root==null) return;
        inorderTraversal(root.left);
        if(pre!=null && pre.val>root.val){
            if(first==null) first = pre;
            second = root;
        }
        pre = root;
        inorderTraversal(root.right);
    }
    public static void recoverTree1(TreeNode root) {
        List<TreeNode> list = inorderTraversal1(root);
        TreeNode peak = null;
        TreeNode concave = null;
        int size = list.size();
        for(int i=0;i<size;i++){
            boolean smallerThanLeft = false;
            boolean smallerThanRight = true;
            if(i-1>=0 && list.get(i-1).val>list.get(i).val) smallerThanLeft = true;
            if(i+1<size && list.get(i+1).val<list.get(i).val) smallerThanRight = false;
            if(smallerThanLeft && smallerThanRight) concave = list.get(i);
            if(!smallerThanLeft && !smallerThanRight && peak==null) peak = list.get(i);
        }
        int temp = peak.val;
        peak.val = concave.val;
        concave.val = temp;
    }
    public static List<TreeNode> inorderTraversal1(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(root!=null){
            if(root.left!=null){
                stack.push(root);
                root = root.left;
            }else{
                res.add(root);
                while(root.right==null){
                    if(stack.isEmpty()) return res;
                    root = stack.pop();
                    res.add(root);
                }
                root = root.right;
            }
        }
        return res;
    }
}
