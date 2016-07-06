package LeetCode.lc_251_300;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Tin on 7/4/16.
 */
public class lc286_Walls_and_Gates {
    /*
    http://blog.csdn.net/xudli/article/details/48748547
     */
    public static void main(String[] args) {

    }
    public static void wallsAndGates2(int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(rooms[i][j]==0) queue.offer(new int[]{i,j});
            }
        }
        while(!queue.isEmpty()){
            int[] coordinates = queue.poll();
            int i = coordinates[0], j = coordinates[1];
            int curDistance = rooms[i][j]+1;
            if(i>0 && rooms[i-1][j]>curDistance+1){
                rooms[i-1][j] = curDistance+1;
                queue.offer(new int[]{i-1,j});
            }
            if(i<m-1 && rooms[i+1][j]>curDistance+1){
                rooms[i+1][j] = curDistance+1;
                queue.offer(new int[]{i+1,j});
            }
            if(j>0 && rooms[i][j-1]>curDistance+1){
                rooms[i][j-1] = curDistance+1;
                queue.offer(new int[]{i,j-1});
            }
            if(j<m-1 && rooms[i][j+1]>curDistance+1){
                rooms[i][j+1] = curDistance+1;
                queue.offer(new int[]{i,j+1});
            }
        }
    }
    public static void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(rooms[i][j]==0) wallsAndGatesHelper(rooms, i, j, 0);
            }
        }
    }
    private static void wallsAndGatesHelper(int[][] rooms, int x, int y, int distance){
        // start point (i,j) would not be less than d, so the process will be triggered
        if((x<0 || x>=rooms.length) || (y<0 || y>=rooms[0].length) || rooms[x][y]<distance)
            return;
        rooms[x][y] = distance;
        wallsAndGatesHelper(rooms, x+1, y, distance+1);
        wallsAndGatesHelper(rooms, x-1, y, distance+1);
        wallsAndGatesHelper(rooms, x, y+1, distance+1);
        wallsAndGatesHelper(rooms, x, y - 1, distance + 1);
    }
}
