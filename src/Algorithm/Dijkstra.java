package Algorithm;

import java.util.*;
/**
 * Created by Tin on 6/5/15.
 */
public class Dijkstra {
    static class Node{
        String name;
        Map<Node, Integer> neighbors = new HashMap<>();
        Node(String name){
            this.name = name;
        }
        @Override
        public String toString(){
            return name;
        }
    }
    static class Record{
        Node node;
        int distance = 0;
        Record(Node node){
            this.node = node;
        }
        Record(Node node, int distance){
            this.node = node;
            this.distance = distance;
        }
    }

    public static void main(String[] args){
        Node seattle = new Node("Seattle");
        Node newyork = new Node("New York");
        Node chicago = new Node("Chicago");
        Node sanjose = new Node("San Jose");
        Node boston = new Node("Boston");
        Node cary = new Node("Cary");
        Node dallas = new Node("Dallas");
        seattle.neighbors.put(chicago, 7);
        seattle.neighbors.put(sanjose, 5);
        newyork.neighbors.put(boston, 3);
        newyork.neighbors.put(cary, 3);
        chicago.neighbors.put(seattle, 7);
        chicago.neighbors.put(boston, 3);
        chicago.neighbors.put(sanjose, 5);
        sanjose.neighbors.put(seattle, 5);
        sanjose.neighbors.put(chicago, 5);
        sanjose.neighbors.put(dallas, 5);
        boston.neighbors.put(chicago, 3);
        boston.neighbors.put(newyork, 3);
        cary.neighbors.put(newyork, 3);
        cary.neighbors.put(dallas, 5);
        dallas.neighbors.put(cary, 5);
        dallas.neighbors.put(sanjose, 5);

        System.out.println("=====> "+newyork);
        printMap(shortestDistances(newyork));
        System.out.println("=====>"+sanjose);
        printMap(shortestDistances(sanjose));

    }
    private static <K,V> void printMap(Map<K,V> map){
        for(K key : map.keySet()){
            System.out.println("key:"+key+" -> value:"+map.get(key));
        }
    }

    public static Map<Node, Integer> shortestDistances(Node node){
        // to track the minimum distance
        Map<Node, Integer> res = initialize(node);
        res.put(node, 0);
        Comparator<Record> comp = new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return o1.distance - o2.distance;
            }
        };
        PriorityQueue<Record> pq = new PriorityQueue<>(comp);
        pq.offer(new Record(node));
        while(!pq.isEmpty()){
            Record op = pq.poll();
            for(Node n : op.node.neighbors.keySet()){
                int curMin = res.get(n);
                int byPass = res.get(op.node)+op.node.neighbors.get(n);
                int dis = Math.min(curMin, byPass);
                if(dis<res.get(n)){
                    // change the tracking
                    res.put(n,dis);
                    // and offer to queue for update
                    pq.offer(new Record(n, dis));
                }
            }
        }
        return res;
    }

    private static Map<Node, Integer> initialize(Node node){
        Map<Node, Integer> res = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            Node op = queue.poll();
            res.put(op, Integer.MAX_VALUE);
            for(Node n : op.neighbors.keySet()){
                if(!res.containsKey(n)) queue.offer(n);
            }
        }
        return res;
    }


}
