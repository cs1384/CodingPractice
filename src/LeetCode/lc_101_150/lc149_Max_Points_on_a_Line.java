package LeetCode.lc_101_150;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Tin on 7/26/16.
 */
public class lc149_Max_Points_on_a_Line {
    static class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
        public String toString(){
            return "["+x+","+y+"]";
        }
    }
    public static void main(String[] args) {
        Point[] points = {
                new Point(84, 250),
                new Point(0, 0),
                new Point(1, 0),
                new Point(0, -70),
                new Point(0, -70),
                new Point(1, -1),
                new Point(21, 10),
                new Point(42, 90),
                new Point(-42, -230)
        };
        System.out.println(maxPoints(points));
    }
    public static int maxPoints(Point[] points) {
        Map<Double, Integer> slopeToCount = new HashMap<>();
        int len = points.length;
        if(len<=2) return len;
        int max = 0;
        for(int i=0;i<len;i++){
            Point a = points[i];
            int localMax = 0;
            int sameCount = 1; // itself
            slopeToCount.clear();
            double slope;
            for(int j=i+1;j<len;j++){
                Point b = points[j];
                // if the same point
                if(a.x-b.x==0 && a.y-b.y==0){
                    sameCount++;
                    continue;
                }
                // decide slope
                if(a.x-b.x==0) slope = 2.0;
                else if(a.y-b.y==0) slope = 0.0;
                else slope = (double)(a.y-b.y)/(a.x-b.x);
//                System.out.println(a+" and "+b+" has slope "+slope);
                // record the slope count
                if(!slopeToCount.containsKey(slope)) slopeToCount.put(slope, 1);
                else slopeToCount.put(slope, slopeToCount.get(slope)+1);
                localMax = Math.max(localMax, slopeToCount.get(slope));
            }
            max = Math.max(max, localMax+sameCount);
        }
        return max;
    }

}
