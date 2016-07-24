package LeetCode.lc_101_150;

import LeetCode.util.Printer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tin on 7/14/16.
 */
public class lc120_Triangle {
    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(2);
        List<Integer> list2 = new ArrayList<>();
        list2.add(3);list2.add(4);
        List<Integer> list3 = new ArrayList<>();
        list3.add(6);list3.add(5);list3.add(7);
        List<Integer> list4 = new ArrayList<>();
        list4.add(4);list4.add(1);list4.add(8);list4.add(3);
        triangle.add(list1);triangle.add(list2);triangle.add(list3);triangle.add(list4);
        System.out.println(minimumTotal(triangle));
    }

    /*
    int[] dp version, much faster
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        int height = triangle.size();
        int[] dp = new int[triangle.get(height-1).size()];
        for(int i=0;i<dp.length;i++) dp[i] = triangle.get(height-1).get(i);
        for(int i=height-2;i>=0;i--){
            List<Integer> list = triangle.get(i);
            for(int j=0;j<list.size();j++){
                dp[j] = list.get(j)+Math.min(dp[j], dp[j+1]);
            }
        }
        return dp[0];
    }
    /*
    Without breaking input object
     */
    public static int minimumTotal2(List<List<Integer>> triangle) {
        int height = triangle.size();
        List<Integer> dp = new ArrayList<>(triangle.get(height-1));
        for(int i=height-2;i>=0;i--){
            List<Integer> list = triangle.get(i);
            for(int j=0;j<list.size();j++){
                dp.set(j, list.get(j)+Math.min(dp.get(j), dp.get(j+1)));
            }
        }
        return dp.get(0);
    }
    public static int minimumTotal1(List<List<Integer>> triangle) {
        for(int i=triangle.size()-1;i>0;i--){
            List<Integer> upper = triangle.get(i-1);
            List<Integer> cur = triangle.get(i);
            for(int j=0;j<cur.size()-1;j++){
                upper.set(j, upper.get(j)+Math.min(cur.get(j), cur.get(j+1)));
            }
            Printer.printListList(triangle);
        }
        return triangle.get(0).get(0);
    }
}
