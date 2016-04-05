package LeetCode.lc_001_050;

/**
 * Created by ytliu on 3/20/16.
 */
public class lc004_Median_of_Two_Sorted_Arrays {
    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1}, new int[]{1})); // 1
        System.out.println(findMedianSortedArrays(new int[]{4}, new int[]{1,2,3,5,6,7})); // 4
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{1,1})); // 1
    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        if(total%2==0){
            int lower = findKthSmallestNumberIn2SortedArrays(total/2, nums1, 0, nums1.length-1, nums2, 0, nums2.length-1);
            int upper = findKthSmallestNumberIn2SortedArrays(total/2+1, nums1, 0, nums1.length-1, nums2, 0, nums2.length-1);
            return (double)(lower+upper)/2;
        }else{
            return (double)findKthSmallestNumberIn2SortedArrays(total/2+1, nums1, 0, nums1.length-1, nums2, 0, nums2.length-1);
        }
    }
    private static int findKthSmallestNumberIn2SortedArrays(int k, int[] nums1, int nums1from, int nums1to, int[] nums2, int nums2from, int nums2to){
        // if either array has longth of 0, then return the kth of the other one
        if(nums1to<nums1from) return nums2[nums2from+k-1];
        if(nums2to<nums2from) return nums1[nums1from+k-1];
        // we have made sure each array has at least one element, so the kth smallest will be in either first index if k<=2

        int nums1Mid = (nums1to+nums1from)/2;
        int nums2Mid = (nums2to+nums2from)/2;

        int nums1CountLeft = nums1Mid-nums1from;
        int nums2CountLeft = nums2Mid-nums2from;
        // plus 1 because we will dump either median
        int totalCountLeft = nums1CountLeft+nums2CountLeft+1;

        if((nums1[nums1Mid] <= nums2[nums2Mid])){
            // k is in the totalCountLeft, so you just give up the larger one
            if(k<=totalCountLeft){
                return findKthSmallestNumberIn2SortedArrays(k, nums1, nums1from, nums1to, nums2, nums2from, nums2Mid-1);
            // k is not in the totalCountLet, the numbers on the left of smaller one (inclusive) could be removed
            }else{
                return findKthSmallestNumberIn2SortedArrays(k-nums1CountLeft-1, nums1, nums1Mid+1, nums1to, nums2, nums2from, nums2to);
            }
        } else {
            if(k<=totalCountLeft){
                return findKthSmallestNumberIn2SortedArrays(k, nums1, nums1from, nums1Mid-1, nums2, nums2from, nums2to);
            }else{
                return findKthSmallestNumberIn2SortedArrays(k-nums2CountLeft-1, nums1, nums1from, nums1to, nums2, nums2Mid+1, nums2to);
            }
        }
    }
}
