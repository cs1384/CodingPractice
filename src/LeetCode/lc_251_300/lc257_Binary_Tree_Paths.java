package LeetCode.lc_251_300;

import LeetCode.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class lc257_Binary_Tree_Paths {
    public static void main(String[] args) {
        TreeNode node5 = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        node2.right = node5;
        TreeNode node1 = new TreeNode(1);
        node1.left = node2;
        node1.right = node3;
        lc257_Binary_Tree_Paths binaryTreePaths = new lc257_Binary_Tree_Paths();
        System.out.println(binaryTreePaths.binaryTreePaths(node1));

    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        LinkedList<Integer> op = new LinkedList<>();
        if (root != null) helper(root, op, result);
        return result;
    }

    private void helper(TreeNode treeNode, LinkedList<Integer> op, List<String> result) {
        op.add(treeNode.val);
        if (treeNode.right == null && treeNode.left == null) {
            result.add(buildString(op));
            op.removeLast();
            return;
        }
        if (treeNode.left != null) helper(treeNode.left, op, result);
        if (treeNode.right != null) helper(treeNode.right, op, result);
        op.removeLast();
    }

    private String buildString(List<Integer> op) {
        return String.join("->", op.stream().map(value -> String.valueOf(value)).collect(Collectors.toList()));
    }
}
