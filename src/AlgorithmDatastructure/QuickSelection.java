package AlgorithmDatastructure;

import Library.util.Printer;

import java.util.Arrays;

/**
 * Created by Tin on 7/6/16.
 */
public class QuickSelection {
    public static void main(String[] args) {
        Printer.printArray(findFirstKthLargests(new int[]{1, 4, 2, 3, 5, 7, 6}, 7));
    }
    public static int[] findFirstKthLargests(int[] nums, int k){
        int len = nums.length;
        int low = 0, high = len-1;
        while(low<=high){
            int cut = partition(nums, low, high);
            int onLeft = len-cut;
            if(onLeft==k) return Arrays.copyOfRange(nums, cut, len);
            else if(onLeft>k) low = cut+1;
            else high = cut-1;
        }
        return null;
    }
    public static int partition(int[] nums, int low, int high){
        int pivot = high;
        int swapIndex = low;
        for(int i=low;i<=high;i++){
            if(nums[i]<=nums[pivot]){
                int temp = nums[i];
                nums[i] = nums[swapIndex];
                nums[swapIndex++] = temp;
            }
        }
        return swapIndex-1;
    }

}
