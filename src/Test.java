import LeetCode.util.Printer;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by ytliu on 3/23/16.
 */
public class Test {
    public static void main(String[] args) {
//        System.out.println(is_Palindrome("aaabbbb"));
//        System.out.println(is_Palindrome("cdefghmnopqrstuvw"));
        System.out.println(bitFlip(new int[]{1,0,0,1,0,0,1,0}));
    }
    static int bitFlip(int[] arr) {
        int leftIndex = -1, rightIndex = -1;
        int tracker = 0, max = 0;
        int nOne = 0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]==1){
                tracker--;
                nOne++;
            }
            else{
                tracker++;
                if(leftIndex==-1) leftIndex = i;
            }
            if(tracker<0){
                leftIndex = -1;
                rightIndex = -1;
                tracker = 0;
            }else if(tracker>max){
                rightIndex = i;
                max = tracker;
            }
        }
        return max+nOne;
    }
    static String is_Palindrome(String s) {
        int[] tracker = new int[256];
        for(char c : s.toCharArray()) tracker[c]++;
        boolean odd = false;
        for(int i : tracker){
            if(i%2!=0){
                if(odd) return "NO";
                else odd = true;
            }
        }
        return "YES";
    }




    static int zeros(int n) {
        int op = 5;
        int res = 0;
        while(op<=n){
            res += (n/op);
            op *= 5;
        }
        return res;
    }
    static int sum(int[] numbers) {

        int sum = 0;
        for(int i=0;i<numbers.length;i++){
            sum += numbers[i];
        }
        return sum;
    }












    private static void AlgorithmicCrush() {
        Scanner scanner = new Scanner(System.in);
        int sizeOfList = scanner.nextInt();
        long[] change = new long[sizeOfList];
        int nOperation = scanner.nextInt();
        for(int i=0; i<nOperation; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            long adding = scanner.nextLong();
            if(adding==0) continue;
            change[from-1] += adding;
            if(to<sizeOfList) change[to]-=adding;
        }
        long sum = 0;
        long max = 0;
        for (int i = 0; i < sizeOfList; i++) {
            sum += change[i];
            max = Math.max(max, sum);
        }
        System.out.println(max);
    }
    static int[] mergeArrays(int[] a, int[] b) {
        int m = a.length;
        int[] res = new int[m*2];
        int aIndex = m-1;int bIndex = m-1;
        for(int i=res.length-1;i>=0;i--){
            if(aIndex>=0 && bIndex>=0){
                if(b[bIndex]>=a[aIndex]) res[i] = b[bIndex--];
                else res[i] = a[aIndex--];
            }else if(aIndex>=0){
                res[i] = a[aIndex--];
            }else{
                res[i] = b[bIndex--];
            }
        }
        return res;
    }
    static String firstRepeatedWord(String s) {
        String[] words = s.trim().split("[\\s\\t,:;\\-\\.]+");
        Set<String> shownWords = new HashSet<>();
        for(String w : words){
            if(shownWords.contains(w)) return w;
            else shownWords.add(w);
        }
        return null;
    }
}
