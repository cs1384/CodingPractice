package LeetCode.lc_101_150;

import DataStructureImplement.UnionFind;
import LeetCode.util.Printer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Tin on 7/22/16.
 */
public class lc130_Surrounded_Regions {
    public static void main(String[] args) {
        String[] arr = {"XOOOOOOOOOOOOOOOOOOO","OXOOOOXOOOOOOOOOOOXX","OOOOOOOOXOOOOOOOOOOX","OOXOOOOOOOOOOOOOOOXO","OOOOOXOOOOXOOOOOXOOX","XOOOXOOOOOXOXOXOXOXO","OOOOXOOXOOOOOXOOXOOO","XOOOXXXOXOOOOXXOXOOO","OOOOOXXXXOOOOXOOXOOO","XOOOOXOOOOOOXXOOXOOX","OOOOOOOOOOXOOXOOOXOX","OOOOXOXOOXXOOOOOXOOO","XXOOOOOXOOOOOOOOOOOO","OXOXOOOXOXOOOXOXOXOO","OOXOOOOOOOXOOOOOXOXO","XXOOOOOOOOXOXXOOOXOO","OOXOOOOOOOXOOXOXOXOO","OOOXOOOOOXXXOOXOOOXO","OOOOOOOOOOOOOOOOOOOO","XOOOOXOOOXXOOXOXOXOO"};
//        String[] arr = {"XXXX","XOOX", "XXOX", "XOXX"};
        char[][] board = new char[arr.length][arr[0].length()];
        for(int i=0;i<arr.length;i++){
            board[i] = arr[i].toCharArray();
        }
        solve1(board);
        Printer.printMatrix(board);
    }
    public static void solve(char[][] board) {
        int m = board.length;
        if(m==0) return;
        int n = board[0].length;
        UnionFind unionFind = new UnionFind(m*n+1);
        int dummyUFIndex = m*n;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='O'){
                    int curUFIndex = i*n+j;
                    if(i==0 || j==0 || i==m-1 || j==n-1) unionFind.union(curUFIndex, dummyUFIndex);
                    if(i-1>=0 && board[i-1][j]=='O') unionFind.union((i-1)*n+j, curUFIndex);
                    if(i+1<m && board[i+1][j]=='O') unionFind.union((i+1)*n+j, curUFIndex);
                    if(j-1>=0 && board[i][j-1]=='O') unionFind.union(i*n+j-1, curUFIndex);
                    if(j+1<n && board[i][j+1]=='O') unionFind.union(i*n+j+1, curUFIndex);
                }
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='X') continue;
                int curUFIndex = i*n+j;
                if(unionFind.find(curUFIndex, m*n)) board[i][j] = 'O';
                else board[i][j] = 'X';
            }
        }
    }
    public static void solve1(char[][] board) {
        int m = board.length;
        if(m==0) return;
        int n = board[0].length;
        for(int i=0;i<m;i++){
            if(board[i][0]=='O') solveDFS(board, i, 0);
            if(board[i][n-1]=='O') solveDFS(board, i, n - 1);
        }
        for(int j=0;j<n;j++){
            if(board[0][j]=='O') solveDFS(board, 0, j);
            if(board[m-1][j]=='O') solveDFS(board, m - 1, j);
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='O') board[i][j] = 'X';
                else if(board[i][j]=='#') board[i][j] = 'O';
            }
        }
    }
    private static void solveBFS(char[][] board, int i, int j){
        int m = board.length, n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i,j});
        while(!queue.isEmpty()){
            int[] coordinates = queue.poll();
            int x = coordinates[0];
            int y = coordinates[1];
            if(x<0 || x>=m || y<0 || y>=n || board[x][y]=='#' || board[x][y]=='X')
                continue;
            board[x][y] = '#';
            queue.offer(new int[]{x+1, y});
            queue.offer(new int[]{x-1, y});
            queue.offer(new int[]{x, y+1});
            queue.offer(new int[]{x, y-1});
        }
    }
    /*
    TLE
     */
    private static void solveBFS1(char[][] board, int i, int j){
        int m = board.length, n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i,j});
        while(!queue.isEmpty()){
            int[] coordinates = queue.poll();
            int x = coordinates[0];
            int y = coordinates[1];
            board[x][y] = '#';
            if(x+1<m && board[x+1][y]=='O') queue.offer(new int[]{x+1, y});
            if(x-1>=0 && board[x-1][y]=='O') queue.offer(new int[]{x-1, y});
            if(y+1<n && board[x][y+1]=='O') queue.offer(new int[]{x, y+1});
            if(y-1>=0 && board[x][y-1]=='O') queue.offer(new int[]{x, y-1});
        }
    }
    /*
    Error eliminated....
     */
    private static void solveDFS(char[][] board, int x, int y){
        int m = board.length, n = board[0].length;
        board[x][y] = '#';
        if(x<m-1 && board[x+1][y]=='O') solveDFS(board, x + 1, y);
        if(x>1 && board[x-1][y]=='O') solveDFS(board, x-1, y);
        if(y<n-1 && board[x][y+1]=='O') solveDFS(board, x, y+1);
        if(y>1 && board[x][y-1]=='O') solveDFS(board, x, y-1);
    }
    /*
    StackOverFlow
     */
    private static void solveDFS1(char[][] board, int x, int y){
        int m = board.length, n = board[0].length;
        board[x][y] = '#';
        if(x+1<m && board[x+1][y]=='O') solveDFS(board, x + 1, y);
        if(x-1>=0 && board[x-1][y]=='O') solveDFS(board, x-1, y);
        if(y+1<n && board[x][y+1]=='O') solveDFS(board, x, y+1);
        if(y-1>=0 && board[x][y-1]=='O') solveDFS(board, x, y-1);
    }
}
