package LeetCode.lc_051_100;

import LeetCode.util.Printer;

import java.util.*;

/**
 * Created by Tin on 6/21/16.
 */
public class lc077_Combinations {
    /*
    Four ways:
    1. recursive backtracking
    2. iterative
    3. math
    4. DP
    5. bit manipulation
     */
    public static void main(String[] args) {
//        System.out.println("--- approach 1");
//        Printer.printListList(combine1(5,2));
//        Printer.printListList(combine1(5,3));
//        System.out.println("--- approach 2");
//        Printer.printListList(combine2(5,2));
//        Printer.printListList(combine2(5,3));
//        System.out.println("--- approach 3");
//        Printer.printListList(combine3(4, 2));
//        Printer.printListList(combine3(5, 2));
//        Printer.printListList(combine3(5, 3));
        System.out.println("--- approach 5");
        Printer.printListList(combine5(4, 2));
//        Printer.printListList(combine5(5, 2));
//        Printer.printListList(combine5(5, 3));
    }

    public static List<List<Integer>> combine1(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        combine1DFS(new ArrayList<Integer>(), 1, n, k, res);
        return res;
    }
    private static void combine1DFS(List<Integer> op, int start, int n, int k, List<List<Integer>> res){
        if(op.size()==k){
            res.add(new ArrayList<Integer>(op));
            return;
        }
        for(int i=start;i<=n;i++){
            op.add(i);
            combine1DFS(op, i+1, n, k, res);
            op.remove(op.size()-1);
        }
    }

    public static List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        for(int i=1;i<=n;i++) res.add(Arrays.asList(i));
        for(int i=2;i<=k;i++){
            List<List<Integer>> newRes = new ArrayList<>();
            for(int j=i;j<=n;j++){
                for(List<Integer> list : res){
                    if(list.get(i-2)<j){
                        List<Integer> toAdd = new ArrayList<>(list);
                        toAdd.add(j);
                        newRes.add(toAdd);
                    }
                }
            }
            res = newRes;
        }
        return res;
    }

    /*
    Actually, the combine(n, k) has two parts, one part is all combinations without n,
    it's combine(n-1, k), another is all combinations with n, which can be gotten by
    appending n to every element in combine(n-1, k-1). Note, the combine(i, i) is
    what we can get directly.
    https://leetcode.com/discuss/32955/a-short-recursive-java-solution-based-on-c-n-k-c-n-1-k-1-c-n-1-k
    https://leetcode.com/discuss/12888/dp-for-the-problem
     */
    public static List<List<Integer>> combine3(int n, int k) {
        if(k==0){
            List<Integer> row = new LinkedList<>();
            return new LinkedList<>(Arrays.asList(row));
        }
        if(k==n){
            List<Integer> row = new LinkedList<>();
            for (int i=1;i<=k;i++) row.add(i);
            return new LinkedList<>(Arrays.asList(row));
        }
        List<List<Integer>> result = combine3(n - 1, k - 1);
        result.forEach(e -> e.add(n));
        result.addAll(combine3(n - 1, k));
        return result;
    }

    public List<List<Integer>> combine4(int n, int k) {
        // just the iterative version of combine3
        // https://leetcode.com/discuss/12888/dp-for-the-problem
        return Collections.EMPTY_LIST;
    }
    /*
    http://blog.csdn.net/w57w57w57/article/details/6657547
     */
    public static List<List<Integer>> combine5(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if(k==0){
            res.add(new ArrayList<Integer>());
            return res;
        }
        int op = (1<<k)-1;
        // (1<<n)-(1<<(n-k) : 1000 - 0010 = 0110, the largest op can be
        while(op<= ((1<<n)-(1<<(n-k)))){
            int comb = op;
            int mask = 1;
            System.out.println(Integer.toString(comb, 2));
            List<Integer> list = new ArrayList<>();
            for(int i=1;i<=n;i++){
                if((comb&mask)!=0) list.add(i);
                mask <<= 1;
            }
            if(list.size()!=0)
                res.add(list);
            op = nextIntegerWithSameNumOfBinary1s(op);
        }
        return res;
    }
    /*
    78: 1001110
    83: 1010011
    how? move the leftmost 1 to one position left and put the rest 1s to the
    right side.
     */
    private static int nextIntegerWithSameNumOfBinary1s(int num){
        int x = num&(-num); // to find rightmost 1
        int left = num+x; // move leftmost 1
        int right = ((num^left)/x)>>2;
        return left | right;
    }


}
