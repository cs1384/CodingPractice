package LeetCode.lc_051_100;

/**
 * Created by Tin on 6/13/16.
 */
public class lc052_N_Queens_II {
    public static void main(String[] args) {
        System.out.println(totalNQueens(9));
    }
    static int count;
    public static int totalNQueens(int n) {
        count = 0;
        boolean[] occupiedCol = new boolean[n];
        boolean[] diagonal = new boolean[2*n-1];
        boolean[] antidiagonal = new boolean[2*n-1];
        totalNQueensHelper(n, 0, occupiedCol, diagonal, antidiagonal);
        return count;
    }
    private static void totalNQueensHelper
            (int n, int rowN, boolean[] col, boolean[] diagonal, boolean[] antidiagonal){
        if(n==rowN){
            count++;
            return;
        }
        for(int i=0;i<n;i++){
            if(col[i]) continue;
            int diagonalIndex = n-(rowN-i)-1; // from bottom left to upper right
            if(diagonal[diagonalIndex]) continue;
            int antidiagonalIndex = rowN+i; // from upper left to bottom right
            if(antidiagonal[antidiagonalIndex]) continue;
            col[i] = true;
            diagonal[diagonalIndex] = true;
            antidiagonal[antidiagonalIndex] = true;
            totalNQueensHelper(n, rowN+1, col, diagonal, antidiagonal);
            col[i] = false;
            diagonal[diagonalIndex] = false;
            antidiagonal[antidiagonalIndex] = false;
        }
    }
}
