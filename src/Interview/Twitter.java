package Interview;

import LeetCode.util.Printer;
import Library.object.GraphNode;
import Library.object.Node;

import java.util.*;

/**
 * Created by Tin on 7/9/16.
 */
public class Twitter {
    public static void main(String[] args) {
        System.out.println("1. print all acyclic path in a graph");
        System.out.println("http://www.1point3acres.com/bbs/thread-187666-1-1.html");
        Map<Node, List<Node>> map = new HashMap<>();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        List<Node> n1_neighbors = new ArrayList<>();
        n1_neighbors.add(n2);n1_neighbors.add(n3);n1_neighbors.add(n4);
        map.put(n1,n1_neighbors);
        map.put(n2,Arrays.asList(n3));
        List<Node> n3_neighbors = new ArrayList<>();
        n3_neighbors.add(n1);n3_neighbors.add(n2);
        map.put(n3,n3_neighbors);
        printAcyclicPaths(map, n1);

        System.out.println("2. character frequencies");
        System.out.println("http://www.1point3acres.com/bbs/thread-181263-1-1.html");
        System.out.println(charFrequency("banana"));

        System.out.println("3. number of friend circle");
        System.out.println("https://www.hackerrank.com/contests/juniper-hackathon/challenges/friend-circles");
        System.out.println("https://www.hackerearth.com/notes/disjoint-set-union-union-find/");
        System.out.println(friendCircles(new String[]{
                "YYNN",
                "YYYN",
                "NYYN",
                "NNNY"
        }));
        System.out.println(friendCircles(new String[]{
                "YNNNN",
                "NYNNN",
                "NNYNN",
                "NNNYN",
                "NNNNY"
        }));

        System.out.println("4. longest chain");
        System.out.println("http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=131907&page=1#pid1871241");
        Set<String> dictionary = new HashSet<>();
        dictionary.add("a");
        dictionary.add("abcd");
        dictionary.add("bcd");
        dictionary.add("abd");
        dictionary.add("cd");
        dictionary.add("c");
        System.out.println(longestChain(dictionary));

        System.out.println("5. Digits occurrence");
        System.out.println("http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=194888&extra=page%3D1%26filter%3Dauthor%26orderby%3Ddateline%26sortid%3D311%26sortid%3D311%26orderby%3Ddateline");
        Printer.printArray(digitOccurrence1(4));
        Printer.printArray(digitOccurrence(4));
        Printer.printArray(digitOccurrence1(55));
        Printer.printArray(digitOccurrence(55));
        Printer.printArray(digitOccurrence1(255));
        Printer.printArray(digitOccurrence(255));

        System.out.println("6. Closest numbers");
        System.out.println("https://www.hackerrank.com/challenges/closest-numbers");

        System.out.println("7. Type of Triangle");
        System.out.println("https://www.hackerrank.com/challenges/what-type-of-triangle");

        System.out.println("8. Game of throne");
        System.out.println("https://www.hackerrank.com/challenges/game-of-thrones");
        System.out.println("https://www.hackerrank.com/challenges/game-of-throne-ii");

        System.out.println("9. most frequnet number in a sorted array");
        System.out.println("http://www.1point3acres.com/bbs/thread-193601-1-1.html");
        System.out.println(mostFrequentNumberInSortedArray(new int[]{1,2,3,3,3,4,6,7,7,7,7}));

        System.out.println("10. draw beans");
        System.out.println("http://www.1point3acres.com/bbs/thread-118660-1-1.html");
        System.out.println(probabilityForLastWhiteBean(2,2));

        System.out.println("11. url shortener");
        System.out.println("http://www.geeksforgeeks.org/how-to-design-a-tiny-url-or-url-shortener/");
        UrlShortener urlShortener = new UrlShortener();
//        System.out.println(urlShortener.encodeUrlID(125));
//        System.out.println(urlShortener.decodeShortenUrl("cb"));
        System.out.println(urlShortener.decodeShortenUrl(urlShortener.encodeUrlID(125)));
    }
    public static class UrlShortener{
        char[] map;
        public UrlShortener(){
            map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        }
        public String encodeUrlID(long urlid){
            long mapSize = map.length; //62
            StringBuilder sb = new StringBuilder();
            while(urlid>0){
                sb.append(map[(int)(urlid%mapSize)]);
                urlid /= mapSize;
            }
            return sb.reverse().toString();
        }
        public long decodeShortenUrl(String url){
            long id = 0;
            for(char c : url.toCharArray()){
                id *= 62;
                if('a'<=c && c<='z') id += c-'a';
                else if('A'<=c && c<='Z') id += c-'A'+26;
                else if('0'<=c && c<='9') id += c-'0'+52;
                else return -1;
            }
            return id;
        }
    }
    public static double probabilityForLastWhiteBean(int white, int red){
        double[][] dp = new double[red+1][white+1];
        dp[0][0] = 1.0;
        for(int i=0;i<=red;i++){
            for(int j=0;j<=white;j++){
                int wLeft = white-j;
                int rLeft = red-i;
                double pRed = (double)rLeft/(rLeft+wLeft);
                pRed *= pRed;
                if(j<white) dp[i][j+1] += dp[i][j]*(1-pRed);
                if(i<red) dp[i+1][j] += dp[i][j]*pRed;
             }
        }
        return dp[red][white-1];
    }

    public static int mostFrequentNumberInSortedArray(int[] arr){
        int len = arr.length;
        int res = arr[0];
        int pre = arr[0];
        int count = 1, most = 1;
        for(int i=1;i<len;i++){
            if(arr[i]==pre){
                count++;
            }else{
                if(count>most){
                    res = pre;
                    most = count;
                }
                count = 1;
            }
            pre = arr[i];
        }
        return count>res?pre:res;
    }


    public static int[] digitOccurrence(int n){
        int[] res = new int[10];
        int num = 1;
        while(num<=n){
            int op = num*10;
            int add = n/op;
            if(add!=0) {
                res[0] += add;
                for (int i = 1; i < res.length; i++) res[i] += add * num;
            }
            int extra = n%op;
            if(extra!=0) {
                for (int i = 1; i < extra/num; i++) res[i] += num;
                res[extra/num] += (extra%num)+1;
            }
            num *= 10;
        }
        return res;
    }
    /*
    Wrong Answer
     */
    public static int[] digitOccurrence1(int n){
        int[] res = new int[10];
        int occurrence = 0;
        int op = 10;
        while(op<=n){
            occurrence += n/op;
            op *= 10;
        }
        res[0] = occurrence;
        for(int i=1;i<res.length;i++){
            occurrence = 0;
            int base = i;
            op = 10;
            int num = 1;
            while(base<=n){
                int left = n-base;
                if(left<num){
                    occurrence += left+1;
                }else {
                    occurrence += (left/op+1) * num; // giving wrong answer
                }
                base *= 10;
                num *= 10;
                op *= 10;
            }
            res[i] = occurrence;
        }
        return res;
    }

    public static int longestChain(Set<String> dictionary){
        Map<String, Integer> wordToLongestChain = new TreeMap<String, Integer>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length()!=o2.length()) return o1.length()-o2.length();
                return o1.compareTo(o2);

            }
        });
        for(String string : dictionary) wordToLongestChain.put(string, 0);
        int longest = 0;
        for(String string : wordToLongestChain.keySet()){
            wordToLongestChain.put(string, longestChainHelper(0, string, wordToLongestChain));
            longest = Math.max(longest, wordToLongestChain.get(string));
        }
        return longest;
    }
    private static int longestChainHelper(int level, String word, Map<String, Integer> wordToLongestChain){
        StringBuilder sb = new StringBuilder(word);
        for(int i=0;i<word.length();i++){
            char ori = sb.charAt(i);
            sb.deleteCharAt(i);
            String newWord = sb.toString();
            if(wordToLongestChain.containsKey(newWord))
                level = Math.max(level, wordToLongestChain.get(newWord));
            sb.insert(i,ori);
        }
        return level+1;
    }

    /*
    See DisjointSet_UnionFind.java for another approach
     */
    public static int friendCircles(String[] matrix) {
        int m = matrix.length;
        Set<Integer> unvisited = new HashSet<>();
        for(int i=0;i<m;i++) unvisited.add(i);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int nCircle = 0;
        while(!queue.isEmpty()){
            int studnetNo = queue.poll();
            for(int j=0;j<matrix[studnetNo].length();j++){
                if(matrix[studnetNo].charAt(j)=='Y' && unvisited.contains(j))
                    queue.add(j);
            }
            unvisited.remove(studnetNo);
            if(queue.isEmpty()){
                nCircle++;
                if(!unvisited.isEmpty()) queue.offer(unvisited.iterator().next());
            }
        }
        return nCircle;
    }

    static class CharFrequency{
        char c;
        int fre;
        CharFrequency(char c){
            this.c = c;
            fre = 0;
        }
        public String toString(){
            return ""+c+fre;
        }
    }
    public static String charFrequency(String string){
        CharFrequency[] charFrequencies = new CharFrequency[256];
        for(char c : string.toCharArray()){
            if(charFrequencies[c]==null) charFrequencies[c] = new CharFrequency(c);
            charFrequencies[c].fre++;
        }
        Arrays.sort(charFrequencies, new Comparator<CharFrequency>() {
            @Override
            public int compare(CharFrequency o1, CharFrequency o2) {
                if(o1==null && o2==null) return 0;
                if(o1==null) return -1;
                if(o2==null) return 1;
                return o2.fre-o1.fre;
            }
        });
        StringBuilder sb = new StringBuilder();
        for(CharFrequency charFrequency : charFrequencies){
            if(charFrequency!=null) sb.append(charFrequency.toString());
        }
        return sb.toString();
    }
    public static void printAcyclicPaths(Map<Node, List<Node>> map, Node start){
        List<Node> path = new ArrayList<>();
        Set<Node> visited = new HashSet<>();
        printAcyclicPathsDFS(map, start, visited, path);
    }
    private static void printAcyclicPathsDFS(Map<Node, List<Node>> map, Node node,
                                      Set<Node> visited, List<Node> path){
        if(visited.contains(node)) return;
        List<Node> neighbors = map.get(node);
        path.add(node);
        visited.add(node);
        System.out.print("[");
        printPath(path);
        if(neighbors!=null)
            for (Node n : neighbors)
                printAcyclicPathsDFS(map, n, visited, path);
        visited.remove(node);
        path.remove(path.size()-1);
    }
    private static void printPath(List<Node> list){
        System.out.print("[");
        for(Node n : list){
            System.out.print(n.value + ", ");
        }
        System.out.println("]");
    }
}
