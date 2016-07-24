package LeetCode.lc_301_350;

import LeetCode.util.Printer;

import java.util.*;

/**
 * Created by Tin on 7/19/16.
 */
public class lc349_Intersection_of_Two_Arrays {
    public static void main(String[] args) {
        Printer.printArray(intersection1(new int[]{1,2,2,1}, new int[]{2,2}));
        Printer.printArray(intersection2(new int[]{1,2,2,1}, new int[]{2,2}));
        Printer.printArray(intersection(new int[]{1,2,2,1}, new int[]{2,2}));
    }
    /*
    binary search
    N1 = nums1.length
    N2 = nums2.length
    O(N1logN2)
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        if(nums1.length<nums2.length) return intersection1(nums2, nums1);
        Arrays.sort(nums2); //sort short one
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<nums1.length;i++){
            if(binarySearch(nums2, nums1[i])!=-1){
                set.add(nums1[i]);
            }
        }
        int[] res = new int[set.size()];
        int index = 0;
        for(int i : set) res[index++] = i;
        return res;
    }
    private static int binarySearch(int[] arr, int target){
        int high = arr.length-1, low = 0;
        while(low<=high){
            int mid = (low+high)/2;
            if(arr[mid]==target) return mid;
            if(arr[mid]>target) high = mid-1;
            else low = mid+1;
        }
        return -1;
    }
    /*
    sort and two pointers
    O(NlogN)
     */
    public static int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);Arrays.sort(nums2);
        int pointer1 = 0, pointer2 = 0;
        Set<Integer> set = new HashSet<>();
        while(pointer1<nums1.length && pointer2<nums2.length){
            if(nums1[pointer1]==nums2[pointer2]){
                set.add(nums1[pointer1]);
                pointer1++;pointer2++;
            }else if(nums1[pointer1]>nums2[pointer2]){
                pointer2++;
            }else{
                pointer1++;
            }
        }
        int[] res = new int[set.size()];
        int index = 0;
        for(int i : set) res[index++] = i;
        return res;
    }
    /*
    Two sets
    O(N)
     */
    public static int[] intersection1(int[] nums1, int[] nums2) {
        if(nums1.length>nums2.length) return intersection1(nums2, nums1);
        Set<Integer> set = new HashSet<>();
        for(int i : nums1) set.add(i);
        Set<Integer> intersection = new HashSet<>();
        for(int i : nums2) if(set.contains(i)) intersection.add(i);
        int[] res = new int[intersection.size()];
        int index = 0;
        for(int i : intersection) res[index++] = i;
        return res;
    }
}
