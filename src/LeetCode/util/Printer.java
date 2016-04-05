package LeetCode.util;

/**
 * Created by ytliu on 3/4/16.
 */
public class Printer {
    public static void printMetrix(int[][] m){
        System.out.println("[");
        for(int i=0;i<m.length;i++){
            System.out.print(" [");
            for(int j=0;j<m[i].length;j++){
                System.out.print(m[i][j]+", ");
            }
            System.out.println("]");
        }
        System.out.println("]");
    }
    public static void printArray(int[] arr){
        System.out.print("[");
        for(int i : arr){
            System.out.print(i+", ");
        }
        System.out.println("]");
    }
    public static void printListNode(ListNode listNode){
        while(listNode!=null){
            System.out.print(listNode.val+" -> ");
            listNode = listNode.next;
        }
        System.out.println();
    }
}
