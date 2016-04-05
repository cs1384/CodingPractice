package LeetCode.lc_001_050;

/**
 * Created by ytliu on 3/31/16.
 */
public class lc008_String_to_Integer_atoi {
    public static void main(String[] args) {
//        System.out.println(myAtoi("0"));
//        System.out.println(myAtoi("-0"));
//        System.out.println(myAtoi("+123"));
//        System.out.println(myAtoi("-12345"));
//        System.out.println(myAtoi("-eee092323"));
//        System.out.println(myAtoi("+9823923289382193"));
//        System.out.println(myAtoi("-1234edede"));
        System.out.println(myAtoi("2147483648"));
    }
    public static int myAtoi(String str) {
        // deal with whitespaces
        str = str.trim();
        if(str.length()==0) return 0;
        // deal with sign
        char first = str.charAt(0);
        boolean positive = true;
        int index = 0;
        if(first=='-'){
            positive = false;
            index++;
        }else if(first=='+'){
            index++;
        }else if(first-'9'>0 || '0'-first>0){
            return 0;
        }
        // build value
        int value = 0;
        while(index<str.length()){
            char c = str.charAt(index);
            if(c-'9'>0 || '0'-c>0) break;
            int num = c-'0';
            if(positive && (Integer.MAX_VALUE-num)/10<value) return Integer.MAX_VALUE;
            if(!positive && (Integer.MIN_VALUE+num)/10>value) return Integer.MIN_VALUE;
            value *= 10;
            if(positive) value += num;
            else value -= num;
            index++;
//            System.out.println(value);
        }
        return value;
    }
}
