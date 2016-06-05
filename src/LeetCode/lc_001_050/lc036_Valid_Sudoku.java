package LeetCode.lc_001_050;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ytliu on 6/4/16.
 */
public class lc036_Valid_Sudoku {
    public static void main(String[] args) {
        System.out.println("example too troublesome");
    }

    /**
     * From Discussion board, very clever and should be thought of
     * @param board
     * @return
     */
    public boolean isValidSudoku2(char[][] board) {
        int [] vset = new int [9];
        int [] hset = new int [9];
        int [] bckt = new int [9];
        int idx = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    idx = 1 << (board[i][j] - '0') ;
                    if ((hset[i] & idx) > 0 ||
                            (vset[j] & idx) > 0 ||
                            (bckt[(i / 3) * 3 + j / 3] & idx) > 0) return false;
                    hset[i] |= idx;
                    vset[j] |= idx;
                    bckt[(i / 3) * 3 + j / 3] |= idx;
                }
            }
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board) {

        Map<String, BitSet[]> check = new HashMap<>();
        check.put("row", new BitSet[9]);
        for(int i=0;i<9;i++) check.get("row")[i] = new BitSet(9);
        check.put("col", new BitSet[9]);
        for(int i=0;i<9;i++) check.get("col")[i] = new BitSet(9);
        check.put("box", new BitSet[9]);
        for(int i=0;i<9;i++) check.get("box")[i] = new BitSet(9);

        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                char c = board[i][j];
                if(c!='.'){
                    int index = c-'1';

                    if(check.get("row")[i].get(index)) return false;
                    else check.get("row")[i].set(index);
                    if(check.get("col")[j].get(index)) return false;
                    else check.get("col")[j].set(index);

                    int nBox = (i/3)*3 + (j/3);
                    if(check.get("box")[nBox].get(index)) return false;
                    else check.get("box")[nBox].set(index);
                }
            }
        }

        return true;
    }
}
