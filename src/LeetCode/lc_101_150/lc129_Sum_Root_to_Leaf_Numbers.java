package LeetCode.lc_101_150;

import LeetCode.util.TreeNode;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

import java.util.Stack;

/**
 * Created by Tin on 7/22/16.
 */
public class lc129_Sum_Root_to_Leaf_Numbers {
    public static void main(String[] args) {
        TreeNode tn1 = new TreeNode(1);
        TreeNode tn2 = new TreeNode(2);
        TreeNode tn3 = new TreeNode(3);
        tn1.left = tn2;tn1.right = tn3;
//        System.out.println(sumNumbers(tn1));

        TreeNode tn4 = new TreeNode(4);
        TreeNode tn5 = new TreeNode(5);
        tn2.left = tn4;tn2.right = tn5;
        System.out.println(sumNumbers(tn1));
    }
    /*
    From discussion
    https://discuss.leetcode.com/topic/10705/non-recursive-preorder-traverse-java-solution/2
     */
    public static int sumNumbers(TreeNode root) {
        if(root==null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int sum = 0;
        while(!stack.isEmpty()){
            TreeNode treeNode = stack.pop();
            if(treeNode.left==null && treeNode.right==null){
                sum += treeNode.val;
                continue;
            }
            if(treeNode.left!=null){
                treeNode.left.val += treeNode.val*10;
                stack.push(treeNode.left);
            }
            if(treeNode.right!=null){
                treeNode.right.val += treeNode.val*10;
                stack.push(treeNode.right);
            }
        }
        return sum;
    }
    public static int sumNumbers1(TreeNode root) {
        return root==null?0:sumNumbersHelper(root, 0);
    }
    private static int sumNumbersHelper(TreeNode root, int carry){
        if(root.left==null && root.right==null) return carry*10+root.val;
        carry = carry*10+root.val;
        int sum = 0;
        if(root.left!=null) sum += sumNumbersHelper(root.left, carry);
        if(root.right!=null) sum += sumNumbersHelper(root.right, carry);
        return sum;
    }
}
