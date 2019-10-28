package LeetCode.lc_251_300;

import java.util.Arrays;

public class lc274_H_Index {
    public static void main(String[] args) {
        lc274_H_Index lc274_h_index = new lc274_H_Index();
        System.out.println(lc274_h_index.hIndex1(new int[]{3,0,6,1,5}));
        System.out.println(lc274_h_index.hIndex1(new int[]{}));
        System.out.println(lc274_h_index.hIndex1(new int[]{100}));
    }

    // sort and count
    public int hIndex1(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        Arrays.sort(citations);
        int count = 1;
        for (; count <= citations.length ; count++) {
            if (citations[citations.length-count] < count) break;
        }
        return count -1;
    }
}
