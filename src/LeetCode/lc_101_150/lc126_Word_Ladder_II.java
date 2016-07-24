package LeetCode.lc_101_150;

import LeetCode.util.Printer;

import java.util.*;

/**
 * Created by Tin on 7/16/16.
 */
public class lc126_Word_Ladder_II {
    public static void main(String[] args) {
        Set<String> wordList = new HashSet<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        Printer.printListList(findLadders("hit", "cog", wordList));

        Set<String> wordList2 = new HashSet<>();
        wordList2.add("hot");
        wordList2.add("dog");
        wordList2.add("dot");
        Printer.printListList(findLadders("hot", "dot", wordList2));

        Set<String> wordList3 = new HashSet<>();
        wordList3.add("hot");
        wordList3.add("dog");
        Printer.printListList(findLadders("hot", "dog", wordList3));

        Set<String> wordList4 = new HashSet<>();
        wordList4.add("hot");
        wordList4.add("cog");
        wordList4.add("dog");
        wordList4.add("tot");
        wordList4.add("hog");
        wordList4.add("hop");
        wordList4.add("pot");
        wordList4.add("dot");
        Printer.printListList(findLadders("hot", "dog", wordList4));

        Set<String> wordList5 = new HashSet<>();
        wordList5.add("hot");
        wordList5.add("cog");
        wordList5.add("dot");
        wordList5.add("dog");
        wordList5.add("hit");
        wordList5.add("lot");
        wordList5.add("log");
        Printer.printListList(findLadders("hit", "cog", wordList5));
    }
    public static List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        wordList.remove(beginWord);
        wordList.remove(endWord);
        Set<String> fromBegin = new HashSet<>();fromBegin.add(beginWord);
        Set<String> fromEnd = new HashSet<>();fromEnd.add(endWord);
        Set<String> reached = new HashSet<>();reached.add(beginWord);
        Map<String, Set<String>> fromStrings = new HashMap<>();
        fromStrings.put(beginWord, new HashSet<String>());
        boolean connected = false;
        while(!reached.isEmpty()){
            reached.clear();
            if(fromEnd.size()<fromBegin.size()){
                for(String child : fromEnd){
                    char[] wordArray = child.toCharArray();
                    for(int i=0;i<wordArray.length;i++){
                        char ori = wordArray[i];
                        for(int c='a';c<='z';c++){
                            if(c==ori) continue;
                            wordArray[i] = (char)c;
                            String parent = new String(wordArray);
                            if(!wordList.contains(parent) && !fromBegin.contains(parent)) continue;
                            if(fromBegin.contains(parent)) connected = true;
                            if(!fromStrings.containsKey(child)) fromStrings.put(child, new HashSet<String>());
                            fromStrings.get(child).add(parent);
                            reached.add(parent);
                        }
                        wordArray[i] = ori;
                    }
                }
                fromEnd.addAll(reached);
            }else{
                for(String parent : fromBegin){
                    char[] wordArray = parent.toCharArray();
                    for(int i=0;i<wordArray.length;i++){
                        char ori = wordArray[i];
                        for(int c='a';c<='z';c++){
                            if(c==ori) continue;
                            wordArray[i] = (char)c;
                            String child = new String(wordArray);
                            if(!wordList.contains(child) && !fromEnd.contains(child)) continue;
                            if(fromEnd.contains(child)) connected = true;
                            if(!fromStrings.containsKey(child)) fromStrings.put(child, new HashSet<String>());
                            fromStrings.get(child).add(parent);
                            reached.add(child);
                        }
                        wordArray[i] = ori;
                    }
                }
                fromBegin.addAll(reached);
            }
            wordList.removeAll(reached);
            if(connected) break;
        }
        List<List<String>> res = new ArrayList<>();
        if(!connected) return res;
        List<String> op = new ArrayList<>();
        op.add(endWord);
        findLaddersDFS(op, fromStrings, res);
        return res;
    }
    public static List<List<String>> findLadders1(String beginWord, String endWord, Set<String> wordList) {
        Map<String, Set<String>> fromStrings = new HashMap<>();
        fromStrings.put(beginWord, new HashSet<String>());

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        queue.offer(null);

        // make wordList as in the form given by the example
        wordList.add(endWord);
        wordList.remove(beginWord);

        Set<String> reached = new HashSet<>();
        while(!queue.isEmpty()){
            String string = queue.poll();
            if(string==null){
                wordList.removeAll(reached);
                if(!wordList.contains(endWord)) break;
                queue.addAll(reached);
                reached.clear();
                if(!queue.isEmpty()) queue.offer(null);
                continue;
            }
            char[] wordArray = string.toCharArray();
            for(int i=0;i<wordArray.length;i++){
                char ori = wordArray[i];
                for(int c='a';c<='z';c++){
                    if(c==ori) continue;
                    wordArray[i] = (char)c;
                    String child = new String(wordArray);
                    if(!wordList.contains(child)) continue;
                    if(!fromStrings.containsKey(child)) fromStrings.put(child, new HashSet<String>());
                    fromStrings.get(child).add(string);
                    reached.add(child);
                }
                wordArray[i] = ori;
            }
        }
        List<List<String>> res = new ArrayList<>();
        if(fromStrings.get(endWord)==null) return res;
        List<String> op = new ArrayList<>();
        op.add(endWord);
//        asList() gives static List
//        findLaddersDFS(Arrays.asList(endWord), fromStrings, res);
        findLaddersDFS(op, fromStrings, res);
        return res;
    }
    private static void findLaddersDFS(List<String> op, Map<String, Set<String>> childToParent,
                                List<List<String>> res){
        String child = op.get(0);
        Set<String> parents = childToParent.get(child);
        if(parents==null) return;
        if(parents.size()==0){
            res.add(new ArrayList<>(op));
            return;
        }
        for(String parent : parents){
            op.add(0,parent);
            findLaddersDFS(op, childToParent, res);
            op.remove(0);
        }
    }
}
