package LeetCode.lc_051_100;

/**
 * Created by Tin on 6/20/16.
 */
public class lc074_Search_a_2D_Matrix {
    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        }, 50));
    }
    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int low = 0;
        int high = (m-1)*n+(n-1);
        while(low<=high){
            int mid = (low+high)/2;
            int i = mid/n;
            int j = mid%n;
            if(matrix[i][j]==target){
                return true;
            }else if(matrix[i][j]<target){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return false;
    }
}
