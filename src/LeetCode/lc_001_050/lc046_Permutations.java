package LeetCode.lc_001_050;

import LeetCode.util.Printer;

import java.util.*;

/**
 * Created by Tin on 6/11/16.
 */
public class lc046_Permutations {
    public static void main(String[] args) {
        Printer.printListList(permute2(new int[]{1, 2, 3}));
    }

    /**
     * insert the number in between the elements of current list
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        res.add(new LinkedList<>());
        for(int i=0;i<nums.length;i++){
            List<List<Integer>> temp = new LinkedList<>();
            for(List<Integer> list : res){
                for(int j=0;j<=list.size();j++){
                    list.add(j, nums[i]);
                    List<Integer> newList = new LinkedList<>(list);
                    temp.add(newList);
                    list.remove(j);
                }
            }
            res = temp;
        }
        return res;
    }

    /**
     * based on permute1, but use Set to avoid entire looping through
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Set<Integer> notUsed = new HashSet<>();
        for(int i=0;i<nums.length;i++) notUsed.add(i);
        permuteDFS2(nums, new LinkedList<>(), notUsed, res);
        return res;
    }
    private static void permuteDFS2(int[] nums, LinkedList<Integer> op, Set<Integer> notUsed, List<List<Integer>> res){
        if(notUsed.size()==0){
            res.add(new LinkedList<>(op));
            return;
        }
        for(int i : notUsed){
            op.add(nums[i]);
            Set<Integer> nextSet = new HashSet<>(notUsed);
            nextSet.remove(i);
            permuteDFS2(nums, op, nextSet, res);
            op.remove(op.size()-1);
        }
    }

    /**
     * Use a tracker to track which number has been inserted into the list
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        BitSet tracker = new BitSet(nums.length);
        permuteDFS1(nums, new LinkedList<>(), tracker, 0, res);
        return res;
    }
    private static void permuteDFS1(int[] nums, LinkedList<Integer> op, BitSet tracker, int added, List<List<Integer>> res){
        if(added==nums.length){
            res.add(new LinkedList<>(op));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(!tracker.get(i)){
                tracker.set(i);
                op.add(nums[i]);
                permuteDFS1(nums, op, tracker, added+1, res);
                op.remove(op.size()-1);
                tracker.clear(i);
            }
        }
    }

}
