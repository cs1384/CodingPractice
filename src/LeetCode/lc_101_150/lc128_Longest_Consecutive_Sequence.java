package LeetCode.lc_101_150;

import LeetCode.util.Printer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Tin on 7/20/16.
 */
public class lc128_Longest_Consecutive_Sequence {
    public static void main(String[] args) {
        System.out.println(longestConsecutive1(new int[]{5, 3, 2, 4, 1}));
        System.out.println(longestConsecutive1(new int[]{100, 4, 200, 1, 3, 2}));
    }
    public static int longestConsecutive(int[] nums) {
        Map<Integer, Integer> numToSequence = new HashMap<>();
        int longest = 0;
        for(int num : nums){
            if(numToSequence.containsKey(num)) continue;
            int leftSequence = numToSequence.containsKey(num-1)?numToSequence.get(num-1):0;
            int rightSequence = numToSequence.containsKey(num+1)?numToSequence.get(num+1):0;
            int sequence = leftSequence+rightSequence+1;
            numToSequence.put(num, sequence);
            longest = Math.max(longest, sequence);
            // update the edges
            numToSequence.put(num-leftSequence, sequence);
            numToSequence.put(num+rightSequence, sequence);
        }
        return longest;
    }
    /*
    How to know the neighboring number exists? Set!
     */
    public static int longestConsecutive2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i : nums) set.add(i); //don't care about duplicates
        int longest = 0;
        for(int i : nums){
            if(!set.contains(i)) continue;
            int count = 1;
            set.remove(i);
            int op = i+1;
            while(set.contains(op)){
                count++;
                set.remove(op++);
            }
            op = i-1;
            while(set.contains(op)){
                count++;
                set.remove(op--);
            }
            longest = Math.max(longest, count);
        }
        return longest;
    }
    public static int longestConsecutive1(int[] nums) {
        int len = nums.length;
        UnionFind unionFind = new UnionFind(len);
        Map<Integer, Integer> numToIndex = new HashMap<>();
        for(int i=0;i<len;i++){
            int num = nums[i];
            if(numToIndex.containsKey(num)) continue;
            if(numToIndex.containsKey(num-1))
                unionFind.union(i, numToIndex.get(num-1));
            if(numToIndex.containsKey(num+1))
                unionFind.union(i, numToIndex.get(num+1));
            numToIndex.put(num, i);
        }
        return unionFind.SizeOflargestGroup();
    }
    static class UnionFind{
        // basic methods
        int[] belongGroup;
        UnionFind(int n){
            belongGroup = new int[n];
            for(int i=0;i<n;i++) belongGroup[i]=i;
        }
        boolean isConnected(int i, int j){
            return belongGroup[i]==belongGroup[j];
        }
        int root(int i){
            while(belongGroup[i]!=i) i = belongGroup[i];
            return i;
        }
        void union(int i, int j){
            int iRoot = root(i);
            int jRoot = root(j);
            belongGroup[jRoot] = iRoot;
        }
        // customized method
        int SizeOflargestGroup(){
            int len = belongGroup.length;
            int[] sizes = new int[len];
            int largest = 0;
            for(int i=0;i<len;i++){
                int group = root(i);
                sizes[group]++;
                largest = Math.max(largest, sizes[group]);
            }
            return largest;
        }
    }
}
