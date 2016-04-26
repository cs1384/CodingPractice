package LeetCode.lc_001_050;

/**
 * Created by ytliu on 4/25/16.
 */
public class lc028_Implement_strStr {
    public static void main(String[] args) {
        System.out.println(naiveIndexOf("TinIsAwesome", "IsA"));
        System.out.println(kmpIndexOf("TinIsAwesome", "IsA"));
        System.out.println(kmpIndexOf("TinIsAwesome", "ababaca"));
        System.out.println(kmpIndexOf("bbaa", "aab"));
    }
    private static int[] getNextStart(char[] str) {
        int len = str.length;
        int[] next = new int[len];
        next[0] = 0; // not matched anything, so shift to index 0
        int matched = 0; // num of matched character in pattern
        for(int i=1;i<len;i++){
            // if not matched, we need to go back last matched index till the very
            // first: index 0
            while (matched > 0 && str[matched] != str[i]) {
                matched = next[matched-1];
            }
            // if matched, move forward
            if(str[matched]==str[i]) matched++;
            next[i] = matched; // the longest index reached
        }
        return next;
    }

    public static int kmpIndexOf(String big, String small) {
        int bLen = big.length();
        int sLen = small.length();
        if(small.equals("")) return 0;
        if(big.equals("")) return -1;
        int[] nextStart = getNextStart(small.toCharArray());

        int matched = 0;
        for(int i=0;i<bLen;i++){
            while(matched>0 && small.charAt(matched)!=big.charAt(i)){
                matched = nextStart[matched-1];
            }
            if(small.charAt(matched)==big.charAt(i)) matched++;
            if(matched==sLen) return i-matched+1;
        }
        return -1;
    }
    public static int naiveIndexOf(String big, String small){
        int bigIndex = 0;
        int end = big.length()-small.length();
        while(bigIndex<=end){
            int i = bigIndex;
            int j = 0;
            while(j<small.length() && big.charAt(i)==small.charAt(j)){
                i++;j++;
            }
            if(j==small.length()) return bigIndex;
            bigIndex++;
        }
        return -1;
    }
}
