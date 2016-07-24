package Library.object;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tin on 7/9/16.
 */
public class GraphNode extends Node{
    List<GraphNode> neighbors;
    public GraphNode(int value){
        super(value);
        this.neighbors = new ArrayList<>();
    }
}
