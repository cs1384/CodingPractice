package AlgorithmDatastructure;

import java.util.*;

/**
 * Created by Tin on 6/26/15.
 */
public class TopologicalSort {
    public static class GNode{
        char value;
        boolean visited = false;
        List<GNode> linkTo = new ArrayList<GNode>();
        public GNode(char val){
            this.value = val;
        }
        public void addNode(GNode n){
            this.linkTo.add(n);
        }
        @Override
        public String toString(){
            return Character.toString(value);
        }
    }
    public static void main(String[] args){

        //see picture for FA homework9 quesiton5
        System.out.println("13. topological sort");
        GNode m = new GNode('m');
        GNode n = new GNode('n');
        GNode o = new GNode('o');
        GNode p = new GNode('p');
        GNode q = new GNode('q');
        GNode r = new GNode('r');
        GNode s = new GNode('s');
        GNode t = new GNode('t');
        GNode u = new GNode('u');
        GNode v = new GNode('v');
        GNode w = new GNode('w');
        GNode x = new GNode('x');
        GNode y = new GNode('y');
        GNode z = new GNode('z');
        m.addNode(x);m.addNode(q);m.addNode(r);
        n.addNode(q);n.addNode(u);n.addNode(o);
        o.addNode(r);o.addNode(s);
        p.addNode(o);p.addNode(s);
        q.addNode(t);
        r.addNode(u);r.addNode(y);
        s.addNode(r);
        u.addNode(t);
        v.addNode(x);v.addNode(w);
        y.addNode(v);
        List<GNode> set = new ArrayList<GNode>();
        set.add(m);set.add(n);set.add(p);
        System.out.println(taskOrder(set));


    }
    public static List<GNode> taskOrder(List<GNode> unconstrained){
        Stack<GNode> stack = new Stack<GNode>();
        Integer check = 1;
        for(int i=0;i<unconstrained.size();i++){
            GNode n = unconstrained.get(i);
            TDFS(n,stack,check);
        }
        List<GNode> result = new LinkedList<GNode>();
        while(!stack.isEmpty()){
            result.add(stack.pop());
        }
        return result;
    }
    public static void TDFS(GNode root, Stack<GNode> s, Integer check){
        System.out.println(root + ":" + check++);
        for(int i=0;i<root.linkTo.size();i++){
            if(root.linkTo.get(i).visited)
                continue;
            TDFS(root.linkTo.get(i),s,check);
        }
        root.visited = true;
        System.out.println(root + ":" + check++);
        s.push(root);
        return;
    }
}
