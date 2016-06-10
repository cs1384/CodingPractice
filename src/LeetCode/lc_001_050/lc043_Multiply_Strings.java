package LeetCode.lc_001_050;

/**
 * Created by ytliu on 6/9/16.
 */
public class lc043_Multiply_Strings {
    public static void main(String[] args) {
        System.out.println(add("1111", "52223"));
        System.out.println(add("1", "9"));
        System.out.println(multiply("25", "25"));
        System.out.println(multiply("25", "20"));
        System.out.println(multiply("9133", "0"));
        System.out.println(multiply("123456789", "987654321"));
    }

    /**
     *  https://leetcode.com/discuss/71593/easiest-java-solution-with-graph-explanation
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int[] product = new int[len1+len2];
        for(int i = len1 - 1; i >= 0; i--) {
            for(int j = len2 - 1; j >= 0; j--) {
                int mul = (num1.charAt(i)-'0') * (num2.charAt(j)-'0');
                int tenth = i+j, digit = i+j+1;
                int sum = mul + product[digit]; // ex. 45 + 6 = 51
                product[tenth] += sum / 10;
                product[digit] = (sum) % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        int op = 0;
        while(op<product.length && product[op]==0) op++;
        for(int i=op;i<product.length;i++) sb.append(product[i]);
        return sb.length()==0?"0":sb.toString();
    }
    /**
     * naive solution: general division -> too many sb.reverse, slow
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply1(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        if(len2>len1) return multiply(num2, num1);
        String ans = "0";
        for(int j=len2-1;j>=0;j--){
            char c2 = num2.charAt(j);
            if(c2=='0') continue;
            int carry = 0;
            StringBuilder sb = new StringBuilder();
            for(int i=len1-1;i>=0;i--){
                char c1 = num1.charAt(i);
                int m = (c1-'0')*(c2-'0')+carry;
                if(m>=10){
                    carry = m/10;
                    sb.append(m%10);
                }else{
                    carry = 0;
                    sb.append(m);
                }
            }
            if(carry!=0) sb.append(carry);
            for(int k=0;k<len2-1-j;k++) sb.insert(0, '0');
            ans = add(ans, sb.reverse().toString());
        }
        return ans;
    }
    private static String add(String num1, String num2){
        if(num2.length()>num1.length()) return add(num2, num1);
        StringBuilder sb = new StringBuilder();
        int i = num1.length()-1;
        int j = num2.length()-1;
        int carry = 0;
        while(i>=0 || j>=0){
            char c1 = num1.charAt(i);
            char c2 = j>=0?num2.charAt(j):'0';
            int sum = (c1-'0') + (c2-'0') + carry;
            if(sum>=10){
                carry = 1;
                sb.append((sum-10));
            }else{
                carry = 0;
                sb.append(sum);
            }
            i--;j--;
        }
        if(carry==1) sb.append(1);
        return sb.reverse().toString();
    }
}
