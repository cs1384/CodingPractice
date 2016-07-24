package AlgorithmDatastructure;

import LeetCode.util.Printer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Tin on 7/11/16.
 */
public class DisjointSet_UnionFind {
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
        // customized method for longestConsecutive1()
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
    /*
    https://www.hackerearth.com/notes/disjoint-set-union-union-find/
     */
    public static void main(String[] args) {
        System.out.println(friendCircles(new String[]{
                "YYNN",
                "YYYN",
                "NYYN",
                "NNNY"
        }));
        System.out.println(friendCircles(new String[]{
                "YNNNN",
                "NYNNN",
                "NNYNN",
                "NNNYN",
                "NNNNY"
        }));
        System.out.println(longestConsecutive1(new int[]{5, 3, 2, 4, 1}));
        System.out.println(longestConsecutive1(new int[]{100, 4, 200, 1, 3, 2}));
    }
    public static int friendCircles(String[] isFriend){
        int nStudent = isFriend.length;
        int[] belongCircle = new int[nStudent];
        for(int i=0;i<nStudent;i++) belongCircle[i] = i;
        for(int i=0;i<nStudent;i++){
            String relations = isFriend[i];
            for(int j=i+1;j<relations.length();j++){
                if(relations.charAt(j)=='Y' && belongCircle[i]!=belongCircle[j]){
                    belongCircle[j] = belongCircle[i];
                }
            }
        }
        Set<Integer> circleNo = new HashSet<>();
        for(int i=0;i<nStudent;i++) circleNo.add(belongCircle[i]);
        return circleNo.size();
    }
    /*
    lc128_Longest_Consecutive_Sequence
     */
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
}
