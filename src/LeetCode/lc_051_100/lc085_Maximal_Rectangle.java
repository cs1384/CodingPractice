package LeetCode.lc_051_100;

import LeetCode.util.Printer;

import java.util.Stack;

/**
 * Created by Tin on 6/23/16.
 */
public class lc085_Maximal_Rectangle {
    public static void main(String[] args) {
        char[][] matrix1 = { {'0','1','1','0','1'},
                            {'0','1','1','1','1'},
                            {'0','0','0','0','1'},
                            {'0','1','0','1','1'},
                            {'0','1','1','0','1'}};
        System.out.println(maximalRectangle(matrix1));
        char[][] matrix2 = {{'1','0','1','0'},
                            {'1','0','1','1'},
                            {'1','0','1','1'},
                            {'1','1','1','1'}}; //6
        System.out.println(maximalRectangle(matrix2));
    }
    public static int maximalRectangle(char[][] matrix){
        int m = matrix.length;
        if(m==0) return 0;
        int n = matrix[0].length;
        int[] leftBorders = new int[n];
        int[] rightBorders = new int[n];
        for(int i=0;i<n;i++) rightBorders[i] = n;
        int[] heights = new int[n];
        int max = 0;
        for(int i=0;i<m;i++) {
            int curLeftEdge = 0, curRightEdge = n;
            for (int j=0;j<n;j++) {
                if(matrix[i][j]=='1') heights[j]++;
                else heights[j] = 0;
            }
            for (int j=0;j<n;j++) {
                if(matrix[i][j]=='1')
                    // either use border of last row or curLeftBorder in this row
                    leftBorders[j] = Math.max(leftBorders[j],curLeftEdge);
                else{
                    /*
                    Set to 0 for next row. Since it would be a new height 1, the left
                     border can be as left as possible
                     */
                    leftBorders[j] = 0;
                    curLeftEdge = j+1; // for current row, left border is here
                }
            }
            for(int j=n-1;j>=0;j--){
                if(matrix[i][j]=='1')
                    rightBorders[j] = Math.min(rightBorders[j], curRightEdge);
                else{
                    rightBorders[j] = n;
                    curRightEdge = j;
                }
            }
            for (int j=0;j<n;j++) {
                max = Math.max(max, (rightBorders[j]-leftBorders[j])*heights[j]);
            }
        }
        return max;
    }
    /*
    Based on lc084_Largest_Rectangle_in_Histogram
     */
    public static int maximalRectangle1(char[][] matrix) {
        int m = matrix.length;
        if(m==0) return 0;
        int n = matrix[0].length;
        int[] heights = new int[n];
        int max = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]=='1'){
                    heights[j]++;
                }else{
                    heights[j] = 0;
                }
            }
            max = Math.max(max, largestRectangleArea(heights));
        }
        return max;
    }
    public static int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if(len==0) return 0;
        Stack<Integer> capIndice = new Stack<>();
        int max = 0;
        for(int i=0;i<=len;i++){
            int curCap = i<len?heights[i]:0;
            while(!capIndice.isEmpty() && heights[capIndice.peek()]>curCap){
                int index = capIndice.pop();
                int area;
                if(capIndice.isEmpty()) area = heights[index]*(i-0);
                else area = heights[index]*(i-(capIndice.peek()+1));
                max = Math.max(max, area);
            }
            capIndice.push(i);
        }
        return max;
    }
}
