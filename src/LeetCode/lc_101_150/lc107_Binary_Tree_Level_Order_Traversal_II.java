package LeetCode.lc_101_150;

import LeetCode.util.Printer;
import LeetCode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tin on 7/1/16.
 */
public class lc107_Binary_Tree_Level_Order_Traversal_II {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        Printer.printListList(levelOrderBottom(root));
    }
    /*
    Many ways:
    1. use stack to store lists and then pop and add them to result list
    2. res.add(0, list)
    3. Collections.reverse(res)
    4. find depth and pre-create lists
     */
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        int nLevel = maxDepth(root);
        for(int i=0;i<nLevel;i++) res.add(new ArrayList<>());
        levelOrderBottomHelper(root, nLevel-1, res);
        return res;
    }
    private static void levelOrderBottomHelper(TreeNode root, int index, List<List<Integer>> res){
        if(root==null) return;
        res.get(index).add(root.val);
        levelOrderBottomHelper(root.left, index-1, res);
        levelOrderBottomHelper(root.right, index-1, res);
    }
    public static int maxDepth(TreeNode root) {
        return maxDepthHelper(root, 0);
    }
    private static int maxDepthHelper(TreeNode root, int level){
        if(root==null) return level;
        return Math.max(maxDepthHelper(root.left, level+1),
                maxDepthHelper(root.right, level+1));
    }
}
