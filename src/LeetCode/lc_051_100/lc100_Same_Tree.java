package LeetCode.lc_051_100;

import LeetCode.util.TreeNode;

/**
 * Created by Tin on 6/29/16.
 */
public class lc100_Same_Tree {
    public static void main(String[] args) {

    }
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null) return true;
        if(p==null || q==null) return false;
        if(p.val!=q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
