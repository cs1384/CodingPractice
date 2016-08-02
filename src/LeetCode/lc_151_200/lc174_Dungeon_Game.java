package LeetCode.lc_151_200;

import LeetCode.util.Printer;

/**
 * Created by Tin on 8/2/16.
 */
public class lc174_Dungeon_Game {
    public static void main(String[] args) {
        int[][] dungeon = {{-2,-3,3}, {-5,-10,1}, {10,30,-5}};
        System.out.println(calculateMinimumHP(dungeon));
    }
    /*
    neat version
     */
    public static int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] hpNeededToPricess = new int[m][n];
        hpNeededToPricess[m-1][n-1] = Math.max(0, -dungeon[m-1][n-1]);
        for(int i=m-2;i>=0;i--)
            hpNeededToPricess[i][n-1] = Math.max(0, hpNeededToPricess[i+1][n-1]-dungeon[i][n-1]);
        for(int j=n-2;j>=0;j--)
            hpNeededToPricess[m-1][j] = Math.max(0, hpNeededToPricess[m-1][j+1]-dungeon[m-1][j]);
        for(int i=m-2;i>=0;i--){
            for (int j=n-2;j>=0;j--){
                int goDown = Math.max(0, hpNeededToPricess[i+1][j]-dungeon[i][j]);
                int goRight = Math.max(0, hpNeededToPricess[i][j+1]-dungeon[i][j]);
                hpNeededToPricess[i][j] = Math.min(goDown, goRight);
            }
        }
        return hpNeededToPricess[0][0]+1;
    }
    /*
    The key is you cannot below to 0 along the way
     */
    public static int calculateMinimumHP1(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] hpNeededToPricess = new int[m][n];
        hpNeededToPricess[m-1][n-1] = dungeon[m-1][n-1]<0?-dungeon[m-1][n-1]:0;
        for(int i=m-2;i>=0;i--){
            if(dungeon[i][n-1]<0){
                hpNeededToPricess[i][n-1] = -dungeon[i][n-1]+hpNeededToPricess[i+1][n-1];
            }else{
                hpNeededToPricess[i][n-1] = hpNeededToPricess[i+1][n-1]<=dungeon[i][n-1]?0:hpNeededToPricess[i+1][n-1]-dungeon[i][n-1];
            }
        }
        for(int j=n-2;j>=0;j--){
            if(dungeon[m-1][j]<0){
                hpNeededToPricess[m-1][j] = -dungeon[m-1][j]+hpNeededToPricess[m-1][j+1];
            }else{
                hpNeededToPricess[m-1][j] = hpNeededToPricess[m-1][j+1]<=dungeon[m-1][j]?0:hpNeededToPricess[m-1][j+1]-dungeon[m-1][j];
            }
        }
        for(int i=m-2;i>=0;i--){
            for(int j=n-2;j>=0;j--){
                int need = Math.min(hpNeededToPricess[i+1][j], hpNeededToPricess[i][j+1]);
                if(dungeon[i][j]<0){
                    hpNeededToPricess[i][j] = -dungeon[i][j]+need;
                }else{
                    hpNeededToPricess[i][j] = need<=dungeon[i][j]?0:need-dungeon[i][j];
                }
            }
        }
        return hpNeededToPricess[0][0]+1;
    }

}
