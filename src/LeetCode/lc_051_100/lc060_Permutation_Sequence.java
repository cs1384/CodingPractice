package LeetCode.lc_051_100;

import LeetCode.util.Printer;

/**
 * Created by Tin on 6/15/16.
 */
public class lc060_Permutation_Sequence {
    public static void main(String[] args) {
        System.out.println(getPermutation(3,4)); //231
        System.out.println(getPermutation(3,5)); //312
        System.out.println(getPermutation(4,10)); //2341
        System.out.println(getPermutation(8,8590)); //26847351
    }
    public static String getPermutation(int n, int k) {
        int[] nums = new int[n];
        for(int i=0;i<n;i++) nums[i] = i+1;

        StringBuilder sb = new StringBuilder();
        int nLeftDigits = n-1;
        while(sb.length()<n){
            int fac = factorial(nLeftDigits);
            /*
                k/fac   k   require
                   0    1       1
                   1    2       1
                   1    3       2
                   2    4       2
             */
            int require = k%fac==0?k/fac:k/fac+1; // require nth least available num
            int op = 0;
            for(int i=0;i<nums.length;i++){
                if(nums[i]==0) continue; // 0 represents "used"
                if(++op==require){
                    sb.append(nums[i]);
                    nums[i] = 0;
                    break;
                }
            }
            k -= fac*(require-1);
            nLeftDigits--;
        }
        return sb.toString();
    }
    private static int factorial(int n){
        int result = 1;
        for(int i=1;i<=n;i++) result *= i;
        return result;
    }
    /**
     * basic solution: calculate each of them until found the Kth
     * @param n
     * @param k
     * @return
     */
    public static String getPermutation1(int n, int k) {
        int[] nums = new int[n];
        for(int i=0;i<n;i++) nums[i] = i+1;
        for(int i=2;i<=k;i++){

            Printer.printArray(nums);

            int swapTarget = n-2;
            while(nums[swapTarget]>=nums[swapTarget+1]) swapTarget--;
            int toBeSwapped = n-1;
            while(nums[swapTarget]>=nums[toBeSwapped]) toBeSwapped--;
            swap(nums, swapTarget, toBeSwapped);

            int head = swapTarget+1;
            int rear = n-1;
            while(head<rear) swap(nums, head++, rear--);
        }
        StringBuilder sb = new StringBuilder();
        for(int i : nums) sb.append(i);
        return sb.toString();
    }
    private static void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
