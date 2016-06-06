package LeetCode.lc_001_050;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 6/5/16.
 */
public class lc040_Combination_Sum_II {
    public static void main(String[] args) {
        Printer.printListList(combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new LinkedList<>();
        combinationSum2DFS(candidates, 0, new LinkedList<Integer>(), target, res);
        return res;
    }
    private static void combinationSum2DFS(int[] candidates, int workingIndex, LinkedList<Integer> cur, int target, List<List<Integer>> res){
        if(target==0){
            res.add(new LinkedList<>(cur));
            return;
        }
        if(workingIndex==candidates.length) return;
        for(int i=workingIndex;i<candidates.length&&target-candidates[i]>=0;i++){
            // prevent duplicate
            if(i!=0 && i-1>=workingIndex && candidates[i]==candidates[i-1]) continue;
            cur.add(candidates[i]);
            combinationSum2DFS(candidates, i + 1, cur, target - candidates[i], res);
            cur.remove(cur.size() - 1);
        }
    }
}
