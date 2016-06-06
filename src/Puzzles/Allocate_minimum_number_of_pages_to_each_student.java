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
        DPapproach();
    }
    public static void DPapproach() {
        Scanner sc = new Scanner(System.in);
        int testCaseNum = sc.nextInt();
        int k = 0;
        while(k<testCaseNum){
            int bookNum = sc.nextInt();
            int[] books = new int[bookNum];
            int b = 0;
            while(b<bookNum) books[b++] = sc.nextInt();
            int numStudent = sc.nextInt();
            int[][] indexToEndBooks_numStudent = new int[bookNum][numStudent];
            int sum = 0;
            int max = Integer.MIN_VALUE;
            for(int i=bookNum-1;i>=0;i--){
                sum += books[i];
                max = Math.max(books[i],max);
                for(int j=1;j<=bookNum-i && j<=numStudent;j++){
                    if(j==1) indexToEndBooks_numStudent[i][j-1] = sum;
                    else if(j==bookNum-i) indexToEndBooks_numStudent[i][j-1] = max;
                    else {
                        int localSum = 0;
                        int minMax = Integer.MAX_VALUE;
                        for(int cut=0;bookNum-i-1-cut>=j-1;cut++){
                            localSum += books[i+cut];
                            int localMax = Math.max(localSum, indexToEndBooks_numStudent[i+1+cut][j-1-1]);
                            minMax = Math.min(localMax, minMax);
                        }
                        indexToEndBooks_numStudent[i][j-1] = minMax;
                    }
                }
                Printer.printMatrix(indexToEndBooks_numStudent);
            }
            System.out.println(indexToEndBooks_numStudent[0][numStudent-1]);
            k++;
        }
        sc.close();
    }

    /*
    DFS! Slow. and we will calculate some same result mutiple times
     */
    public static void DFSapproach() {
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
