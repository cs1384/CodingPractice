package LeetCode.lc_151_200;

import LeetCode.util.Printer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Tin on 8/2/16.
 */
public class lc179_Largest_Number {
    public static void main(String[] args) {
        System.out.println(largestNumber(new int[]{3,68,30,34,5,9}));
        System.out.println(largestNumber(new int[]{128,12}));
        System.out.println(largestNumber(new int[]{12,121}));
        System.out.println(largestNumber(new int[]{0,0}));
    }
    public static String largestNumber(int[] nums) {
        int len = nums.length;
        String[] strins = new String[len];
        for(int i=0;i<len;i++) strins[i] = String.valueOf(nums[i]);
        Arrays.sort(strins, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String ab = o1+o2;
                String ba = o2+o1;
                return ba.compareTo(ab);
            }
        });
        StringBuilder sb = new StringBuilder();
        for(String s : strins) sb.append(s);
        while(sb.length()>1 && sb.charAt(0)=='0') sb.deleteCharAt(0);
        return sb.toString();
    }
}
