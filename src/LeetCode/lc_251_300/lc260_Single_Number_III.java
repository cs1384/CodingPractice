package LeetCode.lc_251_300;

import LeetCode.util.Printer;

import java.util.*;

public class lc260_Single_Number_III {
    public static void main(String[] args) {
        lc260_Single_Number_III singleNumberIii = new lc260_Single_Number_III();
        Printer.printArray(singleNumberIii.singleNumber1(new int[]{1,2,1,3,2,5}));
        Printer.printArray(singleNumberIii.singleNumber(new int[]{1,2,1,3,2,5}));
        Printer.printBits(3);
        Printer.printBits(2);
        Printer.printBits(1);
        Printer.printBits(0);
        Printer.printBits(-1);
        Printer.printBits(-2);
        Printer.printBits(-3);
//        int test = 0;
//        Printer.printBits(test <<= 1);
    }

    /*
    1. Find a bit that is different between result two nums
    2. Use that bit to separate the array into two
    3. Since each will have one that appears once and even number of ones that appear twice, another XOR operation will find answer
    RC: O(2N), SC: O(1)
     */
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int n : nums) xor ^= n;
        // xor is now XOR combination of two result nums
        int diff = 1;
        while((diff & xor) == 0) diff <<= 1;
        // diff is now different set
        int[] res = {0, 0};
        for (int n : nums) {
            if ((n & diff) == 0) res[0] ^= n;
            else res[1] ^= n;
        }
        return res;
    }

    /*
    Use set, runtime complexity O(n), space complexity O(n-2)
     */
    public int[] singleNumber1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) {
                set.remove(n);
            } else {
                set.add(n);
            }
        }
        int index = 2;
        int[] res = new int[index--];
        for (int n : set) res[index--] = n;
        return res;
    }
}
