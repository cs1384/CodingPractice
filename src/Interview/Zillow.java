package Interview;

import java.util.*;

/**
 * Created by Tin on 6/27/16.
 */
public class Zillow {
    /*
    http://www.1point3acres.com/bbs/thread-176715-1-1.html
     */
    public static void main(String[] args) {
        System.out.println("1. Median of Two sorted Arrays");
        System.out.println("https://leetcode.com/problems/median-of-two-sorted-arrays/");

        System.out.println("2. Product of Array Except Self");
        System.out.println("https://leetcode.com/problems/product-of-array-except-self/");

        System.out.println("3. Bulls and Cows");
        System.out.println("https://leetcode.com/problems/bulls-and-cows/");

        System.out.println("4. Two sum");
        System.out.println("https://leetcode.com/problems/two-sum/");

        System.out.println("5. Factorial Trailing Zeroes");
        System.out.println("https://leetcode.com/problems/factorial-trailing-zeroes/");

        System.out.println("6. Maximum Subarray");
        System.out.println("https://leetcode.com/problems/maximum-subarray/");

        System.out.println("7. Find all subsets of a set");
        System.out.println("https://leetcode.com/problems/subsets/");

        System.out.println("8. Swap nodes in pairs in a linked list");
        System.out.println("https://leetcode.com/problems/swap-nodes-in-pairs/");

    }

    /*
    https://www.careercup.com/question?id=4908484506157056
     */
    interface MostViewed{
        void propertyViewed(String zpid);
        List<String> getCurrentMostPopular(int count);
    }
    class MostViewedImpl implements MostViewed{
        class DLLNode{
            DLLNode pre;
            DLLNode next;
            String zpid;
            int numViewed = 0;
            DLLNode(String zpid){
                this.zpid = zpid;
                numViewed = 1;
            }
        }
        class Viewed{
            Date date;
            String zpid;
            Viewed(String zpid){
                this.zpid = zpid;
                date = new Date();
            }
        }
        Map<String, DLLNode> map;
        DLLNode head;
        DLLNode newlyAdded;
        Queue<Viewed> queue;

        MostViewedImpl(){
            map = new HashMap<>();
            head = new DLLNode("");
            head.numViewed = Integer.MAX_VALUE;
            newlyAdded = head;
            queue = new LinkedList<>();
        }

        @Override
        public void propertyViewed(String zpid) {
            queue.offer(new Viewed(zpid));
            if(map.containsKey(zpid)){
                DLLNode dllNode = map.get(zpid);
                dllNode.numViewed++;
                moveUp(dllNode);
            }else{
                newlyAdded.next = new DLLNode(zpid);
                newlyAdded = newlyAdded.next;
                moveDown(newlyAdded);
            }
        }
        private void moveUp(DLLNode dllNode){
            while(dllNode.pre.numViewed<dllNode.numViewed){
                DLLNode previous = dllNode.pre;

                previous.next = dllNode.next;
                dllNode.next.pre = previous;

                previous.pre.next = dllNode;
                dllNode.pre = previous.pre;

                dllNode.next = previous;
                previous.pre = dllNode;
            }
        }
        private void moveDown(DLLNode dllNode){
            while(dllNode.next!=null && dllNode.next.numViewed>dllNode.numViewed){
                break;
            }
        }

        @Override
        public List<String> getCurrentMostPopular(int count) {
            List<String> res = new ArrayList<>();
            DLLNode op = head.next;
            while(op!=null){
                res.add(op.zpid);
                op = op.next;
            }
            return res;
        }
        public void cronJob(){
            Date twoHourBefore = new Date();
            while(!queue.isEmpty() && queue.peek().date.before(twoHourBefore)){
                Viewed viewed = queue.poll();
                DLLNode dllNode = map.get(viewed);
                dllNode.numViewed--;
                sort(dllNode);
            }
        }
        private void sort(DLLNode dllNode){
            if(dllNode.pre.numViewed<dllNode.numViewed){
                moveUp(dllNode);
            }else{
                moveDown(dllNode);
            }
        }

    }

}
