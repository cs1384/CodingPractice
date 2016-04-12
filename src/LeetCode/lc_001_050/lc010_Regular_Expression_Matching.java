package LeetCode.lc_001_050;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by ytliu on 4/10/16.
 */
public class lc010_Regular_Expression_Matching {
    public static void main(String[] args) {
        // approach1: backtracking, does not work
//        System.out.println(isMatch1("ab", ".*"));
//        System.out.println(isMatch1("aab", "c*a*b"));
//        System.out.println(isMatch1("aab", "c*a*"));
//        System.out.println(isMatch1("bcccaadddb", "bc*c.*b"));
//        System.out.println(isMatch1("bcccccccccb", "bc*c.*b"));
//        System.out.println(isMatch1("bccccccdcccb", "bc*dc.*b"));
//        System.out.println(isMatch1("aaa", "a.a"));
        // approach2: recursion, time limit excess on leetcode
//        System.out.println(isMatch2("ab", ".*")); //t
//        System.out.println(isMatch2("aab", "c*a*b")); //t
//        System.out.println(isMatch2("aab", "c*a*")); //f
//        System.out.println(isMatch2("bcccaadddb", "bc*c.*b")); //t
//        System.out.println(isMatch2("bcccccccccb", "bc*c.*b")); //t
//        System.out.println(isMatch2("bccccccdcccb", "bc*dc.*b")); //t
//        System.out.println(isMatch2("aaa", "a.a")); //t
        // approach2: recursion
    }

    /*
     * An example:
 *      0   c   *   a   *   b
 * 0    1   0   1   0   1   0
 * a    0   0   0   1   1   0
 * a    0   0   0   0   1   0
 * b    0   0   0   0   0   1
     */
    public boolean isMatch4(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;
        for(int j=1;j<=pLen;j++){
            // the char preceding * does not matter it can be either matched or not under * mode
            if(p.charAt(j-1)=='*') dp[0][j] = dp[0][j-2];
        }
        //note that the column 0 are all set to default value false;
        for(int i=1;i<=sLen;i++){
            for(int j=1;j<=pLen;j++){
                char sChar = s.charAt(i-1);
                char pChar = p.charAt(j-1);
                //matched, then we just check if previous strings match
                if(sChar == pChar || pChar == '.'){
                    dp[i][j] = dp[i - 1][j - 1];
                }else if(pChar == '*'){
                    //if previous character of p does not match, then we put if last real char matched
                    if(sChar != p.charAt(j - 2) && p.charAt(j - 2) != '.'){
                        dp[i][j] = dp[i][j - 2];
                    //if matched, we can either put if last real char matched or if string without current char matched
                    }else{
                        dp[i][j] =  dp[i][j - 2] || dp[i - 1][j];
                    }
                }
            }
        }
        return dp[sLen][pLen];
    }

    // elaborated recursion
    public static boolean isMatch3(String s, String p) {
        // trivial case
        if(s==null) return p==null;
        if(p==null) return s==null;
        int sLen = s.length();
        int pLen = p.length();
        //if(sLen==0) return pLen==0; not the case when: s="", p="b*"
        if(pLen==0) return sLen==0; //base case
        //when working character is not followed by *
        if(pLen==1 || p.charAt(1)!='*'){
            //when p has length>0 and s doesn't, we should return false
            if(sLen!=0 && (s.charAt(0)==p.charAt(0) || p.charAt(0)=='.')){
                return isMatch3(s.substring(1),p.substring(1));
            }else{
                return false;
            }
        //working character is followed by *
        }else{
            int i = -1; //start with -1 to fit the value requried by substring()
            /**
             * s.substring(start), start should be:
             * 0 -> assume ?* is not uutilized
             * 1... until not matching anymore anymore or tail
             */
            while(i<sLen && (p.charAt(0)=='.' || i<0 || p.charAt(0)==s.charAt(i))){
                if(isMatch3(s.substring(i+1),p.substring(2))) return true;
                i++;
            }
            return false;
        }
    }

    // intuitive recursion: TLE
    public static boolean isMatch2(String string, String pattern) {
        return isMatchDFS(string, 0, pattern, 0);
    }
    private static boolean isMatchDFS(String string, int sIndex, String pattern, int pIndex){
        // if both greater than the length, then we've matched everything -> return true
        if(sIndex>=string.length() && pIndex>=pattern.length()) return true;
        // still something to match, return false
        if(sIndex>=string.length() || pIndex>=pattern.length()) return false;
        char pChar = pattern.charAt(pIndex);
        if(pChar=='.'){
            // with *
            if(pIndex+1<pattern.length() && pattern.charAt(pIndex+1)=='*'){
                return isMatchDFS(string, sIndex, pattern, pIndex+2) || // don't use this skipper
                        isMatchDFS(string, sIndex+1, pattern, pIndex+2) || // use this skipper once
                        isMatchDFS(string, sIndex+1, pattern, pIndex); // keep using this skipper
            }else {
                // either use or not use the .
                return isMatchDFS(string, sIndex+1, pattern, pIndex+1) || isMatchDFS(string, sIndex, pattern, pIndex+1);
            }
        // regular character
        }else{
            char sChar = string.charAt(sIndex);
            // with *
            if(pIndex+1<pattern.length() && pattern.charAt(pIndex+1)=='*'){
                if(sChar==pChar){
                    return isMatchDFS(string, sIndex, pattern, pIndex+2) || // dont use this skipper
                            isMatchDFS(string, sIndex+1, pattern, pIndex+2) || // use this skipper once
                            isMatchDFS(string, sIndex+1, pattern, pIndex); // keep using this skipper
                }else{
                    return isMatchDFS(string, sIndex, pattern, pIndex+2); //have no change to use skipper
                }
            }else{
                if(sChar==pChar){
                    return isMatchDFS(string, sIndex+1, pattern, pIndex+1); // move forward
                }else{
                    return false; // failed
                }
            }
        }
    }

    // approach1: backtracking, does not work and too complicated
    static class Skipper{
        boolean isOne;
        boolean isWildcard;
        char match;
        int sIndex = -1;
        Skipper(boolean isOne, boolean isWildcard){
            this.isOne = isOne;
            this.isWildcard = isWildcard;
        }
        Skipper(boolean isOne, boolean isWildcard, char match){
            this.isOne = isOne;
            this.isWildcard = isWildcard;
            this.match = match;
        }
    }
    public static boolean isMatch1(String string, String pattern) {
        Map<Integer, Queue<Skipper>> indexToSkippers = new HashMap<>();
        Stack<Integer> backPoint = new Stack<>();
        int pIndex = pattern.length()-1;
        int sIndex = string.length()-1;
        Queue<Skipper> skippers = null;
        while(pIndex>=0 || sIndex>=0){
            char pChar;
            // set pChar as different, to try to match more in string
            if(pIndex<0) pChar = string.charAt(sIndex)=='a'?'b':'a';
            else pChar = pattern.charAt(pIndex);
            // add skipper
            if(pChar=='.'){
                if(skippers==null) skippers = new LinkedList<>();
                skippers.offer(new Skipper(true, true));
                pIndex -= 1;
            // add skipper
            }else if(pChar=='*'){
                if(skippers==null) skippers = new LinkedList<>();
                char match = pattern.charAt(pIndex-1);
                if(match=='.'){
                    skippers.offer(new Skipper(false, true));
                }else{
                    skippers.offer(new Skipper(false, false, match));
                }
                pIndex -= 2;
            }else{
                // attach skipper to this backoint
                if(skippers!=null){
                    indexToSkippers.put(pIndex, skippers);
                    skippers = null;
                    backPoint.push(pIndex);
                }
                // because we still have something to match, so failed
                if(sIndex<0) return false;
                char sChar = string.charAt(sIndex);
                if(sChar!=pChar){
                    if(backPoint.isEmpty()) return false;
                    while(!backPoint.isEmpty()) {
                        int index = backPoint.peek();
                        Queue<Skipper> skipperQueue = indexToSkippers.get(index);
                        while (!skipperQueue.isEmpty()) {
                            Skipper skipper = skipperQueue.peek();
                            if (skipper.isWildcard) {
                                if(skipper.sIndex==-1) skipper.sIndex = sIndex;
                                else {
                                    skipper.sIndex--;
                                    sIndex = skipper.sIndex-1;
                                }
                                if (skipper.isOne) skipperQueue.poll();
                                break;
                            } else if(skipper.sIndex!=-1){
                                if(string.charAt(skipper.sIndex-1)==skipper.match){
                                    skipper.sIndex--;
                                    sIndex = skipper.sIndex-1;
                                    break;
                                }else{
                                    skipperQueue.poll();
                                }
                            } else if (skipper.match != sChar) {
                                skipperQueue.poll();
                            } else if (skipper.match==sChar) {
                                skipper.sIndex = sIndex;
                                sIndex = skipper.sIndex-1;
                                break;
                            }
                        }
                        if(skipperQueue.isEmpty()){
                            indexToSkippers.remove(index);
                            backPoint.pop();
                        }else{
                            break;
                        }
                    }
                    if(backPoint.isEmpty()) return false;
                    pIndex = backPoint.peek();
                }else{
                    pIndex -= 1;
                    sIndex -= 1;
                }
            }
        }
        return true;
    }
}
