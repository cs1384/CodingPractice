package LeetCode.lc_151_200;

/**
 * Created by Tin on 8/1/16.
 */
public class lc168_Excel_Sheet_Column_Title {
    public static void main(String[] args) {
        System.out.println(convertToTitle(1)); //A
        System.out.println(convertToTitle(27)); //AA
        System.out.println(convertToTitle(53)); //BA
        System.out.println(convertToTitle(703)); //AAA
        System.out.println(convertToTitle(1000000001)); //CFDGSXM
    }
    public static String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        long num = n-1;
        long op = 1;
        while(num>=0){
            int i = (int)(num/op%26);
            sb.insert(0, (char) ('A' + i));
            num -= op*26;
            op *= 26;
        }
        return sb.toString();
    }
}
