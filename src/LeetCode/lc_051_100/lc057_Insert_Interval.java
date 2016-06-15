package LeetCode.lc_051_100;

import LeetCode.util.Interval;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Tin on 6/14/16.
 */
public class lc057_Insert_Interval {
    public static void main(String[] args) {
        List<Interval> list1 = new ArrayList<>();
        list1.add(new Interval(1,3));
        list1.add(new Interval(6,9));
        System.out.println(insert(list1, new Interval(2,5)));
        List<Interval> list2 = new ArrayList<>();
        list2.add(new Interval(1,2));
        list2.add(new Interval(3,5));
        list2.add(new Interval(6,7));
        list2.add(new Interval(8,10));
        list2.add(new Interval(12,16));
        System.out.println(insert(list2, new Interval(4,9)));

    }
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        boolean inserted = false;
        for(Interval interval : intervals){
            if(interval.end<newInterval.start){
                res.add(interval);
            }else if(interval.start>newInterval.end) {
                if(!inserted){
                    res.add(newInterval);
                    inserted = true;
                }
                res.add(interval);
            }else{
                newInterval.start = Math.min(newInterval.start, interval.start);
                newInterval.end = Math.max(newInterval.end, interval.end);
            }
        }
        if(!inserted) res.add(newInterval);
        return res;
    }
}
