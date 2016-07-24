package LeetCode.lc_201_250;

import LeetCode.util.Printer;

import java.util.*;

/**
 * Created by Tin on 7/18/16.
 */
public class lc212_Word_Search_II {
    public static void main(String[] args) {
        char[][] board = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain"};
        Printer.printList(findWords(board, words));

    }
    /*
    Change TrieNode structure to allow better efficiency
     */
    public static List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        if(m==0) return Collections.EMPTY_LIST;
        int n = board[0].length;
        TrieNodeWithString root = new TrieNodeWithString();
        for(String w : words){
            TrieNodeWithString op = root;
            for(char c : w.toCharArray()){
                int index = c-'a';
                if(op.childNodes[index]==null) op.childNodes[index] = new TrieNodeWithString();
                op = op.childNodes[index];
            }
            op.word = w;
        }
        List<String> res = new ArrayList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                findWordsDFS(board, i, j, root, res);
            }
        }
        return res;
    }
    private static void findWordsDFS(char[][] board, int x, int y, TrieNodeWithString root,
                                     List<String> res){
        int m = board.length, n = board[0].length;
        if(x<0 || x>=m || y<0 || y>=n || board[x][y]=='#') return;
        char c = board[x][y];
        TrieNodeWithString next = root.childNodes[c-'a'];
        if(next==null) return;
        if(next.word!=null){
            res.add(next.word);
            next.word = null; // avoid duplicate
        }
        board[x][y] = '#'; // mark as used
        findWordsDFS(board, x+1, y, next, res);
        findWordsDFS(board, x-1, y, next, res);
        findWordsDFS(board, x, y+1, next, res);
        findWordsDFS(board, x, y-1, next, res);
        board[x][y] = c;
    }
    static class TrieNodeWithString {
        String word = null;
        TrieNodeWithString[] childNodes = new TrieNodeWithString[26];
    }
    /*
    Use Trie to check if we should keep DFSing
     */
    public static List<String> findWords1(char[][] board, String[] words) {
        int m = board.length;
        if(m==0) return Collections.EMPTY_LIST;
        int n = board[0].length;
        Trie trie = new Trie();
        for(String w : words) trie.insert(w);
        StringBuilder sb = new StringBuilder();
        boolean[][] used = new boolean[m][n];
        Set<String> res = new HashSet<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                findWordsDFS1(board, i, j, trie, used, sb, res);
            }
        }
        return new ArrayList<>(res);
    }
    private static void findWordsDFS1(char[][] board, int x, int y, Trie dict, boolean[][] used,
                              StringBuilder sb, Set<String> res){
        int m = board.length, n = board[0].length;
        if(x<0 || x>=m || y<0 || y>=n || used[x][y]) return;
        sb.append(board[x][y]);
        used[x][y] = true;
        if(dict.startsWith(sb.toString())){
            if(dict.search(sb.toString())) res.add(sb.toString());
            findWordsDFS1(board, x+1, y, dict, used, sb, res);
            findWordsDFS1(board, x-1, y, dict, used, sb, res);
            findWordsDFS1(board, x, y+1, dict, used, sb, res);
            findWordsDFS1(board, x, y-1, dict, used, sb, res);
        }
        used[x][y] = false;
        sb.deleteCharAt(sb.length()-1);
    }
    static class TrieNode {
        // Initialize your data structure here.
        boolean isWord = false;
        TrieNode[] childNodes = new TrieNode[26];
    }
    public static class Trie {
        private TrieNode root;
        public Trie() { root = new TrieNode(); }
        public void insert(String word) { findNode(word, true).isWord = true; }
        public boolean search(String word) {
            TrieNode trieNode = findNode(word, false);
            return trieNode!=null && trieNode.isWord;
        }
        public boolean startsWith(String prefix) { return findNode(prefix, false)!=null; }
        private TrieNode findNode(String prefix, boolean force){
            int i = 0;
            TrieNode op = root;
            while(i<prefix.length()) {
                char c = prefix.charAt(i++);
                if (op.childNodes[c-'a']==null){
                    if (force) op.childNodes[c-'a'] = new TrieNode();
                    else return null;
                }
                op = op.childNodes[c-'a'];
            }
            return op;
        }
    }

}
