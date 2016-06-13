package LeetCode.lc_001_050;

import LeetCode.util.Printer;

import java.util.*;

/**
 * Created by Tin on 6/13/16.
 */
public class lc049_Group_Anagrams {
    public static void main(String[] args) {
        Printer.printListList(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(int i=0;i<strs.length;i++){
            char[] temp = strs[i].toCharArray();
            Arrays.sort(temp);
            String str = new String(temp);
            if(!map.containsKey(str)){
                map.put(str, new LinkedList<String>());
            }
            map.get(str).add(strs[i]);
        }
        List<List<String>> res = new LinkedList<>();
        for(List<String> l : map.values()){
            res.add(l);
        }
        return res;
    }
}
