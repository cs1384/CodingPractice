package LeetCode.lc_101_150;

import LeetCode.util.Printer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tin on 7/14/16.
 */
public class lc119_Pascal_s_Triangle_II {
    public static void main(String[] args) {
        Printer.printList(getRow(3));
    }
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        for(int i=0;i<=rowIndex;i++){
            res.add(1);
            for(int j=res.size()-2;j>0;j--){
                res.set(j, res.get(j-1)+res.get(j));
            }
        }
        return res;
    }
    public static List<Integer> getRow1(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        for(int i=0;i<rowIndex;i++){
            List<Integer> newRes = new ArrayList<>();
            newRes.add(1);
            for(int j=0;j<res.size()-1;j++){
                newRes.add(res.get(j)+res.get(j+1));
            }
            newRes.add(1);
            res = newRes;
        }
        return res;
    }
}
