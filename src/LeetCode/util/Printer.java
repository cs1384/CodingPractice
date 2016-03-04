package LeetCode.util;

/**
 * Created by ytliu on 3/4/16.
 */
public class Printer {
    public static void printMetrix(int[][] m){
        System.out.println("[");
        for(int i=0;i<m.length;i++){
            System.out.print(" [");
            for(int j=0;j<m[i].length;j++){
                System.out.print(m[i][j]+", ");
            }
            System.out.println("]");
        }
        System.out.println("]");
    }
}
