package LeetCode.lc_101_150;

import LeetCode.util.Printer;
import LeetCode.util.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tin on 7/1/16.
 */
public class lc105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {
    public static void main(String[] args) {
        int[] preorder = {1,2,3};
        int[] inorder = {2,1,3};
        System.out.println(buildTree(preorder, inorder));
    }
    /*
    Use map and no Arrays.copyOfRange to accerlerate
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        Map<Integer, Integer> valueToIndex = new HashMap<>();
        for(int i=0;i<len;i++) valueToIndex.put(inorder[i], i);
        return buildTreeHelper(preorder, 0, len-1, valueToIndex, 0, len-1);
    }
    private static TreeNode buildTreeHelper(int[] preorder,
                                            int preorderFrom, int preorderTo,
                                            Map<Integer, Integer> valueToInorderIndex,
                                            int inorderFrom, int inorderTo){
        if(preorderTo<preorderFrom) return null;
        int value = preorder[preorderFrom];
        TreeNode root = new TreeNode(value);
        int numOnLeft = valueToInorderIndex.get(value)-inorderFrom;
        root.left = buildTreeHelper(preorder, preorderFrom+1, preorderFrom+numOnLeft,
                valueToInorderIndex, inorderFrom, valueToInorderIndex.get(value)-1);
        root.right = buildTreeHelper(preorder, preorderFrom+numOnLeft+1, preorderTo,
                valueToInorderIndex, valueToInorderIndex.get(value)+1, inorderTo);
        return root;
    }
    public static TreeNode buildTree1(int[] preorder, int[] inorder) {
        int len = preorder.length;
        if(len==0) return null;
        TreeNode root = new TreeNode(preorder[0]);
        int cut = 0;
        while(cut<len){
            if(inorder[cut]==preorder[0]) break;
            cut++;
        }
        root.left = buildTree(Arrays.copyOfRange(preorder, 1, 1+cut),
                Arrays.copyOfRange(inorder, 0, cut));
        root.right = buildTree(Arrays.copyOfRange(preorder, 1+cut, len),
                Arrays.copyOfRange(inorder, cut+1, len));
        return root;
    }
}
