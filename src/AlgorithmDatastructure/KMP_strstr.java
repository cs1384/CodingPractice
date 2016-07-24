package AlgorithmDatastructure;

/**
 * Created by Tin on 11/22/15.
 *
 * The naive solution is not efficient in such situation: ("aaaaaaaaaaab","aaaab").
 * It will match aaaa and then fail again and again until the rear.
 *
 * The KMP's idea is to get the information from the matching process and then
 * decide can we shift forward maybe more then one position. For example,
 *
 * "xxxxababaaxxxxx"
 *      "ababac"
 *
 * we stop at c, and base on the the character sequence we've mathced, "ababa",
 * we could move the matching position 2 step forward as following:
 *
 * "xxxxababaaxxxxx"
 *       "ababac"
 *
 * How do we decide the number of forward steps? Suppose we've matched Text[X~(X+N)]
 * and Pattern[0~N], our goal is to find the logest prefix of P[0~N] witch matches one
 * suffix of T[X~(X+N)].
 *
 * prefix: a substring excluding the last character.
 * suffix: a substring excluding the first character.
 *
 * So this particular substring is "aba" in the example. Note that by shift, we mean
 * stay at the same position of the text and back to a certain point of pattern
 * (from index of 5 to index of 3 in our case)
 *
 * In fact, we can pre-decide the shit number of each index of pattern since so called
 * Text[X~(X+N)] is just part of P itself! So as you can see from the code, the
 * implementation is similar in both methods.
 *
 * Time Complexity:
 * Matched can only be incremented once in an iteration, total increase could
 * only be len(pattern)-1 at top
 * Matched is always less than i because they start out as 0 and 1 and can only
 * be incremented by 1 at one iteration in getNextStart(). Therefore, next[i]=matched
 * implies that matched=next[matched-1] is a decrement operation for sure (in
 * both methods)
 * Since matched can only be incremented at a time, while loop can only operate
 * len(pattern)-1 times during the entire for loop. And that is O(len).
 *
 * The for loop od getNextStart() is O(len) as well, so the TC of this method is O(len)
 * kmpIndexOf() has O(bLen) for loop, since sLen is less then bLen, the TC is O(bLen)
 * If you see a comparison as a basic TC unit, then TC of kmpIndexOf() could be
 * O(bLen+sLen)
 *
 */
public class KMP_strstr {

    private static int[] getNextStart(char[] str) {
        int len = str.length;
        int[] next = new int[len];
        next[0] = 0; // not matched anything, so shit to index 0
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

    public static void main(String[] args){
        System.out.println("== Implementation of strstr(), indexOf() in java JDK ==");
        System.out.println(naiveIndexOf("TinIsAwesome", "IsA"));
        System.out.println(kmpIndexOf("TinIsAwesome", "IsA"));
        System.out.println(kmpIndexOf("TinIsAwesome", "ababaca"));
        System.out.println(kmpIndexOf("bbaa", "aab"));
    }
}
