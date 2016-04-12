package Interview;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Properties;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 4/7/16.
 */
public class Blippar {
    public static void main(String[] args) {
        /*
        1)  Given two int arrays a&b, sorted by descending order
            A[] = {20, 15, 8, 6, 4, 0, -1, -10}
            B[] = {25, 21, 19, 10, 2, -5}
            Find the top k elements from a&b.
            I.e k=6 {25, 21, 20, 19, 15, 10}
         */
        int[] a = {20,15,8,5,2,0,-3};
        int[] b = {25,21,19,10,4,-2,-7};
        Printer.printArray(findTopKFromTwoSortedArrays(a,b,6));
        /*
        2)  If you can change a and b, assume k <= len(a), can you return the top k elements in array a with only O(1) additional space.
         */
        findTopKFromTwoSortedArraysInPlace(a,b,6);
        Printer.printArray(a);
        /*
         3) Given M int arrays
            A[0] = {4,3,2}
            A[1] = {2,1}
            …
            A[M-1] = {}
            Find the top k from A’s
         */
        int[][] arrays = {
                {20,15,8,5,2,0,-3},
                {25,21,19,10,4,-2,-7},
                {23,21,18,7,3,0,-1},
                {30}
        };
        Printer.printArray(findTopKFromMultipleSortedArrays(arrays, 7));


    }
    static int[] findTopKFromTwoSortedArrays(int[] a, int[] b, int k){
        int[] res = new int[k];
        int aIndex = 0;
        int bIndex = 0;
        for(int i=0;i<k;i++){
            if(aIndex<a.length && bIndex<b.length){
                if(a[aIndex]>b[bIndex]) res[i] = a[aIndex++];
                else res[i] = b[bIndex++];
            }else if(aIndex<a.length){
                res[i] = a[aIndex++];
            }else{
                res[i] = b[bIndex++];
            }
        }
        return res;
    }
    static void findTopKFromTwoSortedArraysInPlace(int[] a, int[] b, int k){
        int aIndex = 0;
        int bIndex = 0;
        // first pass
        for(int i=0;i<k;i++){
            if(aIndex<a.length && bIndex<b.length){
                if(a[aIndex]>b[bIndex]) aIndex++;
                else bIndex++;
            }else if(aIndex<a.length){
                aIndex++;
            }else{
                bIndex++;
            }
        }
        // set these three variable into indices base
        aIndex--;bIndex--;k--;
        while(k>=0){
            if(aIndex>=0 && bIndex>=0){
                // if the element in a is greater, just decrement the aIndex;
                if(a[aIndex]<b[bIndex]) a[k] = a[aIndex--];
                // else put bIndex into k index in a
                else a[k] = b[bIndex--];
            }else if(bIndex>=0){
                // put bIndex into k index in a
                a[k] = b[bIndex--];
            }else{
                // only elements in a left, no swap at all
                break;
            }
            k--;
        }
        return;
    }

    static class ToCompare{
        int arrayId;
        int index;
        int value;
        ToCompare(int arrayId, int index, int value){
            this.arrayId = arrayId;
            this.index = index;
            this.value = value;
        }
    }
    static int[] findTopKFromMultipleSortedArrays(int[][] arrays, int k){
        PriorityQueue<ToCompare> heap = new PriorityQueue<>(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(((ToCompare)o1).value < ((ToCompare)o2).value) return 1;
                else if (((ToCompare)o1).value > ((ToCompare)o2).value) return -1;
                return 0;
            }
        });
        for(int i=0;i<arrays.length;i++){
            ToCompare toCompare = new ToCompare(i, 0, arrays[i][0]);
            heap.offer(toCompare);
        }
        int[] res = new int[k];
        int i = 0;
        while(i<k && !heap.isEmpty()){
            ToCompare toCompare = heap.poll();
            res[i++] = toCompare.value;
            int arrayId = toCompare.arrayId;
            int index = toCompare.index + 1;
            if(index >= arrays[arrayId].length) continue;
            ToCompare add = new ToCompare(arrayId, index, arrays[arrayId][index]);
            heap.offer(add);
        }
        return res;
    }
}
