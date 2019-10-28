package LeetCode.lc_251_300;

import java.util.Arrays;
import java.util.List;

public class lc273_Integer_to_English_Words {
    public static void main(String[] args) {
        lc273_Integer_to_English_Words lc273_integer_to_english_words = new lc273_Integer_to_English_Words();
//        System.out.println(lc273_integer_to_english_words.numberToWords(123));
//        System.out.println(lc273_integer_to_english_words.numberToWords(12345));
//        System.out.println(lc273_integer_to_english_words.numberToWords(1234567));
//        System.out.println(lc273_integer_to_english_words.numberToWords(1234567891));
//        System.out.println(lc273_integer_to_english_words.numberToWords(50868));
        System.out.println(lc273_integer_to_english_words.numberToWords(1000000));
    }

    private List<String> oneWords = Arrays.asList("", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
            "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen");
    private List<String> tens = Arrays.asList("Zero", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety");
    private List<String> literals = Arrays.asList("", "Thousand", "Million", "Billion");

    public String numberToWords(int num) {
        if (num == 0 ) return "Zero";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; num > 0; i++) {
            String s = threeDigitsToWords(num%1000);
            if (!s.equals("")) {
                sb.insert(0, String.join(" ", s, literals.get(i)));
                sb.insert(0, ' ');
            }
            num /= 1000;
        }
        return sb.toString().trim();
    }

    private String threeDigitsToWords(int num) {
        if (num < 100) {
            return twoDigitsToWords(num);
        } else {
            return String.join(" ", oneWords.get(num/100), "Hundred", twoDigitsToWords(num%100)).trim();
        }
    }

    private String twoDigitsToWords(int num) {
        if (num < 20) {
            return oneWords.get(num);
        } else {
            return String.join(" ", tens.get(num/10), oneWords.get(num%10)).trim();
        }
    }
}
