package Interview;

import LeetCode.lc_251_300.lc286_Walls_and_Gates;
import LeetCode.util.Printer;

import java.util.*;
import java.util.regex.Matcher;

/**
 * Created by Tin on 7/3/16.
 */
public class Amazon {
    public static void main(String[] args) {
        System.out.println("1. Social graph");
        System.out.println("http://www.1point3acres.com/bbs/thread-145926-1-1.html");

        System.out.println("2. Wall and Gates");
        System.out.println("lc286_Walls_and_Gates");

        System.out.println("3. Pascal's Triangle");
        System.out.println("https://leetcode.com/problems/pascals-triangle/");

        System.out.println("4. Algorithmic Crush");
        System.out.println("https://www.hackerrank.com/contests/w4/challenges/crush");
//        AlgorithmicCrush();

        System.out.println("5. first repeated word");
        System.out.println("http://www.cnblogs.com/lilyfindjobs/p/4225953.html");
        System.out.println("https://www.hackerrank.com/challenges/duplicate-word/forum");
        System.out.println(firstRepeatedWord("  he had had a good son."));

        System.out.println("6. min operations");
        System.out.println("http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=194035&extra=page%3D1%26filter%3Dauthor%26orderby%3Ddateline%26sortid%3D311%26sortid%3D311%26orderby%3Ddateline");
        int test = 25;
        System.out.println(minOperations1(test));
        int[] test1 = new int[test+1];
        int[] test2 = new int[test+1];
        for(int i=0;i<=test;i++){
            test1[i] = minOperations2(i);
            test2[i] = minOperations(i);
        }
        Printer.printArray(test1);
        Printer.printArray(test2);


    }
    public static int minOperations(int n){
        int res = 0;
        while(n>1){
            res += 1+((n&1)==1?1:0);
            n >>= 1;
        }
        return res+n;
    }
    public static int minOperations2(int n){
        int res = 0;
        while(n!=0){
            if(n%2!=0) n--;
            else n /= 2;
            res++;
        }
        return res;
    }
    public static int minOperations1(int n){
        int[] dp = new int[n+1];
        for(int i=0;i<=3;i++) dp[i] = i;
        for(int i=4;i<=n;i++){
            dp[i] = Math.min(dp[i-1]+1, dp[i/2]+1+(i%2==0?0:1));
        }
        Printer.printArray(dp);
        return dp[n];
    }
    public static String firstRepeatedWord(String sentence){
        String[] words = sentence.split("[\\s,;:\\-\\.]+");
        Printer.printArray(words);
        Set<String> shownWords = new HashSet<>();
        for(String w : words){
            if(w.equals("")) continue;
            if(shownWords.contains(w)) return w;
            else shownWords.add(w);
        }
        return null;
    }

    private static void AlgorithmicCrush() {
        Scanner scanner = new Scanner(System.in);
        int sizeOfList = scanner.nextInt();
        long[] change = new long[sizeOfList];
        int nOperation = scanner.nextInt();
        for (int i = 0; i < nOperation; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            long adding = scanner.nextLong();
            if (adding == 0) continue;
            change[from - 1] += adding;
            if (to < sizeOfList) change[to] -= adding;
        }
        long sum = 0;
        long max = 0;
        for (int i = 0; i < sizeOfList; i++) {
            sum += change[i];
            max = Math.max(max, sum);
        }
        System.out.println(max);
    }
}
