package LeetCode.lc_101_150;

/**
 * Created by Tin on 7/23/16.
 */
public class lc134_Gas_Station {
    public static void main(String[] args) {
        System.out.println(canCompleteCircuit(new int[]{3,4,7,2}, new int[]{2,6,5,4}));
    }
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int needInTheEnd = 0;
        int curGas = 0, curCost = 0;
        int start = 0;
        for(int i=0;i<len;i++){
            if(curGas<curCost){
                needInTheEnd += curCost-curGas;
                curGas = 0;
                curCost = 0;
                start = i;
            }
            curGas += gas[i];
            curCost += cost[i];
        }
        if(curGas-curCost>=needInTheEnd) return start;
        return -1;
    }
}
