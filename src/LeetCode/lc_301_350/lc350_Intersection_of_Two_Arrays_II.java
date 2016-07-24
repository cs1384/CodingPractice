package LeetCode.lc_301_350;

import LeetCode.util.Printer;

import java.util.*;

/**
 * Created by Tin on 7/20/16.
 */
public class lc350_Intersection_of_Two_Arrays_II {
    public static void main(String[] args) {
        Printer.printArray(intersect(new int[]{1,2,2,1}, new int[]{2,2}));
    }
    public static int[] intersect(int[] nums1, int[] nums2) {
        if(nums1.length>nums2.length) return intersect1(nums2, nums1);
        Arrays.sort(nums1);Arrays.sort(nums2);
        int pre = nums1.length==0||nums1[0]==1?0:1;
        int index = 0;
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<nums1.length;i++){
            if(nums1[i]==pre && index!=-1){
                if(index+1<nums2.length && nums2[index+1]==nums1[i]){
                    list.add(nums2[++index]);
                }
            }else{
                index = binarySearch(nums2, nums1[i]);
                if(index!=-1){
                    list.add(nums2[index]);
                }
            }
            pre = nums1[i];
        }
        int[] res = new int[list.size()];
        for(int i=0;i<res.length;i++) res[i] = list.get(i);
        return res;
    }
    private static int binarySearch(int[] arr, int target){
        int low = 0, high = arr.length-1;
        while(low<=high){
            int mid = (low+high)/2;
            if(arr[mid]==target){
                if(mid==0 || arr[mid-1]!=target) return mid;
                else high = mid-1;
            }else if(arr[mid]>target) high = mid-1;
            else low = mid+1;
        }
        return -1;
    }
    public static int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);Arrays.sort(nums2);
        int pointer1 = 0, pointer2 = 0;
        List<Integer> list = new ArrayList<>();
        while(pointer1<nums1.length && pointer2<nums2.length){
            if(nums1[pointer1] == nums2[pointer2]){
                list.add(nums1[pointer1]);
                pointer1++;pointer2++;
            }else if(nums1[pointer1]>nums2[pointer2]){
                pointer2++;
            }else{
                pointer1++;
            }
        }
        int[] res = new int[list.size()];
        for(int i=0;i<res.length;i++) res[i] = list.get(i);
        return res;
    }
    public static int[] intersect1(int[] nums1, int[] nums2) {
        if(nums1.length>nums2.length) return intersect1(nums2, nums1);
        Map<Integer, Integer> numToCount = new HashMap<>();
        for(int i=0;i<nums1.length;i++){
            int num = nums1[i];
            if(!numToCount.containsKey(num)) numToCount.put(num, 0);
            numToCount.put(num, numToCount.get(num)+1);
        }
        List<Integer> res = new ArrayList<>();
        for(int i=0;i<nums2.length;i++){
            int num = nums2[i];
            if(numToCount.containsKey(num)){
                res.add(num);
                numToCount.put(num, numToCount.get(num)-1);
                if(numToCount.get(num)==0) numToCount.remove(num);
            }
        }
        int[] arr = new int[res.size()];
        for(int i=0;i<arr.length;i++) arr[i] = res.get(i);
        return arr;
    }
}
