package LeetCode.lc_001_050;

import LeetCode.util.Printer;

/**
 * Created by Tin on 6/12/16.
 */
public class lc048_Rotate_Image {
    public static void main(String[] args) {
        int[][] matrix1 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        rotate(matrix1);
        Printer.printMatrix(matrix1);
        int[][] matrix2 = {{1,2,3},{4,5,6},{7,8,9}};
        rotate(matrix2);
        Printer.printMatrix(matrix2);
    }
    public static void rotate(int[][] matrix) {
        int len = matrix.length;
        int op = 0;
        while(op<len/2){
            int toGo = len-1-2*op;
            // define the transition matrix for corners
            int[][] transition =
                    {{op,op+toGo}, {op+toGo,op+toGo}, {op+toGo,op}, {op,op}};
            // arrange the followers of corners
            for (int j=0; j<toGo; j++) {
                int temp = matrix[transition[3][0]][transition[3][1]+j];
                matrix[transition[3][0]][transition[3][1]+j] = matrix[transition[2][0]-j][transition[2][1]];
                matrix[transition[2][0]-j][transition[2][1]] = matrix[transition[1][0]][transition[1][1]-j];
                matrix[transition[1][0]][transition[1][1]-j] = matrix[transition[0][0]+j][transition[0][1]];
                matrix[transition[0][0]+j][transition[0][1]] = temp;
            }
            op++;
        }
    }
}
