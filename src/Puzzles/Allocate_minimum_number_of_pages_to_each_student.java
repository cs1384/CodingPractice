package Puzzles;

import java.util.Scanner;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 4/15/16.
 */
public class Allocate_minimum_number_of_pages_to_each_student {
    static int minMax;
    static int op;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCaseNum = sc.nextInt();
        int i = 0;
        while(i<testCaseNum){
            minMax = Integer.MAX_VALUE;
            op = 0;
            int bookNum = sc.nextInt();
            int[] books = new int[bookNum];
            int j = 0;
            while(j<bookNum){
                books[j++] = sc.nextInt();
            }
//            Printer.printArray(books);
            int[]  ref  = new int[bookNum+1];
            ref[0] = 0;
            for(int k=1;k<ref.length;k++) ref[k] = ref[k-1]+books[k-1];
//            Printer.printArray(ref);
            int numStudent = sc.nextInt();
            allocateMinimumBookPages(ref, books, 0, numStudent);
            System.out.println(minMax);
            i++;
        }
        sc.close();
    }
    // [0, 12, 46, 113, 203]
    // [12, 34, 67, 90]
    private static void allocateMinimumBookPages(int[] ref, int[] books, int cut, int numStudent){
//        System.out.println("numStudent:"+numStudent);
//        System.out.println("cut:"+cut);
        if(numStudent==1) {
            int range = ref[ref.length-1] - ref[cut];
//            System.out.println("ragne:"+range);
            int temp = op;
            op = Math.max(range, op);
            minMax = Math.min(op, minMax);
            op = temp;
            return;
        }
        int stop = cut+1;
        while(stop<books.length) {
            int temp = op;
            int range = ref[stop] - ref[cut];
            op = Math.max(range, op);
//            System.out.println("ragne:"+range);
            allocateMinimumBookPages(ref, books, stop, numStudent - 1);
            op = temp;
            stop++;
        }
    }

}
