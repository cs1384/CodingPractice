package LeetCode.lc_151_200;

import LeetCode.util.Printer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tin on 8/5/16.
 */
public class lc200_Number_of_Islands {
    public static void main(String[] args) {
//        char[][] grid = {   "111".toCharArray(),
//                            "010".toCharArray(),
//                            "111".toCharArray()};
//        System.out.println(numIslands(grid)); //1
//        char[][] grid2 = {{'1'},{'0'},{'1'},{'0'},{'1'},{'1'}};
//        System.out.println(numIslands(grid2)); //3
//        char[][] grid3 = {  "10111".toCharArray(),
//                            "10101".toCharArray(),
//                            "11101".toCharArray()};
//        System.out.println(numIslands(grid3)); //1
        char[][] grid4 = {  "11110".toCharArray(),
                            "11010".toCharArray(),
                            "11000".toCharArray(),
                            "00000".toCharArray()};
        System.out.println(numIslands(grid4));
    }
    /*
    union find approach, but with some improvement
     */
    public static int numIslands(char[][] grid) {
        if(grid.length==0) return 0;
        int m = grid.length, n = grid[0].length;
        UnionFind unionFind = new UnionFind(m*n);
        int ones = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]=='1'){
                    if(i-1>=0 && grid[i-1][j]=='1') unionFind.union(i*n+j, (i-1)*n+j);
                    if(j-1>=0 && grid[i][j-1]=='1') unionFind.union(i*n+j, i*n+j-1);
                    ones++;
                }
            }
        }
        return ones-unionFind.getUnions();
    }
    /*
    union find approach, but TLE in some cases
     */
    public static int numIslands2(char[][] grid) {
        int m = grid.length;
        if(m==0) return 0;
        int n = grid[0].length;
        UnionFind unionFind = new UnionFind(m*n);
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]=='1') {
                    if (i - 1 >= 0 && grid[i - 1][j] == '1') unionFind.union(i * n + j, (i - 1) * n + j);
                    if (j - 1 >= 0 && grid[i][j - 1] == '1') unionFind.union(i * n + j, i * n + j - 1);
                }
            }
        }
        Set<Integer> islands = new HashSet<>();
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++){
                if(grid[i][j]=='1') islands.add(unionFind.root(i*n+j));
            }
        }
        return islands.size()-1;
    }
    public static class UnionFind {
        private int[] groupSize;
        private int[] indexToGroup;
        private int unions;
        public UnionFind(int n){
            unions = 0;
            indexToGroup = new int[n];
            groupSize = new int[n];
            for(int i=0;i<n;i++){
                indexToGroup[i] = i;
                groupSize[i] = 1;
            }
        }
        public int root(int i){
            while(indexToGroup[i]!=i){
                // this line compresses the path
                indexToGroup[i] = indexToGroup[indexToGroup[i]];
                i = indexToGroup[i];
            }
            return i;
        }
        public boolean find(int i, int j){
            return root(i)==root(j);
        }
        public void union(int i, int j){
            int iRoot = root(i);
            int jRoot = root(j);
            if(iRoot==jRoot) return; // necessary to avoid duplicate unions increment
            // flatten the tree
            if(groupSize[iRoot]>groupSize[jRoot]){
                indexToGroup[jRoot] = iRoot;
                groupSize[iRoot] += groupSize[jRoot];
            }else{
                indexToGroup[iRoot] = jRoot;
                groupSize[jRoot] += groupSize[iRoot];
            }
            unions++;
        }
        public int getUnions() {
            return unions;
        }
    }
    public static int numIslands1(char[][] grid) {
        int m = grid.length;
        if(m==0) return 0;
        int n = grid[0].length;
        int res = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]=='1'){
                    markTheLand(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }
    /*
    Could use BFS
     */
    private static void markTheLand(char[][] grid, int x, int y){
        int m = grid.length, n = grid[0].length;
        if(x<0 || x>=m || y<0 || y>=n || grid[x][y]=='0') return;
        grid[x][y] = '0';
        markTheLand(grid, x+1, y);
        markTheLand(grid, x-1, y);
        markTheLand(grid, x, y+1);
        markTheLand(grid, x, y-1);
    }
}
