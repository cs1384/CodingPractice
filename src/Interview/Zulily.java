package Interview;

import LeetCode.util.Printer;
import Library.object.TreeNode;

import java.util.*;

/**
 * Created by Tin on 6/16/16.
 */
public class Zulily {
    /*
    http://www.1point3acres.com/bbs/thread-147916-1-1.html
     */
    public static void main(String[] args) {
        TreeNode<Integer> tn1 = new TreeNode<>(1);
        TreeNode<Integer> tn2 = new TreeNode<>(2);
        TreeNode<Integer> tn3 = new TreeNode<>(3);
        TreeNode<Integer> tn4 = new TreeNode<>(4);
        TreeNode<Integer> tn5 = new TreeNode<>(5);
        TreeNode<Integer> tn6 = new TreeNode<>(6);
        TreeNode<Integer> tn7 = new TreeNode<>(7);
        TreeNode<Integer> tn8 = new TreeNode<>(8);
        TreeNode<Integer> tn9 = new TreeNode<>(9);
        tn1.left = tn2;
        tn1.right = tn3;
        tn2.left = tn4;
        tn2.right = tn5;
        tn3.left = tn6;
        tn3.right = tn7;
        tn6.right = tn8;
        tn7.right = tn9;
        System.out.println("Print_a_Binary_Tree_in_Vertical_Order() ---");
        Print_a_Binary_Tree_in_Vertical_Order(tn1);

        System.out.println("pathWithLeastSum() ---");
        Printer.printMatrix(pathWithLeastSum(new int[][]{{1,0,2,0}, {3,1,2,1},
                {8,5,3,15}, {1,6,10,0}}));

        System.out.println("calculate() ---");
        System.out.println(calculate("5*3-(9+7)/3"));
    }

    public static void Print_a_Binary_Tree_in_Vertical_Order(TreeNode<Integer> root){
        Map<Integer, List<TreeNode<Integer>>> verticalLevel_TreeNodes = new TreeMap<>();
        assignVerticalLevel(verticalLevel_TreeNodes, root, 0);
        for(int i : verticalLevel_TreeNodes.keySet()){
            for(TreeNode<Integer> treeNode : verticalLevel_TreeNodes.get(i)){
                System.out.print(treeNode + ", ");
            }
            System.out.println();
        }
    }
    private static void assignVerticalLevel(Map<Integer, List<TreeNode<Integer>>> verticalLevel_TreeNodesv,
                                     TreeNode<Integer> root, int level){
        if(root==null) return;
        if(!verticalLevel_TreeNodesv.containsKey(level))
            verticalLevel_TreeNodesv.put(level, new ArrayList<TreeNode<Integer>>());
        verticalLevel_TreeNodesv.get(level).add(root);
        assignVerticalLevel(verticalLevel_TreeNodesv, root.left, level-1);
        assignVerticalLevel(verticalLevel_TreeNodesv, root.right, level+1);
    }

    public static int[][] pathWithLeastSum(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        // DP to get least sum
        int[][] sums = new int[m][n];
        int[][] dirs = new int[m][n];
        for(int j=0;j<n;j++) sums[0][j] = grid[0][j];
        for(int i=1;i<m;i++){
            for(int j=0;j<n;j++){
                int least = sums[i-1][j];
                if(j>0 && sums[i-1][j-1]<least){
                    least = sums[i-1][j-1];
                    dirs[i][j] = -1;
                }
                if(j<n-1 && sums[i-1][j+1]<least){
                    least = sums[i-1][j+1];
                    dirs[i][j] = 1;
                }
                sums[i][j] = least + grid[i][j];
            }
        }
        Printer.printMatrix(sums);
        Printer.printMatrix(dirs);
        // find least sum in last row
        int index = 0;
        int min = sums[m-1][index];
        int j = 1;
        while(j<n){
            if(sums[m-1][j]<min){
                min = sums[m-1][j];
                index = j;
            }
            j++;
        }
        System.out.println("index:"+index);
        // trace back from index of least sum
        int[][] res = new int[m][2];
        for(int i=m-1;i>=0;i--){
            res[i][0] = i;
            res[i][1] = index;
            index += dirs[i][index];
        }
        return res;
    }

    public static int calculate(String s) {
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        s = "("+s+")";
        int len = s.length();
        for(int i=0;i<len;i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                int num = c-'0';
                while (i+1<len && Character.isDigit(s.charAt(i+1))) {
                    num *= 10;
                    num += s.charAt(++i) - '0';
                }
                if(!ops.isEmpty() && (ops.peek()=='/' || ops.peek()=='*')) priorOperation(nums, ops, num);
                else nums.push(num);
            } else{
                if(c==')') calculateParentheses(nums, ops);
                else ops.push(c);
            }
        }
        return nums.pop();
    }
    private static void calculateParentheses(Stack<Integer> nums, Stack<Character> ops){
        int res = 0;
        char op = ')';
        while(op!='('){
            int num = nums.pop();
            op = ops.pop();
            if(op=='-'){
                res -= num;
            }else{
                res += num;
            }
        }
        if(!ops.isEmpty() && (ops.peek()=='/' || ops.peek()=='*')) priorOperation(nums, ops, res);
        else nums.push(res);
    }
    private static void priorOperation(Stack<Integer> nums, Stack<Character> ops, int b){
        char op = ops.pop();
        int a = nums.pop();
        int ans = op=='/'?a/b:a*b;
        nums.push(ans);
    }

}
