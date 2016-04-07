package LeetCode.lc_301_350;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 4/6/16.
 */
public class lc332_Reconstruct_Itinerary {
    public static void main(String[] args) {
//        System.out.println(findItinerary(new String[][]{{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}}));
//        System.out.println(findItinerary(new String[][]{{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}}));
        System.out.println(findItinerary(new String[][]{{"EZE","TIA"},{"EZE","HBA"},{"AXA","TIA"},{"JFK","AXA"},{"ANU","JFK"},{"ADL","ANU"},{"TIA","AUA"},{"ANU","AUA"},{"ADL","EZE"},{"ADL","EZE"},{"EZE","ADL"},{"AXA","EZE"},{"AUA","AXA"},{"JFK","AXA"},{"AXA","AUA"},{"AUA","ADL"},{"ANU","EZE"},{"TIA","ADL"},{"EZE","ANU"},{"AUA","ANU"}}));
    }

    static Map<String, LinkedList<String>> fromTo;
    static LinkedList<String> res;
    static int ticketNumber;

    public static List<String> findItinerary(String[][] tickets) {
        fromTo = new HashMap<>();
        for(String[] ticket : tickets){
            fromTo.putIfAbsent(ticket[0], new LinkedList<>());
            fromTo.get(ticket[0]).offer(ticket[1]);
        }
        for(LinkedList<String> list : fromTo.values()){
            Collections.sort(list);
        }
        res = new LinkedList<>();
        res.add("JFK");
        ticketNumber = tickets.length;
        findItineraryDFS("JFK", 0);
        return res;
    }
    private static boolean findItineraryDFS(String fromPort, int journeyNumber){
        if(journeyNumber==ticketNumber) return true;
        if(!fromTo.containsKey(fromPort)) return false;
        int top = fromTo.get(fromPort).size();
        for(int i=0;i<top;i++){
            String toPort = fromTo.get(fromPort).removeFirst();
            res.add(toPort);
            if(findItineraryDFS(toPort, journeyNumber+1)) return true;
            fromTo.get(fromPort).add(toPort);
            res.removeLast();
        }
        return false;
    }
}
