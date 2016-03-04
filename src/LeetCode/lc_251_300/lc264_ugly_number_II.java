package LeetCode.lc_251_300;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by ytliu on 3/3/16.
 */
public class lc264_ugly_number_II {

    public static void main(String[] args){

        System.out.println(nthUglyNumber(1)); //1
        System.out.println(nthUglyNumber(4)); //4
        System.out.println(nthUglyNumber(10)); //12
        System.out.println(nthUglyNumber(20));
        System.out.println(nthUglyNumber(50));

    }
    public static int nthUglyNumber(int n) {

        Queue<Integer> queueFor2 = new LinkedList<>();
        Queue<Integer> queueFor3 = new LinkedList<>();
        Queue<Integer> queueFor5 = new LinkedList<>();

        queueFor2.offer(2);
        queueFor3.offer(3);
        queueFor5.offer(5);

        int count = 1;
        int res = 1;

        while(count<n){
            res = getSmallest(queueFor2, queueFor3, queueFor5);
            queueFor2.offer(res*2);
            queueFor3.offer(res*3);
            queueFor5.offer(res*5);
            count++;
        }

        return res;
    }
    private static int getSmallest(Queue<Integer> q1, Queue<Integer> q2, Queue<Integer> q3){
        int min = Math.min(q1.peek(), Math.min(q2.peek(), q3.peek()));
        if(q1.peek()==min) q1.poll();
        if(q2.peek()==min) q2.poll();
        if(q3.peek()==min) q3.poll();
        return min;
    }

}
