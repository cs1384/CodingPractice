package LeetCode.lc_001_050;

import LeetCode.util.Printer;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Tin on 6/13/16.
 */
public class lc047_Permutations_II {
    public static void main(String[] args) {
        Printer.printListList(permuteUnique(new int[]{1,1,2}));
        Printer.printListList(permuteUnique(new int[]{-1,2,-1,2,1,-1,2,1}));
    }
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        permuteUniqueDFS(nums, new ArrayList<>(), used, res);
        return res;
    }

    /**
     * Using boolean[] did not help the speed but ArrayList did!!
     * http://stackoverflow.com/questions/322715/when-to-use-linkedlist-over-arraylist
     * @param nums
     * @param op
     * @param used
     * @param res
     */
    private static void permuteUniqueDFS(int[] nums, ArrayList<Integer> op, boolean[] used, List<List<Integer>> res){
        if(op.size()==nums.length){
            res.add(new ArrayList<>(op));
            return;
        }
        for(int i=0;i<nums.length;i++){
            // later duplicate should be used after former
            if(i>0 && nums[i-1]==nums[i] && !used[i-1]) continue;
            if(!used[i]){
                used[i] = true;
                op.add(nums[i]);
                permuteUniqueDFS(nums, op, used, res);
                op.remove(op.size()-1);
                used[i] = false;
            }
        }
    }
    public static List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        BitSet tracker = new BitSet(nums.length);
        permuteUniqueDFS2(nums, new LinkedList<>(), tracker, res);
        return res;
    }
    private static void permuteUniqueDFS2(int[] nums, LinkedList<Integer> op, BitSet tracker, List<List<Integer>> res){
        if(op.size()==nums.length){
            res.add(new LinkedList<>(op));
            return;
        }
        for(int i=0;i<nums.length;i++){
            // later duplicate should be used after former
            if(i>0 && nums[i-1]==nums[i] && !tracker.get(i-1)) continue;
            if(!tracker.get(i)){
                tracker.set(i);
                op.add(nums[i]);
                permuteUniqueDFS2(nums, op, tracker, res);
                op.remove(op.size()-1);
                tracker.clear(i);
            }
        }
    }

    public static List<List<Integer>> permuteUnique1(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        res.add(new LinkedList<Integer>());
        for(int i=0;i<num.length;i++){
            List<List<Integer>> newRes = new LinkedList<>();
            for(List<Integer> list : res){
                for(int j=list.size();j>=0;j--){
                    if(j!=list.size() && list.get(j)==num[i]) break;
                    List<Integer> permutation = new LinkedList<>(list);
                    permutation.add(j,num[i]);
                    newRes.add(permutation);
                }
            }
            res = newRes;
        }
        return res;
    }
}
