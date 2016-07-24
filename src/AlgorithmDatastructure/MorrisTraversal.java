package AlgorithmDatastructure;

import LeetCode.util.Printer;
import LeetCode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tin on 7/1/16.
 *
 * traverse binary tree without recursion by constant space
 */
public class MorrisTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);

        root.left = new TreeNode(2);
        root.right = new TreeNode(6);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        Printer.printList(inorderTraversal(root));
    }
    public static List<Integer> inorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        TreeNode op = null;
        TreeNode cur = root;
        while(cur!=null){
            if(cur.left==null){
                res.add(cur.val);
                cur = cur.right;
            }else{
                op = cur.left;
                while(op.right!=null && op.right!=cur) op = op.right;
                if(op.right==null){
                    op.right = cur;
                    cur = cur.left;
                }else{
                    op.right = null;
                    res.add(cur.val);
                    cur = cur.right;
                }
            }
        }
        return res;
    }
}
