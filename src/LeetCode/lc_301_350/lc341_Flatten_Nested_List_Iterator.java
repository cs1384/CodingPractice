package LeetCode.lc_301_350;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by Tin on 7/26/16.
 */
public class lc341_Flatten_Nested_List_Iterator {
    public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
    public static class NestedIntegerImpl implements NestedInteger{
        Integer value = null;
        List<NestedInteger> list = null;
        NestedIntegerImpl(Integer value){
            this.value = value;
        }
        NestedIntegerImpl(List<NestedInteger> list){
            this.list = list;
        }
        @Override
        public boolean isInteger() {
            return value!=null;
        }
        @Override
        public Integer getInteger() {
            return value;
        }
        @Override
        public List<NestedInteger> getList() {
            return list;
        }
    }
    public static void main(String[] args) {
        List<NestedInteger> nestedList = new ArrayList<>();
        List<NestedInteger> list1 = new ArrayList<>();
        list1.add(new NestedIntegerImpl(1));
        list1.add(new NestedIntegerImpl(1));
        NestedIntegerImpl nestedInteger1 = new NestedIntegerImpl(list1);
        nestedList.add(nestedInteger1);
        nestedList.add(new NestedIntegerImpl(2));
        List<NestedInteger> list2 = new ArrayList<>(list1);
        NestedIntegerImpl nestedInteger2 = new NestedIntegerImpl(list2);
        nestedList.add(nestedInteger2);

        NestedIterator nestedIterator = new NestedIterator(nestedList);
        while(nestedIterator.hasNext()){
            System.out.println(nestedIterator.next());
        }

    }
    public static class NestedIterator implements Iterator<Integer> {

        Stack<NestedInteger> stack = new Stack<>();
        public NestedIterator(List<NestedInteger> nestedList) {
            for(int i=nestedList.size()-1;i>=0;i--){
                stack.push(nestedList.get(i));
            }
        }

        @Override
        public boolean hasNext() {
            while(!stack.isEmpty()){
                NestedInteger nestedInteger = stack.peek();
                if(nestedInteger.isInteger()) return true;
                stack.pop();
                List<NestedInteger> list = nestedInteger.getList();
                for(int i=list.size()-1;i>=0;i--){
                    stack.push(list.get(i));
                }
            }
            return false;
        }

        @Override
        public Integer next() {
            return stack.pop().getInteger();
        }
    }
    /*
    not working
     */
    public static class NestedIterator1 implements Iterator<Integer> {
        Stack<List<NestedInteger>> lists = new Stack<>();
        Stack<Integer> indices = new Stack<>();
        public NestedIterator1(List<NestedInteger> nestedList) {
            if(nestedList==null || nestedList.size()==0) return;
            lists.push(nestedList);
            indices.push(-1);
            forwardIndex();
        }
        @Override
        public Integer next() {
            int index = indices.peek();
            List<NestedInteger> list = lists.peek();
            while(!list.get(index).isInteger()){
                lists.push(list.get(index).getList());
                indices.push(0);
                list = lists.peek();
                index = indices.peek();
            }
            Integer res = list.get(index).getInteger();
            forwardIndex();
            return res;
        }
        private void forwardIndex(){
            indices.push(indices.pop()+1);
            while(indices.peek()==lists.peek().size() || lists.peek().size()==0){
                lists.pop();
                indices.pop();
                if(!indices.isEmpty()) indices.push(indices.pop()+1);
                else break;
            }
        }
        @Override
        public boolean hasNext() {
            return !indices.isEmpty();
        }
    }
}
