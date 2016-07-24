package Library.util;

import LeetCode.util.ListNode;

import java.util.List;

/**
 * Created by ytliu on 3/4/16.
 */
public class Printer {
    public static void printMatrix(boolean[][] m){
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
    public static void printMatrix(int[][] m){
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
    public static void printMatrix(char[][] board){
        System.out.println("{");
        for(int i=0;i<board.length;i++){
            System.out.print("[");
            for(int j=0;j<board[i].length;j++){
                System.out.print(board[i][j]+",");
            }
            System.out.println("],");
        }
        System.out.println("}");
    }
    public static void printArray(int[] arr){
        System.out.print("[");
        for(int i : arr){
            System.out.print(i+", ");
        }
        System.out.println("]");
    }
    public static <T> void printArray(T[] arr){
        System.out.print("[");
        for(T t : arr){
            System.out.print(t+", ");
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
    public static <T> void printListList(List<List<T>> list){
        System.out.println("[");
        for(List<T> ls : list){
            printList(ls);
        }
        System.out.println("]");
    }
    public static <T> void printList(List<T> list){
        System.out.print("[");
        for (T e : list) {
            System.out.print(e+", ");
        }
        System.out.println("]");
    }
}
