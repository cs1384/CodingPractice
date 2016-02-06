package LeetCode;

import java.util.*;
/**
 * Created by Tin on 2/6/16.
 */
public class From300To350 {
    public static void main(String[] args){
        System.out.println("315. Count of Smaller Numbers After Self");
        System.out.println(countSmaller(new int[]{5, 2, 6, 1})); //[2, 1, 1, 0]
    }
    static class TreeNode {
        TreeNode left, right;
        int val;
        int numOnLeft=0, numOnRight=0;
        public TreeNode(int val) {
            this.val = val;
        }
    }
    public static List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new LinkedList<Integer>();
        int len = nums.length;
        if(len==0) return res;
        TreeNode root = new TreeNode(nums[len-1]);
        res.add(0);
        for(int i=len-2;i>=0;i--){
            insertCount(0, root, nums[i], res);
        }
        Collections.reverse(res);
        return res;
    }
    private static void insertCount(int pre, TreeNode root, int value, List<Integer> res){
        if(root.val<value){
            root.numOnLeft++;
            if(root.left==null){
                root.left = new TreeNode(value);
                res.add(pre+root.numOnRight+1);
            }else{
                insertCount(pre+root.numOnRight+1, root.left, value, res);
            }
        }else{
            root.numOnRight++;
            if(root.right==null){
                root.right = new TreeNode(value);
                res.add(pre);
            }else{
                insertCount(pre, root.right, value, res);
            }
        }
    }
}
