package LeetCode.lc_051_100;

import LeetCode.util.Printer;

import java.util.*;

/**
 * Created by Tin on 6/26/16.
 */
public class lc090_Subsets_II {
    public static void main(String[] args) {
        Printer.printListList(subsetsWithDup3(new int[]{1, 2, 2}));
        Printer.printListList(subsetsWithDup3(new int[]{4,4,4,1,4}));
    }

    /*
    DFS: in each level, don't form a new list by skipping same element
     */
    public static List<List<Integer>> subsetsWithDup3(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> op = new ArrayList<>();
        subsetsWithDupDFS(op, num, 0, res);
        return res;
    }
    public static void subsetsWithDupDFS(List<Integer> op, int[] num,
                                                        int start, List<List<Integer>> res){
        res.add(op);
        for(int i=start;i<num.length;i++){
            if(i>start && num[i]==num[i-1]) continue;
            List<Integer> newOp = new ArrayList<>(op);
            newOp.add(num[i]);
            subsetsWithDupDFS(newOp, num, i+1, res);
        }
    }

    /*
    example [1,2,2,2]
    Treat [2], [2,2], [2,2,2] as individual elements in interative approach
     */
    public static List<List<Integer>> subsetsWithDup2(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        int i = 0;
        while(i<num.length){
            List<List<Integer>> newRes = new ArrayList<>(res);
            List<Integer> op = new ArrayList<>();
            do{
                op.add(num[i]);
                for(List<Integer> list : res){
                    List<Integer> toAdd = new ArrayList<>(list);
                    toAdd.addAll(op);
                    newRes.add(toAdd);
                }
            }while(++i<num.length && num[i]==num[i-1]);
            res = newRes;
        }
        return res;
    }

    /*
    Use set to detect duplicate
     */
    public static List<List<Integer>> subsetsWithDup1(int[] num) {
        Arrays.sort(num);
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        set.add(new ArrayList<Integer>());
        for(int i : num){
            Set<List<Integer>> op = new HashSet<List<Integer>>();
            for(List<Integer> list : set){
                List<Integer> temp = new ArrayList<Integer>();
                temp.addAll(list);
                temp.add(i);
                op.add(temp);
            }
            set.addAll(op);
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.addAll(set);
        return result;
    }
}
