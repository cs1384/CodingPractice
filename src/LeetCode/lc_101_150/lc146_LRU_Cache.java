package LeetCode.lc_101_150;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tin on 7/26/16.
 */
public class lc146_LRU_Cache {
    public static void main(String[] args) {

    }
    public class LRUCache{
        class DLLNode{
            DLLNode prev = null;
            DLLNode next = null;
            int value;
            int key;
            DLLNode(int key, int value){
                this.key = key;
                this.value = value;
            }
        }
        int capacity;
        DLLNode dummy;
        DLLNode rear;
        Map<Integer, DLLNode> keyToNode;
        public LRUCache(int capacity) {
            this.capacity = capacity;
            dummy = new DLLNode(-1,-1);
            rear = dummy;
            keyToNode = new HashMap<>();
        }

        public int get(int key) {
            if(!keyToNode.containsKey(key)) return -1;
            DLLNode node = keyToNode.get(key);
            if(dummy.next!=node) {
                remove(node);
                use(node);
            }
            return node.value;
        }

        public void set(int key, int value) {
            if(keyToNode.containsKey(key)){
                DLLNode node = keyToNode.get(key);
                node.value = value;
                if(dummy.next!=node) {
                    remove(node);
                    use(node);
                }
            }else{
                if(keyToNode.size()==capacity) {
                    keyToNode.remove(rear.key);
                    remove(rear);
                }
                DLLNode node = new DLLNode(key, value);
                keyToNode.put(key, node);
                use(node);
            }
        }

        private void use(DLLNode node){
            if(dummy==rear){
                dummy.next = node;
                node.prev = dummy;
                rear = node;
            }else{
                node.next = dummy.next;
                dummy.next.prev = node;
                node.prev = dummy;
                dummy.next = node;
            }
        }
        private void remove(DLLNode node){
            if(node==rear){
                rear = node.prev;
                rear.next = null;
            }else{
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            node.prev = null;
            node.next = null;
        }
    }
}
