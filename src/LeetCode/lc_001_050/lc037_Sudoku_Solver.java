package LeetCode.lc_001_050;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 6/4/16.
 */
public class lc037_Sudoku_Solver {
    public static void main(String[] args) {
        char[][] board = new char[9][];
        board[0] = "..9748...".toCharArray();
        board[1] = "7........".toCharArray();
        board[2] = ".2.1.9...".toCharArray();
        board[3] = "..7...24.".toCharArray();
        board[4] = ".64.1.59.".toCharArray();
        board[5] = ".98...3..".toCharArray();
        board[6] = "...8.3.2.".toCharArray();
        board[7] = "........6".toCharArray();
        board[8] = "...2759..".toCharArray();
        solveSudoku(board);
        Printer.printMatrix(board);

        char[][] board2 = new char[9][];
        board2[0] = "53..7....".toCharArray();
        board2[1] = "6..195...".toCharArray();
        board2[2] = ".98....6.".toCharArray();
        board2[3] = "8...6...3".toCharArray();
        board2[4] = "4..8.3..1".toCharArray();
        board2[5] = "7...2...6".toCharArray();
        board2[6] = ".6....28.".toCharArray();
        board2[7] = "...419..5".toCharArray();
        board2[8] = "....8..79".toCharArray();
        solveSudoku(board2);
        Printer.printMatrix(board2);
    }
    static int[] row = null;
    static int[] col = null;
    static int[] box = null;
    static List<Integer> toFill = null;

    /**
     * Use integer bits to track the used numbers of a row, col, and box
     * and then calulate the available numbers for each cell
     * @param board
     */
    public static void solveSudoku(char[][] board) {
        row = new int[9];
        col = new int[9];
        box = new int[9];
        toFill = new LinkedList<>();
        preProcess(board);
        solveSudokuDFS(0, board);
    }
    private static void preProcess(char[][] board){
        int index;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    index = 1 << (board[i][j] - '1') ;
                    row[i] |= index;
                    col[j] |= index;
                    box[(i/3)*3 + j/3] |= index;
                }else{
                    toFill.add(i*9+j);
                }
            }
        }
    }
    private static boolean solveSudokuDFS(int working, char[][] board){
        if(working==toFill.size()) return true; //we are done
        int temp = toFill.get(working);
        int rowN = temp/9;
        int colN = temp%9;
        int boxN = (rowN/3)*3 + colN/3;
        int available = row[rowN] | col[colN] | box[boxN];
        int op = 1;
        for(int i=1;i<=9;i++){
            if((available&op)==0){
                board[rowN][colN] = (char)('0'+i);
                row[rowN] |= op;
                col[colN] |= op;
                box[boxN] |= op;
                if(solveSudokuDFS(working+1, board)) return true;
                board[rowN][colN] = '.';
                row[rowN] ^= op;
                col[colN] ^= op;
                box[boxN] ^= op;
            }
            op <<= 1;
        }
        return false;
    }
    /**
     * I would call this kind of quesiton "try and error" question. You just fill the empty cell with 1 to 9 then try if
     * it works out (means you try next empty and so on util no empty cell left). this would be very like a DFS in which
     * you try every possibility and find a route.
     * In this question, you put all empty cells into an array and start DFS on the first element. In DFS, firstly you
     * check if the value (1-9) is valid and then fill the cell and call next recursion method. if it didn't work out,
     * you set the cell back.
     *
     */
    public void solveSudoku1(char[][] board) {
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]=='.')
                    list.add(i*9+j); //like encode the coordinates
            }
        }
        solveSudokuDFS1(list, 0, list.size(), board);
    }
    public boolean solveSudokuDFS1(List<Integer> list, int cur, int len, char[][] board){
        if(cur==len) return true; //termination
        int temp = list.get(cur);
        int row = temp/9; //decode
        int col = temp%9; //decode
        for(int i=1;i<=9;i++){
            if(isValid(board, row, col, i)){
                board[row][col] = (char)('0'+i); //need to specify the casting here
                if(solveSudokuDFS1(list, cur+1, len, board)) return true;
                board[row][col] = '.'; //set back
            }
        }
        return false;
    }
    public boolean isValid(char[][] board, int row, int col, int value){
        for(int i=0;i<9;i++){
            //since the target cell is not filled for now (empty), you are free not to check the target cell as well
            if(board[row][i]-'0'==value) return false;
            if(board[i][col]-'0'==value) return false;
            //very smart code! so in this only iteration, you can check everything
            int row_s = 3*(row/3) + i/3;
            int col_s = 3*(col/3) + i%3;
            if(board[row_s][col_s] - '0'==value) return false;
        }
        return true;
    }
}
