package LeetCode.lc_101_150;

import LeetCode.util.TreeNode;

/**
 * Created by Tin on 7/1/16.
 */
public class lc104_Maximum_Depth_of_Binary_Tree {
    public static void main(String[] args) {

    }
    /*
    or BFS to reach the lowest
     */
    public static int maxDepth(TreeNode root) {
        return maxDepthHelper(root, 0);
    }
    private static int maxDepthHelper(TreeNode root, int level){
        if(root==null) return level;
        return Math.max(maxDepthHelper(root.left, level+1),
                maxDepthHelper(root.right, level+1));
    }
}
