package LeetCode.lc_051_100;

import LeetCode.util.Printer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tin on 6/13/16.
 */
public class lc051_N_Queens {
    public static void main(String[] args) {
        Printer.printListList(solveNQueens(9));
    }
    public static List<List<String>> solveNQueens(int n) {
        int[] row_col = new int[n];
        List<List<String>> res = new ArrayList<>();
        solveNQueensHelper(n, 0, row_col, res);
        return res;
    }
    private static void solveNQueensHelper(int n, int rowN, int[] row_col, List<List<String>> res){
        if(rowN==n){
            addSolution(row_col, res);
            return;
        }
        for(int colN=0;colN<n;colN++){
            if(isValidPosition(rowN, colN, row_col)){
                row_col[rowN] = colN;
                solveNQueensHelper(n, rowN+1, row_col, res);
                row_col[rowN] = -1;
            }
        }
    }
    private static boolean isValidPosition(int rowN, int colN, int[] row_col){
        for(int i=0;i<rowN;i++){
            int takenCol = row_col[i];
            if(colN==takenCol) return false;
            if(rowN-i==Math.abs(colN-takenCol)) return false;
        }
        return true;
    }
    private static void addSolution(int[] row_col, List<List<String>> res){
        List<String> list = new ArrayList<>();
        for(int i=0;i<row_col.length;i++){
            StringBuilder sb = new StringBuilder();
            int col = row_col[i];
            for(int j=0;j<row_col.length;j++){
                if(j==col) sb.append('Q');
                else sb.append('.');
            }
            list.add(sb.toString());
        }
        res.add(list);
    }

}
