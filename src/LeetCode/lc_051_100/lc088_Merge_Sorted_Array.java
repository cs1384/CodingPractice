package LeetCode.lc_051_100;

/**
 * Created by Tin on 6/24/16.
 */
public class lc088_Merge_Sorted_Array {
    public static void main(String[] args) {

    }
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int op = m+n-1;
        m--;n--;
        for(;op>=0;op--){
            if(m>=0 && n>=0){
                if(nums1[m]>nums2[n]) nums1[op] = nums1[m--];
                else nums1[op] = nums2[n--];
            }else if(n>=0){
                nums1[op] = nums2[n--];
            }else{
                break; //rests are existing elements in num1
            }
        }
    }
}
