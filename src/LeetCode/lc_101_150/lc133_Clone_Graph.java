package LeetCode.lc_101_150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created by ytliu on 4/3/16.
 */
public class lc133_Clone_Graph {
    static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    };

    public static void main(String[] args) {
        // {0,0,0}
        UndirectedGraphNode node = new UndirectedGraphNode(0);
        node.neighbors.add(node);
        node.neighbors.add(node);
        cloneGraph(node);
        // {-1,1#1}

    }
    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node==null) return null;
        // store cloned nodes
        Map<Integer, UndirectedGraphNode> labelToNode = new HashMap<>();
        // put first node
        labelToNode.put(node.label, new UndirectedGraphNode(node.label));
        // BFS to link cloned neighbors and to process each original node
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        // offer first node
        queue.offer(node);
        while(!queue.isEmpty()){
            UndirectedGraphNode n = queue.poll();
            UndirectedGraphNode cloned = labelToNode.get(n.label);
            for(UndirectedGraphNode neighbor : n.neighbors){
                // if the cloned neighbor exists, add it to the working cloned node
                if(!labelToNode.containsKey(neighbor.label)){
                    labelToNode.put(neighbor.label, new UndirectedGraphNode(neighbor.label));
                    queue.add(neighbor);
                }
                cloned.neighbors.add(labelToNode.get(neighbor.label));
            }
        }
        return labelToNode.get(node.label);
    }
}
