package LeetCode.lc_101_150;

import LeetCode.util.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Tin on 7/1/16.
 */
public class lc106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {
    public static void main(String[] args) {
        System.out.println(buildTree(new int[]{4,2,5,1,6,3,7},
                new int[]{4,5,2,6,7,3,1}));
        System.out.println(buildTree(new int[]{2,1},
                new int[]{2,1}));
    }
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = inorder.length;
        if(len==0) return null;
        int iIndex = len-1, pIndex = len-1;
        TreeNode root = new TreeNode(postorder[pIndex--]);
        Stack<TreeNode> rightSlope = new Stack<>();
        rightSlope.push(root);
        while(pIndex>=0){
            TreeNode treeNode = new TreeNode(postorder[pIndex--]);
            TreeNode rightMost = null;
            while(!rightSlope.isEmpty() && rightSlope.peek().val==inorder[iIndex]){
                rightMost = rightSlope.pop();
                iIndex--;
            }
            if(rightMost!=null) rightMost.left = treeNode;
            else if(!rightSlope.isEmpty()) rightSlope.peek().right = treeNode;
            rightSlope.push(treeNode);
        }
        return root;
    }
    public static TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) return null;
        int ip = inorder.length - 1;
        int pp = postorder.length - 1;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode prev = null;
        TreeNode root = new TreeNode(postorder[pp]);
        stack.push(root);
        pp--;

        while (pp >= 0) {
            while (!stack.isEmpty() && stack.peek().val == inorder[ip]) {
                prev = stack.pop();
                ip--;
            }
            TreeNode newNode = new TreeNode(postorder[pp]);
            if (prev != null) {
                prev.left = newNode;
            } else if (!stack.isEmpty()) {
                TreeNode currTop = stack.peek();
                currTop.right = newNode;
            }
            stack.push(newNode);
            prev = null;
            pp--;
        }

        return root;
    }
    /*
    based on the same idea in lc105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal
     */
    public static TreeNode buildTree1(int[] inorder, int[] postorder) {
        int len = inorder.length;
        Map<Integer, Integer> valueToInorderIndex = new HashMap<>();
        for(int i=0;i<len;i++) valueToInorderIndex.put(inorder[i], i);
        return buildTreeHelper(valueToInorderIndex, 0, len-1, postorder, 0, len-1);
    }
    private static TreeNode buildTreeHelper(Map<Integer, Integer> valueToInorderIndex,
                                            int inorderFrom, int inorderTo,
                                            int[] postorder, int postorderFrom, int postorderTo){
        if(postorderFrom>postorderTo) return null;
        int value = postorder[postorderTo];
        TreeNode root = new TreeNode(value);
        int inorderIndex = valueToInorderIndex.get(value);
        int nOnRight = inorderTo-inorderIndex;
        root.right = buildTreeHelper(valueToInorderIndex, inorderIndex+1, inorderTo,
                postorder, postorderTo-nOnRight, postorderTo-1);
        root.left = buildTreeHelper(valueToInorderIndex, inorderFrom, inorderIndex-1,
                postorder, postorderFrom, postorderTo-nOnRight-1);
        return root;
    }

}
