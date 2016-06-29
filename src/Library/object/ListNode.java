package Library.object;

/**
 * Created by ytliu on 3/12/16.
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }
    public void print(){
        ListNode op = this;
        while(op!=null){
            System.out.print(op.val+" -> ");
            op = op.next;
        }
        System.out.println("null");
    }
}
