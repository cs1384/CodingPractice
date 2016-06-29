package LeetCode.lc_051_100;

import LeetCode.util.Printer;

/**
 * Created by Tin on 6/16/16.
 */
public class lc063_Unique_Paths_II {
    public static void main(String[] args) {
        System.out.println(uniquePathsWithObstacles(new int[][]{{0,0,0},{0,1,0},{0,0,0}}));
        System.out.println(uniquePathsWithObstacles(new int[][]{{0,1}}));
    }
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid==null || obstacleGrid.length==0) return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = obstacleGrid[0][0]==1?0:1;
        for(int i=1;i<m;i++) dp[i][0] = obstacleGrid[i][0]==1?0:dp[i-1][0];
        for(int j=1;j<n;j++) dp[0][j] = obstacleGrid[0][j]==1?0:dp[0][j-1];
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(obstacleGrid[i][j]==1){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
                Printer.printMatrix(dp);
            }
        }
        return dp[m-1][n-1];
    }
}
