package AlgorithmDatastructure;

import LeetCode.util.Printer;
import LeetCode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tin on 7/1/16.
 *
 * traverse binary tree without recursion by constant space
 * http://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html
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
        System.out.println(root);
        Printer.printList(inorderTraversal(root));
        Printer.printList(preorderTraversal(root));
        Printer.printList(postorderTraversal(root));
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
    public static List<Integer> preorderTraversal(TreeNode root){
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
                    res.add(cur.val);
                    op.right = cur;
                    cur = cur.left;
                }else{
                    op.right = null;
                    cur = cur.right;
                }
            }
        }
        return res;
    }
    public static List<Integer> postorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        TreeNode op = null;
        TreeNode dummy = new TreeNode(-1);
        dummy.left = root;
        TreeNode cur = dummy;
        while(cur!=null){
            if(cur.left==null){
                cur = cur.right;
            }else{
                op = cur.left;
                while(op.right!=null && op.right!=cur) op = op.right;
                if(op.right==null){
                    op.right = cur;
                    cur = cur.left;
                }else{
                    addAndReverse(res, cur.left, op);
                    op.right = null;
                    cur = cur.right;
                }
            }
        }
        return res;
    }
    private static void addAndReverse(List<Integer> res, TreeNode from, TreeNode to){
        int preLen = res.size();
        while(from!=to){
            res.add(from.val);
            from = from.right;
        }
        res.add(to.val);
        int i = preLen, j = res.size()-1;
        while(i<j){
            int temp = res.get(i);
            res.set(i, res.get(j));
            res.set(j, temp);
            i++;j--;
        }
    }
}
