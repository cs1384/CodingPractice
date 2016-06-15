package LeetCode.lc_051_100;

import LeetCode.util.Printer;

/**
 * Created by Tin on 6/15/16.
 */
public class lc059_Spiral_Matrix_II {
    public static void main(String[] args) {
        Printer.printMatrix(generateMatrix(3));
        Printer.printMatrix(generateMatrix(4));
    }
    public static int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int top = 0, bottom = n-1, left = 0, right = n-1;
        int op = 1;
        for(int layer=0;layer<=n/2;layer++){
            // fill top
            for(int j=left;j<=right;j++) res[top][j] = op++;
            top++;
            if(op>n*n) break;
            // fill right
            for(int i=top;i<=bottom;i++) res[i][right] = op++;
            right--;
            if(op>n*n) break;
            // fill bottom
            for(int j=right;j>=left;j--) res[bottom][j] = op++;
            bottom--;
            if(op>n*n) break;
            // fill left
            for(int i=bottom;i>=top;i--) res[i][left] = op++;
            left++;
            if(op>n*n) break;
        }
        return res;
    }
}
