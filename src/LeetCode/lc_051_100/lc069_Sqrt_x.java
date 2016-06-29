package LeetCode.lc_051_100;

/**
 * Created by Tin on 6/17/16.
 */
public class lc069_Sqrt_x {
    public static void main(String[] args) {

    }
    public static int mySqrt(int x) {
        long low = 0;
        long high = x / 2 + 1; //sqrt will never be greater than half
        while(low <= high){
            long mid = (low+high)/2;
            if(mid*mid == x)
                return (int)mid;
            if(mid*mid < x)
                low = mid+1;
            else
                high = mid-1;
        }
        return (int)high;
    }
}
