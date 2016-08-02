package LeetCode.lc_151_200;

import LeetCode.util.TreeNode;

import java.util.Stack;

/**
 * Created by Tin on 8/2/16.
 */
public class lc173_Binary_Search_Tree_Iterator {
    public static void main(String[] args) {

    }
    public class BSTIterator {
        Stack<TreeNode> stack = new Stack<>();
        public BSTIterator(TreeNode root) {
            proces(root);
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode treeNode = stack.pop();
            proces(treeNode.right);
            return treeNode.val;
        }

        private void proces(TreeNode treeNode){
            while(treeNode!=null){
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
        }
    }
}
