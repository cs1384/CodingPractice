package LeetCode.lc_151_200;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tin on 8/1/16.
 */
public class lc166_Fraction_to_Recurring_Decimal {
    public static void main(String[] args) {
        System.out.println(fractionToDecimal(1,2));
        System.out.println(fractionToDecimal(2,1));
        System.out.println(fractionToDecimal(2,3));
        System.out.println(fractionToDecimal(4,9));
        System.out.println(fractionToDecimal(4,333));
        System.out.println(fractionToDecimal(1,99)); //0.(01)
        System.out.println(fractionToDecimal(-50,8)); //-6.25
        System.out.println(fractionToDecimal(0,-5)); //0
        System.out.println(fractionToDecimal(-1,-2147483648));

    }
    public static String fractionToDecimal(int numerator, int denominator) {
        // prevent overflow
        long num = numerator, denom = denominator;
        // well divisible
        long unit = num/denom;
        if(num%denom==0) return String.valueOf(unit);
        // deal with sign
        StringBuilder sb = new StringBuilder();
        if(unit==0 && (num<0 ^ denom<0)) sb.append('-');
        sb.append(unit);
        sb.append('.');
        // rule out the impact of signed numbers -> all to negative
        num = num%denom;
        if(num>0) num = -num;
        if(denom>0) denom = -denom;
        Map<Long, Integer> numToIndex = new HashMap<>();
        while(num!=0){
            num *= 10;
            if(numToIndex.containsKey(num)){
                int offset = numToIndex.get(num);
                sb.insert(offset, '(');
                sb.append(')');
                return sb.toString();
            }
            long multiplier = num/denom;
            numToIndex.put(num, sb.length());
            sb.append(multiplier);
            num %= denom;
        }
        return sb.toString();
    }
}
