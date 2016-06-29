package LeetCode.lc_051_100;

/**
 * Created by Tin on 6/24/16.
 */
public class lc087_Scramble_String {
    public static void main(String[] args) {
//        System.out.println(isScramble("a", "b"));
        System.out.println(isScramble("abc", "cab"));
    }
    public static boolean isScramble(String s1, String s2) {
        if(s1.equals(s2)) return true;
        if(s1.length()!=s2.length()) return false;

        int[] map = new int[256];
        for(int i=0;i<s1.length();i++){
            map[s1.charAt(i)]++;
            map[s2.charAt(i)]--;
        }
        for(int i=0;i<map.length;i++) if(map[i]!=0) return false;

        for(int i=1;i<s1.length();i++){
            if(isScramble(s1.substring(0,i), s2.substring(0,i)) &&
                    isScramble(s1.substring(i), s2.substring(i))) return true;
            if(isScramble(s1.substring(0,i), s2.substring(s2.length()-i)) &&
                    isScramble(s1.substring(i), s2.substring(0,s2.length()-i))) return true;
        }
        return false;
    }
}
