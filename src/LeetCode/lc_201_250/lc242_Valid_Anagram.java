package LeetCode.lc_201_250;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class lc242_Valid_Anagram {
    public static void main(String[] args) {
        lc242_Valid_Anagram validAnagram = new lc242_Valid_Anagram();

        System.out.println(validAnagram.isAnagram1("nagaram", "anagram"));
        System.out.println(validAnagram.isAnagram1("rat", "car"));

        System.out.println(validAnagram.isAnagram("nagaram", "anagram"));
        System.out.println(validAnagram.isAnagram("rat", "car"));
    }

    // for follow up question
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
        }
        for (char c : t.toCharArray()) {
            if (map.containsKey(c)) {
                int count = map.get(c);
                if (count == 1) {
                    map.remove(c);
                } else {
                    map.put(c, count-1);
                }
            } else {
                return false;
            }
        }
        return map.size() == 0;
    }

    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] map = new int[26];
        for (int i = 0 ; i < s.length() ; i++) {
            map[s.charAt(i)-'a']++;
            map[t.charAt(i)-'a']--;
        }
        return Arrays.stream(map).allMatch(count -> count == 0);
    }
}
