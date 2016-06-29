package LeetCode.lc_051_100;

import LeetCode.util.Printer;
import LeetCode.util.TreeNode;

import java.util.*;

/**
 * Created by Tin on 6/28/16.
 */
public class lc095_Unique_Binary_Search_Trees_II {
    public static void main(String[] args) {

//        TreeNode root = new TreeNode(2);
//        root.left = new TreeNode(3);
//        root.right = new TreeNode(1);
//        changeTreeValue(root, 1);
//        System.out.println(root);

        Printer.printList(generateTrees(3));
    }
    public static List<TreeNode> generateTrees(int n) {
        if(n==0) return Collections.EMPTY_LIST;
        List<TreeNode>[] dp = new List[n+1];
        dp[0] = new ArrayList<>();
        dp[0].add(null);
        for(int i=1;i<=n;i++){
            dp[i] = new ArrayList<>();
            for(int left=0;left<i;left++){
                int right = i-1-left;
                for(TreeNode leftNode : dp[left]){
                    for(TreeNode rightNode : dp[right]){
                        TreeNode root = new TreeNode(left+1);
                        root.left = leftNode;
                        //supposed to clone, but the value remains the same and never be changed
//                      root.left = cloneTree(leftNode, 0);
                        root.right = cloneTree(rightNode, root.val);
                        dp[i].add(root);
                    }
                }
            }
        }
        return dp[n];
    }
    private static TreeNode cloneTree(TreeNode root, int offset){
        if(root==null) return null;
        TreeNode cloneRoot = new TreeNode(root.val+offset);
        cloneRoot.left = cloneTree(root.left, offset);
        cloneRoot.right = cloneTree(root.right, offset);
        return cloneRoot;
    }
    /*
    Does not work because the rightNode is shared by other dp=i, corrected value
    would be changed
     */
    public static List<TreeNode> generateTrees2(int n) {
        List<TreeNode>[] dp = new List[n+1];
        dp[0] = new ArrayList<>();
        dp[0].add(null);
        for(int i=1;i<=n;i++){
            dp[i] = new ArrayList<>();
            for(int left=0;left<i;left++){
                int right = i-1-left;
                for(TreeNode leftNode : dp[left]){
                    for(TreeNode rightNode : dp[right]){
                        TreeNode root = new TreeNode(left+1);
                        root.left = leftNode;
                        changeTreeValue(rightNode, left + 2);
                        root.right = rightNode;
                        dp[i].add(root);
                    }
                }
            }
        }
        return dp[n];
    }
    private static void changeTreeValue(TreeNode root, int start){
        Stack<TreeNode> stack = new Stack<>();
        while(root!=null){
            if(root.left!=null){
                stack.push(root);
                root = root.left;
            }else{
                root.val = start++;
                while(root.right==null){
                    if(stack.isEmpty()) return;
                    root = stack.pop();
                    root.val = start++;
                }
                root = root.right;
            }
        }
    }
    /*
    generate tree everytime, time consuming
     */
    public static List<TreeNode> generateTrees1(int n) {
        if(n==0) return Collections.EMPTY_LIST;
        return generateTreesHelper(1,n);
    }
    private static List<TreeNode> generateTreesHelper(int start, int end){
        List<TreeNode> res = new ArrayList<>();
        if(start>end){
            res.add(null);
            return res;
        }
        for(int i=start;i<=end;i++){
            List<TreeNode> leftNodes = generateTreesHelper(start, i-1);
            List<TreeNode> rightNodes = generateTreesHelper(i+1, end);
            for(TreeNode left : leftNodes){
                for(TreeNode right : rightNodes){
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
