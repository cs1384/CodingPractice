package LeetCode.lc_101_150;

import LeetCode.util.Printer;
import LeetCode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tin on 7/9/16.
 */
public class lc113_Path_Sum_II {
    public static void main(String[] args) {
        TreeNode n5 = new TreeNode(5);
        TreeNode n4 = new TreeNode(4);
        TreeNode n8 = new TreeNode(8);
        TreeNode n11 = new TreeNode(11);
        TreeNode n13 = new TreeNode(13);
        TreeNode n4_2 = new TreeNode(4);
        TreeNode n7 = new TreeNode(7);
        TreeNode n2 = new TreeNode(2);
        TreeNode n5_2 = new TreeNode(5);
        TreeNode n1 = new TreeNode(1);
        n5.left = n4; n5.right = n8;
        n4.left = n11;
        n8.left = n13; n8.right = n4_2;
        n11.left = n7; n11.right = n2;
        n4_2.left = n5_2; n4_2.right = n1;
        Printer.printListList(pathSum(n5, 22));
    }
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> op = new ArrayList<>();
        pathSumDFS(root, sum, 0, op, res);
        return res;
    }
    private static void pathSumDFS(TreeNode root, int sum,
                            int cur, List<Integer> op, List<List<Integer>> res){
        if(root==null) return;
        op.add(root.val);
        cur += root.val;
        if(root.left==null && root.right==null && cur==sum){
            res.add(new ArrayList<>(op));
        }
        pathSumDFS(root.left, sum, cur, op, res);
        pathSumDFS(root.right, sum, cur, op, res);
        op.remove(op.size()-1);
    }
}
