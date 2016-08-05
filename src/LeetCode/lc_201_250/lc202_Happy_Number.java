package LeetCode.lc_201_250;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tin on 8/4/16.
 */
public class lc202_Happy_Number {
    public static void main(String[] args){
        System.out.println(isHappy(19));
    }
    /*
    Like find the cycle in linked list, we can use two pointers to determine the
    termination
     */
    public static boolean isHappy(int n){
        int slow = n, fast = n;
        do{
            slow = newNumer(slow);
            fast = newNumer(newNumer(fast));
            if(slow==1 || fast==1) return true;
        }while(slow!=fast);
        return false;
    }
    private static int newNumer(int n){
        int num = 0;
        while(n>0){
            int remain = n%10;
            num += remain*remain;
            n /= 10;
        }
        return num;
    }
    public static boolean isHappy1(int n){
        Set<Integer> set = new HashSet<>();
        while(set.add(n)){
            int num = 0;
            while(n>0){
                int remain = n%10;
                num += remain*remain;
                n /= 10;
            }
            n = num;
        }
        return n==1;
    }
}
