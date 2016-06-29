package LeetCode.lc_051_100;

import LeetCode.util.Printer;

import java.util.*;

/**
 * Created by Tin on 6/25/16.
 */
public class lc089_Gray_Code {
    /*
    num flipping_bit_to_next

    0000 1
    0001 2
    0011 1
    0010 3
    0110 1
    0111 2
    0101 1
    0100 4

    1100 1
    1101 2
    1111 1
    1110 3
    1010 1
    1011 2
    1001 1
    1000

    0 1 -> 1
    1 1 -> 0
    use XOR
     */

    public static void main(String[] args) {
        Printer.printList(grayCode1(2));
        Printer.printList(grayCode2(2));
        Printer.printList(grayCode(2));
    }
    /*
    Discovered that second half is all elements of first half with significant big set
     */
    public static List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        if(n==0){
            res.add(0);
            return res;
        }
        List<Integer> firstHalf = grayCode(n-1);
        res.addAll(firstHalf);
        int mask = 1<<(n-1);
        for(int i=firstHalf.size()-1;i>=0;i--){
            res.add(mask|firstHalf.get(i));
        }
        return res;
    }
    /*
    Found pattern of flipping index and implemented with Stack
     */
    public static List<Integer> grayCode2(int n) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        int op = 0;
        res.add(op);
        int top = 1<<n;
        for(int i=1;i<top;i++){
            int j = 0;
            while(j<n){
                if(stack.isEmpty()||stack.peek()>j) break;
                j++;
                stack.pop();
            }
            stack.push(j);
            int mask = 1<<j;
            op ^= mask;
            res.add(op);
        }
        return res;
    }
    /*
    Try to flip every bit and see if the result is in the set
     */
    public static List<Integer> grayCode1(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        Set<Integer> shown = new HashSet<>();
        shown.add(0);
        int op = 0;
        int top = 1<<n;
        for(int i=1;i<top;i++){
            int mask = 1;
            int add = -1;
            while(mask<=top){
                add = op^mask;
                if(!shown.contains(add)) break;
                mask <<= 1;
            }
            res.add(add);
            shown.add(add);
            op = add;
        }
        return res;
    }

}
