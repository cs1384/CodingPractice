package LeetCode.lc_051_100;

import LeetCode.util.Printer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tin on 6/17/16.
 */
public class lc068_Text_Justification {
    public static void main(String[] args) {
        Printer.printList(fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));
        Printer.printList(fullJustify(new String[]{"Listen","to","many,","speak","to","a","few."}, 6));
    }
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int count = 0, width = 0;
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        while(i<words.length){
            if(width+words[i].length() > maxWidth){
                int nSpace = maxWidth - (width-count);
                int inBetween = count==1?nSpace:nSpace/(count-1);
                int left = count==1?0:nSpace%(count-1);
                sb.append(words[j++]);
                while(j<i){
                    for(int k=0;k<inBetween;k++) sb.append(' ');
                    if(left-->0) sb.append(' ');
                    sb.append(words[j++]);
                }
                while(sb.length()!=maxWidth) sb.append(' ');
                res.add(sb.toString());
                sb.setLength(0);sb.trimToSize();
                width = 0;
                count = 0;
            }
            width += words[i].length() + 1; // 1 for minimum space
            count++;
            i++;
        }
        sb.append(words[j++]);
        while(j<i){
            sb.append(' ');
            sb.append(words[j++]);
        }
        while(sb.length()!=maxWidth) sb.append(' ');
        res.add(sb.toString());
        return res;
    }

}
