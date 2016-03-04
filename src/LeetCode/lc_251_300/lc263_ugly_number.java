package LeetCode.lc_251_300;

/**
 * Created by ytliu on 3/3/16.
 */
public class lc263_ugly_number {
    public static void main(String[] args) {

        System.out.println(isUgly1(6)); //t
        System.out.println(isUgly1(8)); //t
        System.out.println(isUgly1(14)); //f

        System.out.println(isUgly(6)); //t
        System.out.println(isUgly(8)); //t
        System.out.println(isUgly(14)); //f

    }
    // discussion
    public static boolean isUgly(int num){
        for (int i=2; i<6 && num>0; i++) while (num % i == 0) num /= i;
        return num == 1;
    }
    public static boolean isUgly1(int num) {

        if(num<=0) return false;
        num = divideBy(num, 2);
        num = divideBy(num, 3);
        num = divideBy(num, 5);
        return num==1;
    }
    private static int divideBy(int dividend, int divisor){

        while(dividend!=1){
            if(dividend%divisor!=0) break;
            dividend /= divisor;
        }
        return dividend;
    }

}
