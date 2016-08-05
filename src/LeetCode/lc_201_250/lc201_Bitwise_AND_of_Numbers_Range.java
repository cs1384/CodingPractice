package LeetCode.lc_201_250;

/**
 * Created by Tin on 8/5/16.
 */
public class lc201_Bitwise_AND_of_Numbers_Range {
    public static void main(String[] args) {
        System.out.println(rangeBitwiseAnd(5,7));
    }
    /*
    From discuss:
    We need to find leftmost commons, so we keep shifting two number right util
    they are the same and shift steps  back
     */
    public int rangeBitwiseAnd2(int m, int n) {
        int moveSteps = 0;
        while(m!=n){
            m >>= 1;
            n >>= 1;
            moveSteps++;
        }
        return m<<=moveSteps;
    }
    /**
     * It is like permutation.
     * 2: 0010
     * 3: 0011
     * 4: 0100
     * 5: 0101
     * 6: 0110
     * 7: 0111
     * The pattern is that only the left most consecutively common 1s of m and n could be the answer.
     */
    public static int rangeBitwiseAnd(int m, int n) {
        int res = 0;
        int mask = 1<<30;
        while(mask!=0){
            if((m&mask)!=0 && (n&mask)!=0) res |= mask;
            else if((m&mask)!=0 ^ (n&mask)!=0) return res;
            mask >>= 1;
        }
        return res;
    }
}
