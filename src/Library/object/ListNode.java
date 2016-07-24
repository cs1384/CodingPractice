package Library.object;

/**
 * Created by ytliu on 3/12/16.
 */
public class ListNode extends Node{
    public ListNode next;
    public ListNode(int x) { super(x); }
    public void print(){
        ListNode op = this;
        while(op!=null){
            System.out.print(op.value+" -> ");
            op = op.next;
        }
        System.out.println("null");
    }
}
