package LeetCode.lc_001_050;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ytliu on 4/15/16.
 */
public class lc013_Roman_to_Integer {
    public static void main(String[] args) {
        System.out.println(romanToInt("IV")); //4
        System.out.println(romanToInt("VI")); //6
        System.out.println(romanToInt("MMMCMXCIX")); //3999
        System.out.println(romanToInt("MMCDXCIX")); //2499
        System.out.println(romanToInt("MCM")); //1900
    }
//         1   5   10  50  100 500 1000
    // {'I','V','X','L','C','D','M'};
    public static int romanToInt(String roman) {
        Map<Character, Integer> romanToInt = new HashMap<>();
        romanToInt.put('M', 1000);
        romanToInt.put('D', 500);
        romanToInt.put('C', 100);
        romanToInt.put('L', 50);
        romanToInt.put('X', 10);
        romanToInt.put('V', 5);
        romanToInt.put('I', 1);
        int res = 0;
        for(int i=0;i<roman.length();i++){
            int value = romanToInt.get(roman.charAt(i));
            if(i+1<roman.length() && romanToInt.get(roman.charAt(i+1))>value){
                res -= value;
            }else{
                res += value;
            }

        }
        return res;
    }
}
