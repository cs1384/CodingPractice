package DataStructureImplement;

import java.util.Arrays;

/**
 * Created by Tin on 7/22/16.
 */
/*
https://www.cs.princeton.edu/~rs/AlgsDS07/01UnionFind.pdf
http://www.geeksforgeeks.org/union-find/
http://www.geeksforgeeks.org/union-find-algorithm-set-2-union-by-rank/

complexity  union   find(root)
general     O(N)    O(N)
improved    O(logN) O(logN)
 */
public class UnionFind {
    private int[] groupSize;
    private int[] indexToGroup;
    public UnionFind(int n){
        indexToGroup = new int[n];
        groupSize = new int[n];
        for(int i=0;i<n;i++){
            indexToGroup[i] = i;
            groupSize[i] = 1;
        }
    }
    public int root(int i){
        while(indexToGroup[i]!=i){
            // this line compresses the path
            indexToGroup[i] = indexToGroup[indexToGroup[i]];
            i = indexToGroup[i];
        }
        return i;
    }
    public boolean find(int i, int j){
        return root(i)==root(j);
    }
    public void union(int i, int j){
//        System.out.println("uniting "+i+" and "+j);
        int iRoot = root(i);
        int jRoot = root(j);
        // flatten the tree
        if(groupSize[iRoot]>groupSize[jRoot]){
            indexToGroup[jRoot] = iRoot;
            groupSize[iRoot] += groupSize[jRoot];
        }else{
            indexToGroup[iRoot] = jRoot;
            groupSize[jRoot] += groupSize[iRoot];
        }
    }
    public int[] getIndexToGroup() {
        for(int i=0;i<indexToGroup.length;i++)
            indexToGroup[i] = root(i);
        return indexToGroup;
    }
}
