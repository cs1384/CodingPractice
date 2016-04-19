package LeetCode.lc_001_050;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 4/18/16.
 */
public class lc018_4Sum {
    public static void main(String[] args) {
        Printer.printListList(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
    }
    public static List<List<Integer>> fourSum(int[] num, int target) {
        Arrays.sort(num);
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {
            if(i>0 && num[i]==num[i-1]) continue;
            for (int j = i + 1; j < num.length; j++) {
                if(j>i+1 && num[j]==num[j-1]) continue;
                int k = j + 1;
                int l = num.length - 1;
                while (k < l) {
                    int sum = num[i] + num[j] + num[k] + num[l];
                    if (sum > target) {
                        l--;
                    } else if (sum < target) {
                        k++;
                    } else if (sum == target) {
                        List<Integer> temp = new LinkedList<>();
                        temp.add(num[i]);
                        temp.add(num[j]);
                        temp.add(num[k]);
                        temp.add(num[l]);
                        result.add(temp);
                        do k++; while(k<l && num[k]==num[k-1]);
                        do l--; while(l>k && num[l]==num[l+1]);
                    }
                }
            }
        }
        return result;
    }
}
