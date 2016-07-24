package DataStructureImplement;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Tin on 7/18/16.
 */
public class Trie {
    public class TrieNode{
        boolean isWord = false;
        Map<Character, TrieNode> childNodes = new HashMap<>();
    }
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        findNode(word, true).isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode trieNode = findNode(word, false);
        return trieNode!=null && trieNode.isWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return findNode(prefix, false)!=null;
    }

    private TrieNode findNode(String prefix, boolean force){
        int i = 0;
        TrieNode op = root;
        while(i<prefix.length()) {
            char c = prefix.charAt(i++);
            if (!op.childNodes.containsKey(c)){
                if (force) op.childNodes.put(c, new TrieNode());
                else return null;
            }
            op = op.childNodes.get(c);
        }
        return op;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.search("a"));
        System.out.println(trie.startsWith("a"));
    }

}
