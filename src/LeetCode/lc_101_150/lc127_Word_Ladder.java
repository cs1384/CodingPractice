package LeetCode.lc_101_150;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by Tin on 7/11/16.
 */
public class lc127_Word_Ladder {
    public static void main(String[] args) {
        Set<String> wordList = new HashSet<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        System.out.println(ladderLength("hit", "cog", wordList));
    }
    public static int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Set<String> fromBegin = new HashSet<>();fromBegin.add(beginWord);
        Set<String> fromEnd = new HashSet<>();fromEnd.add(endWord);
        int nStep = 1;
        while(!fromBegin.isEmpty() && !fromEnd.isEmpty()){
            if(fromBegin.size()>fromEnd.size()){
                Set<String> temp = fromBegin;
                fromBegin = fromEnd;
                fromEnd = temp;
            }
            Set<String> generated = new HashSet<>();
            for(String string : fromBegin){
                char[] wordArray = string.toCharArray();
                for(int i=0;i<wordArray.length;i++){
                    char ori = wordArray[i];
                    for(int c='a';c<='z';c++){
                        if(c==ori) continue;
                        wordArray[i] = (char)c;
                        String word = new String(wordArray);
                        if(fromEnd.contains(word)) return nStep+1;
                        if(wordList.contains(word)){
                            generated.add(word);
                            wordList.remove(word);
                        }
                    }
                    wordArray[i] = ori;
                }
            }
            fromBegin = generated;
            nStep++;
        }
        return 0;
    }
    /*
    Another way
     */
    public static int ladderLength3(String beginWord, String endWord, Set<String> wordList) {
        wordList.add(endWord);
        Set<String> generated = new HashSet<>();
        generated.add(beginWord);
        int nStep = 1;
        while(!generated.contains(endWord)){
            Set<String> newGenerated = new HashSet<>();
            for(String string : generated){
                char[] wordArray = string.toCharArray();
                for(int i=0;i<wordArray.length;i++){
                    char ori = wordArray[i];
                    for(int c='a';c<='z';c++){
                        if(c==ori) continue;
                        wordArray[i] = (char)c;
                        String word = new String(wordArray);
                        if(wordList.contains(word)){
                            newGenerated.add(word);
                            wordList.remove(word);
                        }
                    }
                    wordArray[i] = ori;
                }
            }
            if(newGenerated.isEmpty()) return 0;
            generated = newGenerated;
            nStep++;
        }
        return nStep;
    }
    /*
    Reproducing a string is more efficient
     */
    public static int ladderLength2(String beginWord, String endWord, Set<String> wordList) {
        int nStep = 1;
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);queue.add(null);
        while(!queue.isEmpty()){
            String word = queue.poll();
            if(word==null){
                nStep++;
                if(!queue.isEmpty()) queue.add(null);
                continue;
            }
            char[] wordArray = word.toCharArray();
            for(int i=0;i<wordArray.length;i++){
                char ori = wordArray[i];
                for(int c='a';c<='z';c++){
                    if(c==ori) continue;
                    wordArray[i] = (char)c;
                    String str = new String(wordArray);
                    if(str.equals(endWord)) return nStep+1;
                    if(wordList.contains(str)){
                        queue.offer(str);
                        wordList.remove(str);
                    }
                }
                wordArray[i] = ori;
            }
        }
        return 0;
    }
    public static int ladderLength1(String beginWord, String endWord, Set<String> wordList) {
        int nStep = 1;
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);queue.add(null);
        while(!queue.isEmpty()){
            String word = queue.poll();
            if(word==null){
                nStep++;
                if(!queue.isEmpty()) queue.add(null);
                continue;
            }
            StringBuilder sb = new StringBuilder(word);
            for(int i=0;i<sb.length();i++){
                char ori = word.charAt(i);
                for(int c='a';c<='z';c++){
                    if(c==ori) continue;
                    sb.setCharAt(i, (char) c);
                    if(sb.toString().equals(endWord)) return nStep+1;
                    if(wordList.contains(sb.toString())){
                        wordList.remove(sb.toString());
                        queue.offer(sb.toString());
                    }
                }
                sb.setCharAt(i,ori);
            }
        }
        return 0;
    }


}
