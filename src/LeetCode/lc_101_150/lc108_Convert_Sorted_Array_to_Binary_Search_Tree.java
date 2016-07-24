package LeetCode.lc_101_150;

import LeetCode.util.TreeNode;

/**
 * Created by Tin on 7/9/16.
 */
public class lc108_Convert_Sorted_Array_to_Binary_Search_Tree {
    public static void main(String[] args) {

    }
    public TreeNode sortedArrayToBST(int[] num) {
        return buildTree(num,0,num.length-1);
    }
    public TreeNode buildTree(int[] num, int start, int end){
        if(start>end) return null;
        int mid = (start+end)/2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = buildTree(num, start, mid-1);
        root.right = buildTree(num, mid+1, end);
        return root;
    }
}
