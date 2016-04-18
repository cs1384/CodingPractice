package LeetCode.lc_001_050;

/**
 * Created by ytliu on 4/13/16.
 */
public class lc012_Integer_to_Roman {
    public static void main(String[] args) {
        System.out.println(intToRoman(4)); //IV
        System.out.println(intToRoman(6)); //VI
        System.out.println(intToRoman(3999)); //MMMCMXCIX
        System.out.println(intToRoman(2499)); //MMCDXCIX
        System.out.println(intToRoman(1900)); //MCM
    }
    public static String intToRoman(int num) {
        char[] chars = new char[]{'I','V','X','L','C','D','M'};
        int startIndex = 0;
        StringBuilder res = new StringBuilder();
        while(num>0){
            int op = num%10;
            switch (op){
                case 1:case 2:case 3:
                    while(op>0){
                        res.insert(0, chars[startIndex]);
                        op--;
                    }
                    break;
                case 4:
                    res.insert(0, chars[startIndex+1]);
                    res.insert(0, chars[startIndex]);
                    break;
                case 5:case 6:case 7:case 8:
                    while(op>5){
                        res.insert(0, chars[startIndex]);
                        op--;
                    }
                    res.insert(0, chars[startIndex+1]);
                    break;
                case 9:
                    res.insert(0, chars[startIndex+2]);
                    res.insert(0, chars[startIndex]);
                    break;
            }
            num /= 10;
            startIndex += 2;
        }
        return res.toString();
    }
}
