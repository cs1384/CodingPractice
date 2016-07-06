package LeetCode.lc_101_150;

import LeetCode.util.Printer;
import LeetCode.util.TreeNode;

import java.util.*;

/**
 * Created by Tin on 7/1/16.
 */
public class lc102_Binary_Tree_Level_Order_Traversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        Printer.printListList(levelOrder(root));
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrderHelper(root, 1, res);
        return res;
    }
    private static void levelOrderHelper(TreeNode root, int level, List<List<Integer>> res){
        if(root==null) return;
        if(level>res.size()) res.add(new ArrayList<>());
        res.get(level-1).add(root.val);
        levelOrderHelper(root.left, level + 1, res);
        levelOrderHelper(root.right, level + 1, res);
    }
    public List<List<Integer>> levelOrder1(TreeNode root) {
        if(root==null) return Collections.EMPTY_LIST;
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        List<Integer> list = new ArrayList<>();
        while(!queue.isEmpty()){
            TreeNode treeNode = queue.poll();
            if(treeNode!=null){
                list.add(treeNode.val);
                if(treeNode.left!=null) queue.offer(treeNode.left);
                if(treeNode.right!=null) queue.offer(treeNode.right);
            }else{
                res.add(list);
                list = new ArrayList<>();
                if(!queue.isEmpty()) queue.offer(null);
            }
        }
        return res;
    }
}
