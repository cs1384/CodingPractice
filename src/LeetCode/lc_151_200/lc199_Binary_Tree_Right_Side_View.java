package LeetCode.lc_151_200;

import LeetCode.util.Printer;
import LeetCode.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Tin on 8/4/16.
 */
public class lc199_Binary_Tree_Right_Side_View {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t1.left = t2; t1.right = t3;
        t2.right = t5;
        t3.right = t4;
        Printer.printList(rightSideView(t1));
    }
    /*
    DFS
     */
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        rightSideViewDFS(root, 1, res);
        return res;
    }
    private static void rightSideViewDFS(TreeNode root, int level, List<Integer> res){
        if(root==null) return;
        if(level>res.size()) res.add(root.val);
        rightSideViewDFS(root.right, level+1, res);
        rightSideViewDFS(root.left, level+1, res);
    }
    /*
    Faster BFS
     */
    public static List<Integer> rightSideView2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(null);queue.add(root);
        while(!queue.isEmpty()){
            TreeNode treeNode = queue.poll();
            if(treeNode==null){
                if(!queue.isEmpty()){
                    res.add(queue.peek().val);
                    queue.add(null);
                }
            }else{
                if(treeNode.right!=null) queue.offer(treeNode.right);
                if(treeNode.left!=null) queue.offer(treeNode.left);
            }
        }
        return res;
    }
    /*
    regular BFS
     */
    public static List<Integer> rightSideView1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);queue.add(null);
        TreeNode pre = null;
        while(!queue.isEmpty()){
            TreeNode treeNode = queue.poll();
            if(treeNode==null){
                res.add(pre.val);
                if(!queue.isEmpty()) queue.add(null);
            }else{
                if(treeNode.left!=null) queue.offer(treeNode.left);
                if(treeNode.right!=null) queue.offer(treeNode.right);
                pre = treeNode;
            }
        }
        return res;
    }
}
