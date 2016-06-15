package LeetCode.lc_051_100;

/**
 * Created by Tin on 6/15/16.
 */
public class lc058_Length_of_Last_Word {
    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("Hello World"));
        System.out.println(lengthOfLastWord("a "));
        System.out.println(lengthOfLastWord("b    a    "));
    }
    public static int lengthOfLastWord(String s) {
        int res = 0;
        // start from behind to find last word
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)!=' '){
                res++;
            }else if(res!=0) return res;
        }
        return res;
    }

}
