package LeetCode.lc_101_150;

import LeetCode.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Tin on 6/30/16.
 */
public class lc101_Symmetric_Tree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
//        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(3);
//        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        System.out.println(isSymmetric1(root));
    }
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while(queue.size()>=2){
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if(left==null && right==null) continue;
            if(left==null || right==null) return false;
            if(left.val!=right.val) return false;
            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }
    public boolean isSymmetric2(TreeNode root) {
        return root==null?true:isSymmetric2Helper(root.left, root.right);
    }
    private boolean isSymmetric2Helper(TreeNode left, TreeNode right){
        if(left==null && right==null) return true;
        if(left==null || right==null) return false;
        if(left.val!=right.val) return false;
        return isSymmetric2Helper(left.left, right.right) &&
                isSymmetric2Helper(left.right, right.left);
    }

    public static boolean isSymmetric1(TreeNode root) {
        if(root==null) return true;
        List<Integer> t2 = new ArrayList<>();
        List<Integer> t1 = new ArrayList<>();
        inorderTraverse(t1, root.left, true);
        inorderTraverse(t2, root.right, false);
        if(t1.size()!=t2.size()) return false;
        for(int i=0;i<t1.size();i++) if(t1.get(i)!=t2.get(i)) return false;
        return true;
    }
    public static void inorderTraverse(List<Integer> list, TreeNode treeNode,
                                 boolean leftFirst){
        if(treeNode==null){
            list.add(null);
            return;
        }
        list.add(treeNode.val);
        inorderTraverse(list, leftFirst ? treeNode.left : treeNode.right, leftFirst);
        inorderTraverse(list, leftFirst ? treeNode.right : treeNode.left, leftFirst);
    }
}
