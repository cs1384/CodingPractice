package LeetCode.lc_051_100;

/**
 * Created by Tin on 6/22/16.
 */
public class lc079_Word_Search {
    public static void main(String[] args) {
        char[][] board = {  {'A','B','C','E'},
                            {'S','F','C','S'},
                            {'A','D','E','E'}   };
        System.out.println(exist(board, "ABCCED"));
        System.out.println(exist(board, "SEE"));
        System.out.println(exist(board, "ABCB"));
    }
    public static boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] inUse = new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(existHelper(board, i, j, inUse, word, 0)) return true;
            }
        }
        return false;
    }

    private static boolean existHelper(char[][] board, int x, int y,
                                       boolean[][] inUse, String word, int index){
        if(index==word.length()) return true;
        if(x<0 || x>=board.length || y<0 || y>=board[x].length || inUse[x][y]){
            return false;
        }
        if(board[x][y]==word.charAt(index)){
            inUse[x][y] = true;
            if( existHelper(board, x-1, y, inUse, word, index+1) ||
                existHelper(board, x+1, y, inUse, word, index+1) ||
                existHelper(board, x, y-1, inUse, word, index+1) ||
                existHelper(board, x, y+1, inUse, word, index+1) ){
                return true;
            }
            inUse[x][y] = false;
        }
        return false;
    }
}
