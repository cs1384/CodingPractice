package LeetCode.lc_001_050;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 4/27/16.
 */
public class lc030_Substring_with_Concatenation_of_All_Words {
    public static void main(String[] args) {
//        Printer.printList(findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
//        Printer.printList(findSubstring("barfoofoobarthefoobarman", new String[]{"foo", "bar", "the"}));
//        Printer.printList(findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","good"}));
        Printer.printList(findSubstring("abaababbaba", new String[]{"ba","ab","ab"}));
    }
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new LinkedList<>();
        Map<String, Integer> needs = new HashMap<>();
        for(String word : words){
            if(needs.containsKey(word)) needs.put(word, needs.get(word)+1);
            else needs.put(word, 1);
        }
        int wordLen = words[0].length();
        int wordsCount = words.length;
        int requiredLen = wordLen * wordsCount;
        for(int i=0;i<wordLen;i++){
            int startIndex = i;
            int matched = 0;
            int opIndex = i;
            Map<String, Integer> tracker = new HashMap<>(needs);
            Queue<String> window = new LinkedList<>();
            while(startIndex<=s.length()-requiredLen && opIndex<=s.length()-wordLen){
                String sub = s.substring(opIndex, opIndex+wordLen);
                if(tracker.containsKey(sub)){
                    // extra match
                    if(tracker.get(sub)==0){
                        // poll the window till we found previous same match
                        while(!window.peek().equals(sub)){
                            String string = window.poll();
                            tracker.put(string, tracker.get(string)+1); // update tracker
                            matched--; // update matched count
                            startIndex += wordLen; // update startIndex
                        }
                        window.poll(); // poll the previous same match
                        startIndex += wordLen; // update startIndex
                        window.offer(sub);
                    }else{
                        window.offer(sub);
                        tracker.put(sub, tracker.get(sub)-1);
                        matched++;
                    }
                    if(matched==wordsCount){
                        res.add(startIndex);
                        String string = window.poll();
                        tracker.put(string, 1);
                        matched--;
                        startIndex += wordLen;
                    }
                }else{
                    window.clear();
                    tracker = new HashMap<>(needs);
                    startIndex = opIndex+wordLen;
                    matched = 0;
                }
                opIndex += wordLen;
            }
        }
        return res;
    }
}
