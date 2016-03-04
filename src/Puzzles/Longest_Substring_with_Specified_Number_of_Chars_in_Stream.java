package Puzzles;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ytliu on 3/4/16.
 */
public class Longest_Substring_with_Specified_Number_of_Chars_in_Stream {
    public static void main(String[] args) {
        System.out.println("L's google phone interview question");
        System.out.println(longestSubstring("aabbbccc", 2));
        System.out.println(longestSubstring("abbbaccccc", 2));
    }
    public static String longestSubstring(String stream, int n){
        String res = "";
        StringBuilder queue = new StringBuilder();
        Map<Character, Integer> track = new HashMap<Character, Integer>();
        for(char c : stream.toCharArray()){
            // track occurrence of characters
            if(track.containsKey(c)){
                track.put(c,track.get(c)+1);
            }else{
                track.put(c,1);
            }
            // check conditions
            if(track.keySet().size()<=n){
                queue.append(c);
            }else{
                while(queue.length()!=0){
                    char head = queue.charAt(0);
                    queue.deleteCharAt(0);
                    int num = track.get(head);
                    if(num==1){
                        track.remove(head);
                        break;
                    }else{
                        track.put(head,track.get(head)-1);
                    }
                }
                queue.append(c);
            }
            // check longest candidate
//            System.out.println(queue.toString());
            if(queue.length()>res.length()){
                res = queue.toString();
            }
        }
        return res;
    }
}
