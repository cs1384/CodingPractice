package LeetCode.lc_001_050;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 6/5/16.
 */
public class lc039_Combination_Sum {
    public static void main(String[] args) {
        Printer.printListList(combinationSum2(new int[]{2,3,6,7}, 7));
    }
    /*
    DP solution
     */
    public static  List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        Map<Integer, List<List<Integer>>> dp = new HashMap<>();
        for(int i=1;i<=target;i++){
            List<List<Integer>> combinationList = new LinkedList<>();
            for(int j=0;j<candidates.length && candidates[j]<=i;j++){
                if(candidates[j]==i){
                    List<Integer> list = new LinkedList<>();
                    list.add(candidates[j]);
                    combinationList.add(list);
                }else{
                    int need = i-candidates[j];
                    if(dp.get(need)==null) continue;
                    for(List<Integer> list : dp.get(need)){
                        // this check to make sure all lists are in ascending order, equal sign to allow repeated use
                        if(candidates[j]<=list.get(0)) {
                            List<Integer> newList = new LinkedList<>();
                            newList.add(candidates[j]);
                            newList.addAll(list);
                            combinationList.add(newList);
                        }
                    }
                }
            }
            dp.put(i,combinationList);
        }
        return dp.get(target);
    }
    public static  List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new LinkedList<>();
        combinationSumDFS(candidates, 0, new LinkedList<Integer>(), target, res);
        return res;
    }
    private static void combinationSumDFS(int[] candidates, int workingIndex, LinkedList<Integer> cur, int target, List<List<Integer>> res){
        if(target==0){
            res.add(new LinkedList<>(cur));
            return;
        }
        if(workingIndex==candidates.length) return;
        combinationSumDFS(candidates, workingIndex+1, cur, target, res);
        int time = 0;
        while((target -= candidates[workingIndex]) >= 0){
            cur.add(candidates[workingIndex]);
            combinationSumDFS(candidates, workingIndex+1, cur, target, res);
            time++;
        }
        while(time>0){
            cur.remove(cur.size()-1);
            time--;
        }
    }
}
