package LeetCode.lc_051_100;

import LeetCode.util.Printer;

/**
 * Created by Tin on 6/20/16.
 */
public class lc073_Set_Matrix_Zeroes {
    public static void main(String[] args) {
        int[][] matrix1 = {{0,1}};
        setZeroes(matrix1);
        Printer.printMatrix(matrix1);
    }
    public static void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean fColZero = false, fRowZero = false;
        if(matrix[0][0]==0){
            fColZero = true;
            fRowZero = true;
        }else {
            for (int i = 1; i < m; i++) {
                if (matrix[i][0] == 0){
                    fColZero = true;
                    break;
                }
            }
            for (int j = 1; j < n; j++) {
                if (matrix[0][j] == 0){
                    fRowZero = true;
                    break;
                }
            }
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(matrix[i][j]==0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(matrix[0][j]==0 || matrix[i][0]==0){
                    matrix[i][j] = 0;
                }
            }
        }
        if(fColZero) for(int i=0;i<m;i++) matrix[i][0] = 0;
        if(fRowZero) for(int j=0;j<n;j++) matrix[0][j] = 0;
    }
}
