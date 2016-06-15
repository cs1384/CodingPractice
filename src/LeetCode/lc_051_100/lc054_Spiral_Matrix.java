package LeetCode.lc_051_100;

import LeetCode.util.Printer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tin on 6/14/16.
 */
public class lc054_Spiral_Matrix {
    public static void main(String[] args) {
        Printer.printList(spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
    }

    /**
     * In fact, we can compress the boundries along the way
     * https://leetcode.com/discuss/53726/elegant-and-fast-java-solution-240ms
     * @param matrix
     * @return
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0) return res;

        // declare indices
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while(true){
            // 1. print top row
            for(int j=left;j<=right;j++) res.add(matrix[top][j]);
            top++;
            if(boundriesCrossed(left,right,bottom,top)) break;
            // 2. print rightmost column
            for(int i=top;i<=bottom;i++) res.add(matrix[i][right]);
            right--;
            if(boundriesCrossed(left,right,bottom,top)) break;
            // 3. print bottom row
            for(int j=right;j>=left;j--) res.add(matrix[bottom][j]);
            bottom--;
            if(boundriesCrossed(left,right,bottom,top)) break;
            // 4. print leftmost column
            for(int i=bottom;i>=top;i--)res.add(matrix[i][left]);
            left++;
            if(boundriesCrossed(left,right,bottom,top)) break;
        }

        return res;
    }
    private static boolean boundriesCrossed(int left,int right,int bottom,int top){
        return left>right || bottom<top;
    }

    public static List<Integer> spiralOrder1(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        if(m==0) return res;
        int n = matrix[0].length;
        if(n==0) return res;
        boolean[][] visited = new boolean[m][n];
        int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
        int dirIndex = 0;
        int[] op = {0,-1};
        for(int i=0;i<m*n;i++){
            if(!validMove(op, directions[dirIndex], visited, m, n)){
                // dir sequence should be right, down, left, up. Once invalid, we
                // could simply move to the next
                if(dirIndex==3) dirIndex = 0;
                else dirIndex++;
            }
            op[0] += directions[dirIndex][0];
            op[1] += directions[dirIndex][1];
            res.add(matrix[op[0]][op[1]]);
            visited[op[0]][op[1]] = true;
        }
        return res;
    }
    private static boolean validMove
            (int[] op, int[] dir, boolean[][] visited, int m, int n){
        int y = op[1]+dir[1];
        if(y<0 || y>=n) return false;
        int x = op[0]+dir[0];
        if(x<0 || x>=m) return false;
        return !visited[x][y];
    }
}
