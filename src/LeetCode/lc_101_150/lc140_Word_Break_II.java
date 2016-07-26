package LeetCode.lc_101_150;

import LeetCode.util.Printer;

import java.util.*;

/**
 * Created by Tin on 7/24/16.
 */
public class lc140_Word_Break_II {
    public static void main(String[] args) {
//        String string = "test";
//        System.out.println("\"" + string.substring(4) + "\"");
        Set<String> wordDict = new HashSet<>();
        wordDict.addAll(Arrays.asList("cat", "cats", "and", "sand", "dog"));
        Printer.printList(wordBreak("catsanddog", wordDict));
    }
    static Map<String, List<String>> cache = new HashMap<>();
    public static List<String> wordBreak(String s, Set<String> wordDict) {
        if(cache.containsKey(s)) return cache.get(s);
        List<String> res = new ArrayList<>();
        for(String word : wordDict){
            if(s.startsWith(word)){
                if(word.length()==s.length()) res.add(word);
                else{
                    List<String> list = wordBreak(s.substring(word.length()), wordDict);
                    for(String string : list){
                        res.add(word+" "+string);
                    }
                }
            }
        }
        cache.put(s, res);
        return res;
    }
    /*
    workable but slow because we go character by character and create two
    strings at each
     */
    Map<String, List<String>> cache3 = new HashMap<>();
    public List<String> wordBreak3(String s, Set<String> wordDict) {
        if(cache3.containsKey(s)) return cache3.get(s);
        List<String> res = new ArrayList<>();
        for(int i=1;i<=s.length();i++){
            String left = s.substring(0,i);
            String right = s.substring(i);
            if(wordDict.contains(left)){
                if(right.equals("")) res.add(left);
                else {
                    List<String> list = wordBreak(right, wordDict);
                    for (String string : list) {
                        res.add(left+" "+string);
                    }
                }
            }
        }
        cache3.put(s, res);
        return res;
    }
    /*
    Workable but slow because at every visit to a certain cut[], the DFS
    calculates the same sublsit again and again
     */
    public static List<String> wordBreak2(String s, Set<String> wordDict) {
        int len = s.length();
        List<Integer>[] cuts = new List[len+1];
        cuts[0] = new ArrayList<Integer>();
        for(int i=1;i<=len;i++){
            for(int j=0;j<i;j++){
                if(cuts[j]!=null && wordDict.contains(s.substring(j,i))){
                    if(cuts[i]==null) cuts[i] = new ArrayList<>();
                    cuts[i].add(j);
                }
            }
        }
        List<String> res = new ArrayList<>();
        if(cuts[len]==null) return res;
        StringBuilder sb = new StringBuilder();
        wordBreakDFS(s, cuts, len, sb, res);
        return res;
    }
    private static void wordBreakDFS(String s, List<Integer>[] cuts, int start,
                                     StringBuilder sb, List<String> res){
        if(start==0){
            res.add(sb.toString().substring(1));
            return;
        }
        for(int cut : cuts[start]){
            String sub = s.substring(cut, start);
            sb.insert(0," "+sub);
            wordBreakDFS(s, cuts, cut, sb, res);
            sb.delete(0, sub.length()+1);
        }
    }
    /*
    First thought which is based on lc139_Word_Break, TLE on
    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    ["a", "aa", "aaa", .....]
     */
    public static List<String> wordBreak1(String s, Set<String> wordDict) {
        int len = s.length();
        List<String>[] dp = new List[len+1];
        for(int i=0;i<=len;i++) dp[i] = new ArrayList<String>();
        dp[0] = Arrays.asList("");
        for(int i=1;i<=len;i++){
            for(int j=0;j<i;j++){
                if(dp[j].size()!=0 && wordDict.contains(s.substring(j,i))){
                    String newString = s.substring(j,i);
                    for(String string : dp[j]){
                        dp[i].add(string+newString+" ");
                    }
                }
            }
        }
        List<String> res = dp[len];
        for(int i=0;i<res.size();i++) res.set(i, res.get(i).trim());
        return res;
    }
}
