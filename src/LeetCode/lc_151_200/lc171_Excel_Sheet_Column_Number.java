package LeetCode.lc_151_200;

/**
 * Created by Tin on 8/2/16.
 */
public class lc171_Excel_Sheet_Column_Number {
    public static void main(String[] args) {
        System.out.println("C");
        System.out.println("AA"); //53
        System.out.println("AAA"); //703
    }
    public int titleToNumber(String s) {
        int op = 1;
        int res = 0;
        for(int i=s.length()-1;i>=0;i--){
            res += (s.charAt(i)-'A'+1)*op;
            op *= 26;
        }
        return res;
    }
}
