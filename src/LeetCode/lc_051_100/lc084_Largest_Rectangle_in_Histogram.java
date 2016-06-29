package LeetCode.lc_051_100;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Tin on 6/23/16.
 */
public class lc084_Largest_Rectangle_in_Histogram {
    public static void main(String[] args) {
        int[] heights1 = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea(heights1));//10
        int[] heights2 = {0,1,0,1};
        System.out.println(largestRectangleArea(heights2));//1
        int[] heights3 = {2,1,2};
        System.out.println(largestRectangleArea(heights3));//3
        int[] heights4 = {4,2,0,3,2,5};
        System.out.println(largestRectangleArea(heights4));//6
        int[] heights5 = {3,6,5,7,4,8,1,0};
        System.out.println(largestRectangleArea(heights5));//20
    }
    public static int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if(len==0) return 0;
        Stack<Integer> capIndice = new Stack<>();
        int max = 0;
        for(int i=0;i<=len;i++){
            int curCap = i<len?heights[i]:0;
            while(!capIndice.isEmpty() && heights[capIndice.peek()]>curCap){
                int index = capIndice.pop();
                int area;
                if(capIndice.isEmpty()) area = heights[index]*(i-0);
                else area = heights[index]*(i-(capIndice.peek()+1));
                max = Math.max(max, area);
            }
            capIndice.push(i);
        }
        return max;
    }
    /*
    Basic idea is to based on every height, calculate the largest rectangle
    it can get. This takes O(N^2)

    O(NlogN) makes not sense, so I tried with O(N): {2,1,5,6,2,3}
    When I at 2, I got area=2*1
    When I at 1, I got area=2*1 or area=1*2
    When I at 5, I got area=2*1, area=1*3, or area=5*1
    Note that area=2*1 will not change becuase 1 blocks its height, so when we
    get something lower, we can seal some deals.
     */
    public static int largestRectangleArea1(int[] heights) {
        int len = heights.length;
        if(len==0) return 0;
        Stack<Integer> capIndice = new Stack<>();
        int max = 0;
        for(int i=0;i<=len;i++){
            int curCap = i<len?heights[i]:0; //when i=len, seal all before
            if(!capIndice.isEmpty() && curCap<heights[capIndice.peek()]){
                while(!capIndice.isEmpty() && heights[capIndice.peek()]>curCap){
                    int index = capIndice.pop();
                    int area;
                    // from either the index of lower height or the leftmost
                    if(capIndice.isEmpty()) area = heights[index]*(i-0);
                    else area = heights[index]*(i-(capIndice.peek()+1));
                    max = Math.max(max, area);
                }
                /*
                "<=": in {4,2,0,3,2,5}, 2 will be calculated twice, but it does
                not affect the result. but in {0,1,0,1}, 0 will help achieve the
                right answer.
                 */
                if(capIndice.isEmpty() || heights[capIndice.peek()]<=curCap){
                    capIndice.push(i);
                }
            }else{
                capIndice.push(i);
            }
        }
        return max;
    }
}
