package LeetCode.util;

/**
 * Created by Tin on 6/28/16.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        print(sb, 0);
        return sb.toString();
    }

    public void print(StringBuilder sb, int level){

        if(right!=null) right.print(sb, level+1);
        else{
            printLevel(sb, level+1);
            sb.append("null");sb.append("\n");
        }

        printLevel(sb, level);
        sb.append(val);sb.append("\n");

        if(left!=null) left.print(sb, level+1);
        else{
            printLevel(sb, level+1);
            sb.append("null");sb.append("\n");
        }
    }

    private void printLevel(StringBuilder sb, int level){
        for(int i=0;i<level;i++){
            sb.append("---");
        }
        sb.append(' ');
    }
}
