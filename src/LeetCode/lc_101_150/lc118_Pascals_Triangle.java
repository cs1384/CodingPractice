package LeetCode.lc_101_150;

import LeetCode.util.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tin on 7/4/16.
 */
public class lc118_Pascals_Triangle {
    public static void main(String[] args) {
        Printer.printListList(generate(5));
    }
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if(numRows==0) return res;
        res.add(Arrays.asList(1));
        for(int i=1;i<numRows;i++){
            List<Integer> pre = res.get(i-1);
            List<Integer> list = new ArrayList<>();
            for(int j=0;j<=i;j++){
                int left = j>0?pre.get(j-1):0;
                int right = (j<pre.size())?pre.get(j):0;
                list.add(left+right);
            }
            res.add(list);
        }
        return res;
    }
}
