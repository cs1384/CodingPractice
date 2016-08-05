package LeetCode.lc_151_200;

import LeetCode.util.Printer;

import java.util.*;

/**
 * Created by Tin on 8/2/16.
 */
public class lc187_Repeated_DNA_Sequences {
    public static void main(String[] args) {
        Printer.printList(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        Printer.printList(findRepeatedDnaSequences("AAAAAAAAAAA"));
    }
    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        int len = s.length();
        if(len<11) return res;
        Set<Integer> once = new HashSet<>();
        Set<Integer> twice = new HashSet<>();
        int[] mask = new int[26];
        mask['C'-'A'] = 1;
        mask['G'-'A'] = 2;
        mask['T'-'A'] = 3;
        int cut = Integer.MAX_VALUE>>11;
        int queue = 0;
        for(int i=0;i<9;i++){
            queue <<= 2;
            queue |= mask[s.charAt(i)-'A'];
        }
        for(int i=9;i<len;i++){
            queue <<= 2;
            queue &= cut;
            queue |= mask[s.charAt(i)-'A'];
            if(!once.add(queue) && twice.add(queue)) res.add(s.substring(i-9,i+1));
        }
        return res;
    }
    public static List<String> findRepeatedDnaSequences2(String s) {
        int len = s.length();
        if(len<11) return Collections.EMPTY_LIST;
        Set<String> once = new HashSet<>();
        Set<String> added = new HashSet<>();
        int i = 0, j = 10;
        for(;j<=len;i++,j++){
            String string = s.substring(i,j);
            if(added.contains(string)) continue;
            if(once.contains(string)) added.add(string);
            else once.add(string);
        }
        return new ArrayList<>(added);
    }
    public static List<String> findRepeatedDnaSequences1(String s) {
        int len = s.length();
        List<String> res = new ArrayList<>();
        if(len<10) return res;
        Map<String, Integer> stringToIndex = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(0,10));
        stringToIndex.put(sb.toString(), 0);
        for(int i=10;i<len;i++){
            sb.deleteCharAt(0);
            sb.append(s.charAt(i));
            String string = sb.toString();
            if(stringToIndex.containsKey(string)){
                if(stringToIndex.get(string)==len) continue;
                stringToIndex.put(string, len);
            }else{
                stringToIndex.put(string, i);
            }
        }
        for(String string : stringToIndex.keySet()){
            if(stringToIndex.get(string)==len) res.add(string);
        }
        return res;
    }
}
