package LeetCode.lc_051_100;

import LeetCode.util.Printer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tin on 6/28/16.
 */
public class lc093_Restore_IP_Addresses {
    public static void main(String[] args) {
//        Printer.printList(restoreIpAddresses("25525511135"));
//        Printer.printList(restoreIpAddresses("010010"));
        Printer.printList(restoreIpAddresses("0000"));
    }
    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        restoreIpAddressesDFS(s, 0, new StringBuilder(), 0, res);
        return res;
    }
    private static void restoreIpAddressesDFS(String s, int start, StringBuilder sb,
                                       int nPart, List<String> res){
        if(nPart==4){
            res.add(new String(sb.substring(0,sb.length()-1)));
            return;
        }
        nPart++;
        int len = s.length();
        StringBuilder op = new StringBuilder();
        for(int i=start;i<len&&i-start<3;i++){
            op.append(s.charAt(i));
            int ip = Integer.parseInt(op.toString());
            int restLen = len-(i+1), restParts = 4-nPart;
            if(ip<=255 && restLen<=restParts*3 && restLen>=restParts){
                int preLen = sb.length(), addLen = i-start+1+1;
                sb.append(ip);sb.append('.');
                if(preLen+addLen==sb.length()) restoreIpAddressesDFS(s, i+1, sb, nPart, res);
                sb.setLength(preLen);sb.trimToSize();
            }
        }
    }
}
