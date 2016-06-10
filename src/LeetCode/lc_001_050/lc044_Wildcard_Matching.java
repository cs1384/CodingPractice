package LeetCode.lc_001_050;

/**
 * Created by ytliu on 6/6/16.
 */
public class lc044_Wildcard_Matching {
    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a"));
        System.out.println(isMatch("aa", "aa"));
        System.out.println(isMatch("aaa", "aa"));
        System.out.println(isMatch("aa", "*"));
        System.out.println(isMatch("aa", "a*"));
        System.out.println(isMatch("ab", "?*"));
        System.out.println(isMatch("aab", "c*a*b"));

        System.out.println(isMatch("a", "")); //f
        System.out.println(isMatch("abefcdgiescdfimde", "ab*cd?i*de")); //t
    }
    public static boolean isMatch(String str, String pattern) {
        int s = 0, p = 0, matched = 0, starIdx = -1;
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                starIdx = p;
                matched = s - 1; // what matched before * will not changed
                p++;
            // not matched and no ? or *
            }else {
                // we have * before, advance string pointer and restart matching
                if (starIdx != -1) {
                    p = starIdx + 1;
                    matched++; // use * to match one more
                    s = matched + 1;
                // no previous * to use
                } else {
                    return false;
                }
            }
        }
        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*') p++;
        return p == pattern.length();
    }
}
