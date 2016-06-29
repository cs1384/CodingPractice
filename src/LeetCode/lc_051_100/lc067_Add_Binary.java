package LeetCode.lc_051_100;

/**
 * Created by Tin on 6/17/16.
 */
public class lc067_Add_Binary {
    public static void main(String[] args) {
        System.out.println(addBinary("11","1"));
        System.out.println(addBinary("1111","1111"));
    }
    public static String addBinary(String a, String b) {
        if(a.length()<b.length()) return addBinary(b,a);
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int aIndex = a.length()-1, bIndex = b.length()-1;
        while(aIndex>=0 || bIndex>=0){
            int aNum = aIndex>=0?a.charAt(aIndex)-'0':0;
            int bNum = bIndex>=0?b.charAt(bIndex)-'0':0;
            int num = aNum + bNum + carry;
            sb.insert(0,num%2);
            carry = num>=2?1:0;
            aIndex--;bIndex--;
        }
        if(carry==1) sb.insert(0,carry);
        return sb.toString();
    }
}
