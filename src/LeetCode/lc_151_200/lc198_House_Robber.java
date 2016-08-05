package LeetCode.lc_151_200;

/**
 * Created by Tin on 8/4/16.
 */
public class lc198_House_Robber {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{2,1,1,2}));
    }
    public static int rob(int[] nums) {
        int len = nums.length;
        if(len<=1) return len==0?0:nums[0];
        int tillNotConnected = nums[0];
        int tillNextDoor = Math.max(nums[0], nums[1]);
        for(int i=2;i<len;i++){
            int temp = tillNextDoor;
            tillNextDoor = Math.max(tillNotConnected+nums[i], tillNextDoor);
            tillNotConnected = temp;
        }
        return tillNextDoor;
    }
}
