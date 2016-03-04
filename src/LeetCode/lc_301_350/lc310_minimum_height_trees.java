package LeetCode.lc_301_350;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Created by ytliu on 3/3/16.
 */
public class lc310_minimum_height_trees {
    public static void main(String[] args) {

        System.out.println(findMinHeightTrees(4,new int[][]{{1, 0}, {1, 2}, {1, 3}}));
        System.out.println(findMinHeightTrees(6,new int[][]{{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}}));
        System.out.println(findMinHeightTrees(6,new int[][]{{0,1},{0,2},{0,3},{3,4},{4,5}}));
    }
    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // return the trivial case
        if (n == 1) return Collections.singletonList(0);

        //adjacent list
        List<Set<Integer>> adj = new ArrayList<>(n);
        //List<Set<Integer>> adj = new LinkedList<>(n); will TLE
        for(int i=0;i<n;i++) adj.add(new HashSet<Integer>());
        for(int[] edge : edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // start from leaves
        List<Integer> leaves = new LinkedList<>();
        for(int i=0;i<n;i++) if(adj.get(i).size()==1) leaves.add(i);

        while(n>2){ // the number of roots can only be 1 or 2
            n -= leaves.size(); // n is the root candidates
            List<Integer> moreLeaves = new LinkedList<>();
            for(int i : leaves){
                Integer to = adj.get(i).iterator().next();
                if(to!=null) {
                    adj.get(to).remove(i); // going toward the middle
                    if(adj.get(to).size()==1) moreLeaves.add(to);
                }
            }
            leaves = moreLeaves;
        }
        return leaves;
    }
    // Time Limit Exceed
    public static List<Integer> findMinHeightTrees1(int n, int[][] edges) {
        Map<Integer, Set<Integer>> edgesMap = new HashMap<>();
        for(int i=0;i<edges.length;i++){
            int[] edge = edges[i];
            int end1 = edge[0];
            int end2 = edge[1];
            if(!edgesMap.containsKey(end1)) edgesMap.put(end1, new HashSet<Integer>());
            edgesMap.get(end1).add(end2);
            if(!edgesMap.containsKey(end2)) edgesMap.put(end2, new HashSet<Integer>());
            edgesMap.get(end2).add(end1);
        }
        List<Integer> res = new LinkedList<>();
        int min = Integer.MAX_VALUE;
        //n
        for(int node=0;node<n;node++){
            Set<Integer> visited = new HashSet<>();
            Queue<Integer> working = new LinkedList<>();
            working.add(node);
            working.add(null);
            int level = 0;
            //n
            while(!working.isEmpty()){
                if(level>min) break;
                Integer cur = working.poll();
                if(cur==null){
                    level++;
                    if(!working.isEmpty()) working.add(null);
                    else break;
                }else {
                    if (visited.contains(cur)) continue;
                    if(edgesMap.containsKey(cur)) working.addAll(edgesMap.get(cur));
                    visited.add(cur);
                }
            }
            if(working.isEmpty() && visited.size()==n) {
                if (level < min) {
                    min = level;
                    res = new LinkedList<>();
                }
                if (min == level) res.add(node);
            }

        }
        return res;
    }
}
