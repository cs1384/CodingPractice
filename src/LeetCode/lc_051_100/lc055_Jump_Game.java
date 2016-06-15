package LeetCode.lc_051_100;

/**
 * Created by Tin on 6/14/16.
 */
public class lc055_Jump_Game {
    public static void main(String[] args) {
//        System.out.println(canJump(new int[]{2,3,1,1,4}));
//        System.out.println(canJump(new int[]{3,2,1,0,4}));
        System.out.println(canJump(new int[]{4,3,2,1,0}));
    }
    public static boolean canJump(int[] nums) {
        int reach = 0;
        int op = 0;
        while(op<=reach){
            int newReach = op+nums[op];
            if(reach>=nums.length-1) return true;
            reach = Math.max(reach, newReach);
            op++;
        }
        return false;
    }
}
