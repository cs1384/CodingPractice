package LeetCode.lc_051_100;

import LeetCode.util.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tin on 6/22/16.
 */
public class lc078_Subsets {
    public static void main(String[] args) {
        Printer.printListList(subsets(new int[]{1,2,3}));
    }
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        int op = 0;
        while(op<(1<<len)){
            int mask = 1;
            List<Integer> list = new ArrayList<>();
            for(int num : nums){
                if((op&mask)!=0) list.add(num);
                mask <<= 1;
            }
            res.add(list);
            op++;
        }
        return res;
    }
    public List<List<Integer>> subsets1(int[] S) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());

        List<Integer> temp;
        Arrays.sort(S);
        for(int elem : S){
            List<List<Integer>> toAdd = new ArrayList<List<Integer>>();
            for(List<Integer> list : result){
                temp = new ArrayList<Integer>(list);
                temp.add(elem);
                toAdd.add(temp);
            }
            result.addAll(toAdd);
        }
        return result;
    }
}
