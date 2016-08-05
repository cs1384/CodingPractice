package LeetCode.lc_151_200;

/**
 * Created by Tin on 8/4/16.
 */
public class lc190_Reverse_Bits {
    public static void main(String[] args) {
        int n = 43261596;
        System.out.println(Integer.toString(n,2));
        System.out.println(Integer.toBinaryString(reverseBits(n)));
    }
    // you need treat n as an unsigned value
    public static int reverseBits(int n) {
        int res = 0;
        for(int i=0;i<32;i++){
            int mask = 1<<i;
            int op = 1<<(31-i);
            if((n&mask)!=0) res |= op;
        }
        return res;
    }
}
