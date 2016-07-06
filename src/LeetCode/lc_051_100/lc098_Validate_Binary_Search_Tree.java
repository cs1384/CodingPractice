package LeetCode.lc_051_100;

import LeetCode.util.TreeNode;

/**
 * Created by Tin on 6/29/16.
 */
public class lc098_Validate_Binary_Search_Tree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(-2147483648);
        root.left = new TreeNode(-2147483648);
        System.out.println(isValidBST(root)); //false

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(1);
        System.out.println(isValidBST(root2)); //false
    }
    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(null, root, null);
    }
    private static boolean isValidBSTHelper(Integer lBound, TreeNode root, Integer uBound){
        if(root==null) return true;
        if(lBound!=null && root.val<=lBound) return false;
        if(uBound!=null && root.val>=uBound) return false;
        return isValidBSTHelper(lBound, root.left, root.val) &&
                isValidBSTHelper(root.val, root.right, uBound);
    }
}
