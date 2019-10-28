package Library.object;

/**
 * Created by Tin on 6/16/16.
 */
public class TreeNode extends Node {
    public TreeNode left = null;
    public TreeNode right = null;
    public TreeNode(int value){
        super(value);
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        print(sb, 0);
        return sb.toString();
    }
    private void print(StringBuilder sb, int level){

        if(right!=null) right.print(sb, level+1);
        else{
            printLevel(sb, level+1);
            sb.append("null");sb.append("\n");
        }

        printLevel(sb, level);
        sb.append(value);sb.append("\n");

        if(left!=null) left.print(sb, level+1);
        else{
            printLevel(sb, level+1);
            sb.append("null");sb.append("\n");
        }
    }
    private void printLevel(StringBuilder sb, int level){
        for(int i=0;i<level;i++) sb.append("---");
        sb.append(' ');
    }
}
