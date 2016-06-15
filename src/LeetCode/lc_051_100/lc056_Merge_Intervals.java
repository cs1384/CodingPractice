package LeetCode.lc_051_100;

import LeetCode.util.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Tin on 6/14/16.
 */
public class lc056_Merge_Intervals {
    public static void main(String[] args) {

    }

    /**
     * Fir thought is to insert one by going through the rest, this takes O(N^2).
     * So the better approach would be sorting first O(NlogN) and merge while
     * going through O(N) -> O(NlogN) in total.
     * @param intervals
     * @return
     */
    public List<Interval> merge(List<Interval> intervals) {
        if(intervals==null || intervals.size()==0) return intervals;
        Comparator<Interval> comp = new Comparator<Interval>(){
            @Override
            public int compare(Interval o1, Interval o2){
                if(o1.start!=o2.start) return o1.start - o2.start;
                else return o1.end - o2.end;
            }
        };
        // sorted by their start and then end point
        Collections.sort(intervals, comp);
        List<Interval> res = new ArrayList<>();
        Interval merger = intervals.get(0);
        Interval cur;
        for(int i=1;i<intervals.size();i++){
            cur = intervals.get(i);
            // if nothing to merge, put into res
            if(merger.end<cur.start){
                res.add(merger);
                merger = cur;
                // if merge needs to be extended
            }else if(merger.end<=cur.end){
                merger.end = cur.end;
            }
        }
        res.add(merger);
        return res;
    }
}
