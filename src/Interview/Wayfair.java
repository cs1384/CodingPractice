package Interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Created by ytliu on 12/18/15.
 */
public class Wayfair {
    static class LLNode{
        int value;
        LLNode next;
        public LLNode(int val){
            this.value = val;
        }
        @Override
        public String toString(){
            LLNode op = this;
            StringBuilder res = new StringBuilder();
            while(op!=null){
                res.append(op.value+" -> ");
                op = op.next;
            }
            return res.toString();
        }
    }
    public static void printArray(int[] arr){
        System.out.print("[");
        for(int i : arr){
            System.out.print(i+", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args){

        SortedSet<String> sortedSet = new TreeSet<String>();
        sortedSet.add("Tin");
        sortedSet.add("Xin");

        sortedSet.remove("Xin");

//        System.out.println("1. Merge two sorted lists");
//        LLNode list1 = new LLNode(2);list1.next = new LLNode(6);
//        LLNode list2 = new LLNode(1);list2.next = new LLNode(5);
//        System.out.println(mergeTwoSortedList(list1, list2).toString());
//
//
//        System.out.println("2. Remove duplicate from sorted list I");
//        LLNode list = new LLNode(2);list.next = new LLNode(2);
//        System.out.println(removeDuplicateFromSortedList1(list).toString());
//
//        System.out.println("3. Remove duplicate from sorted list II");
//        LLNode dlist = new LLNode(2);dlist.next = new LLNode(2);dlist.next.next = new LLNode(4);
//        System.out.println(removeDuplicateFromSortedList12(dlist));
//
//        System.out.println("4. Split negative and positive");
//        int[] arr = {-6, 2, 1, 2, -10, -4, 8};
//        splitPositiveAndNegative(arr);
//        printArray(arr);
//
//        System.out.println("5. Split negative and positive (remain order)");
//        arr = new int[]{-6, 2, 1, 2, -10, -4, 8};
//        splitPositiveAndNegative2(arr);
//        printArray(arr);
//
//        System.out.println("6. Single number");
//        int[] arrn = {3,3,4,4,5,6,2,2,5};
//        System.out.println(singleNumber(arrn));
//        System.out.println(singleNumber2(arrn));
//
//        System.out.println("7. Return maximum subarray");
//        printArray(maxSubarray(arr));
//
//        System.out.println("8. rotate array");
//        int[] arrr = {1,2,3,4,5,6,7};
//        rotateArray(arrr, 3);
//        printArray(arrr);
//
//        System.out.println("9. valid parentheses");
//        System.out.println(validParehtheses("{}{}([]{})"));
//        System.out.println(validParehtheses("{}{}([]{)}"));
//
//        System.out.println("10. Strstr");
//        System.out.println(indexOf("helloworld", "world"));
//        System.out.println(kmpIndexOf("helloworld", "world"));
//
//        System.out.println("11. Print word combinations iterative DFS");
////        String[][] test1 = {{"quick", "lazy"}, {"brown", "black", "grey"}, {"fox", "dog"}};
//        String[][] test1 = {{"quick", "lazy"}, {"brown", "black", "grey"}};
//        wordCombinationIterativeDFS(test1);

    }

    public static void wordCombinationIterativeDFS(String[][] words){
        Stack<String> stack = new Stack<String>();
        stack.push(null);
        stack.addAll(Arrays.asList(words[0]));
        Stack<String> print = new Stack<String>();
        int layer = 0;
        int total = words.length-1;
        while(!stack.isEmpty()){
            String s = stack.pop();
            if(s==null){
                layer--;
                if(layer<0) return;
                print.pop();
            }else {
                if (layer == total) {
                    print.push(s);
                    printStack(print);
                    print.pop();
                } else {
                    print.push(s);
                    stack.push(null);
                    stack.addAll(Arrays.asList(words[++layer]));
                }
            }
        }
    }
    private static void printStack(Stack<String> stack){
        List<String> list = new LinkedList<String>(stack);
        for(String s : list){
            System.out.print(s+" ");
        }
        System.out.println();
    }

    public static int indexOf(String str, String sub){
        int top = str.length()-sub.length();
        if(top<0) return -1;
        for(int i=0;i<=top;i++){
            int j = 0;
            while(j<sub.length() && str.charAt(i+j)==sub.charAt(j)) j++;
            if(j==sub.length()) return i;
        }
        return -1;
    }
    public static boolean validParehtheses(String str){
        Stack<Character> open = new Stack<Character>();
        for(char c : str.toCharArray()){
            if(c=='(' || c=='[' || c=='{'){
                open.push(c);
            }else{
                if(open.isEmpty()) return false;
                char last = open.pop();
                switch (c){
                    case ')':
                        if(last!='(') return false;
                        break;
                    case ']':
                        if(last!='[') return false;
                    case '{':
                        if(last!='}') return false;
                }
            }
        }
        if(!open.isEmpty()) return false;
        return true;
    }


    public static void rotateArray(int[] arr, int shift){
        int len = arr.length;
        shift %= len;
        resverseArray(arr, 0, len-1);
        resverseArray(arr, 0, shift-1);
        resverseArray(arr, shift, len-1);
//        resverseArray(arr, 0, len-shift-1);
//        resverseArray(arr, 0, len-1);
    }
    private static void resverseArray(int[] arr, int from, int to){
        while(from<to){
            int temp = arr[from];
            arr[from] = arr[to];
            arr[to] = temp;
            from++;to--;
        }
    }

    private static int[] getNextStart(char[] str) {
        int len = str.length;
        int[] next = new int[len];
        next[0] = 0; // not matched anything, so shit to index 0
        int matched = 0; // num of matched character in pattern
        for(int i=1;i<len;i++){
            // if not matched, we need to go back last matched index till the very
            // first: index 0
            while (matched > 0 && str[matched] != str[i]) {
                matched = next[matched-1];
            }
            // if matched, move forward
            if(str[matched]==str[i]) matched++;
            next[i] = matched; // the longest index reached
        }
        return next;
    }

    public static int kmpIndexOf(String big, String small) {
        int bLen = big.length();
        int sLen = small.length();
        if(small.equals("")) return 0;
        if(big.equals("")) return -1;
        int[] nextStart = getNextStart(small.toCharArray());

        int matched = 0;
        for(int i=0;i<bLen;i++){
            while(matched>0 && small.charAt(matched)!=big.charAt(i)){
                matched = nextStart[matched-1];
            }
            if(small.charAt(matched)==big.charAt(i)) matched++;
            if(matched==sLen) return i-matched+1;
        }
        return -1;
    }

    public static int[] maxSubarray(int[] arr){
        int len = arr.length;
        int[] dp = new int[arr.length];
        int index = 0;
        int newsum = arr[index];
        int max = arr[index];
        for(int i=1;i<len;i++){
            newsum=Math.max(newsum+arr[i],arr[i]);
            if(newsum>max){
                max = newsum;
                index = i;
            }
        }
        int op = 0;
        for(int i=index;i>=0;i--){
            op += arr[i];
            if(op==max) return Arrays.copyOfRange(arr, i, index+1);
        }
        return new int[0];
    }

    public static int singleNumber2(int[] arr){
        int res = 0;
        for(int i : arr) res ^= i;
        return res;
    }

    public static int singleNumber(int[] arr){
        Set<Integer> set = new HashSet<Integer>();
        for(int i : arr){
            if(set.contains(i)){
                set.remove(i);
            }else{
                set.add(i);
            }
        }
        return set.iterator().next();
    }

    public static void splitPositiveAndNegative2(int[] arr){
        int len = arr.length;
        for(int i=0;i<len;i++){
            int index = i;
            while(index<len && arr[index]>=0){
                index++;
            }
            if(index==len) return;
            int val = arr[i];
            for(int j=i+1;j<=index;j++){
                int temp = arr[j];
                arr[j] = val;
                val = temp;
            }
            arr[i] = val;
        }
    }

    /**
     * @param arr
     */
    public static void splitPositiveAndNegative(int[] arr){
        int index = 0;
        int op = arr.length-1;
        while(op>=0 && index<op){
            if(arr[op]<0){
                int temp = arr[op];
                arr[op] = arr[index];
                arr[index] = temp;
                index++;
            }else{
                op--;
            }
        }
    }

    /**
     * Ask if you are allowed to change the structure of two original lists or not, the implememt here is for yes
     * @param list1
     * @param list2
     * @return
     */
    public static LLNode mergeTwoSortedList(LLNode list1, LLNode list2){
        LLNode dummy = new LLNode(-1);
        LLNode op = dummy;
        while(list1!=null && list2!=null){
            if(list1.value>list2.value){
                op.next = list2;
                list2 = list2.next;
            }else{
                op.next = list1;
                list1 = list1.next;
            }
            op = op.next;
        }
        if(list1!=null) op.next = list1;
        if(list2!=null) op.next = list2;
        return dummy.next;
    }

    public static LLNode removeDuplicateFromSortedList1(LLNode list){
        LLNode dummy = new LLNode(list.value==-1?0:-1);
        dummy.next = list;
        LLNode pre = dummy;
        while(list!=null){
            if(list.value==pre.value){
                pre.next = list.next;
            }else{
                pre = list;
            }
            list = list.next;
        }
        return dummy.next;

    }

    public static LLNode removeDuplicateFromSortedList12(LLNode list){
        LLNode dummy = new LLNode(list.value==-1?0:-1);
        LLNode op = dummy;
        while(list!=null){
            if(list.next==null || list.next.value!=list.value){
                op.next = list;
                list = list.next;
                op = op.next;
            }else{
                LLNode nxt = list.next;
                while(nxt!=null && nxt.value==list.value){
                    nxt = nxt.next;
                }
                list = nxt;
            }
        }
        return dummy.next;
    }

}
