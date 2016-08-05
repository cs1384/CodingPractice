package LeetCode.lc_151_200;

/**
 * Created by Tin on 8/4/16.
 */
public class lc191_Number_of_1_Bits {
    public static void main(String[] args) {
        System.out.println(hammingWeight(11));
        System.out.println(Integer.toBinaryString(-8));
        System.out.println(Integer.toBinaryString(-8>>>1));
        System.out.println(Integer.toBinaryString(-8>>1));
    }
    /*
    https://discuss.leetcode.com/topic/11385/simple-java-solution-bit-shifting
    unsigned operation >>>
     */
    public static int hammingWeight(int n) {
        int count = 0;
        while(n!=0){
            count += n&1;
            n >>>= 1;
        }
        return count;
    }
    public static int hammingWeight1(int n) {
        int count = 0;
        for(int i=0;i<32;i++){
            int mask = 1<<i;
            if((n&mask)!=0) count++;
        }
        return count;
    }
}
