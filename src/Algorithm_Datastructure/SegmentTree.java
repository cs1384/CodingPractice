package Algorithm_Datastructure;

/**
 * Created by ytliu on 1/4/16.
 *
 * A segment tree is a tree data structure for storing intervals, or segments.
 *
 * Take leetcode: 307. Range Sum Query - Mutable as the example
 */
public class SegmentTree {

    private static class SegmentTreeNode{
        int start, end;
        SegmentTreeNode left=null, right=null;
        int sum=0;
        public SegmentTreeNode(int start, int end){
            this.start = start;
            this.end = end;
        }
        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            if(left!=null) sb.append(left.toString());
            sb.append(sum+"\n");
            if(right!=null) sb.append(right.toString());
            return sb.toString();
        }
    }
    SegmentTreeNode root = null;

    /**
     * Tree Constructor: build the tree based on the given array
     * @param arr
     */
    public SegmentTree(int[] arr){
        root = buildTree(arr, 0, arr.length-1);
    }
    private SegmentTreeNode buildTree(int[] arr, int start, int end){
        if(start>end) return null;
        SegmentTreeNode stn = new SegmentTreeNode(start, end);
        if(start==end){
            stn.sum = arr[start];
        }else{
            int mid = start + (end-start)/2;
            stn.left = buildTree(arr, start, mid);
            stn.right = buildTree(arr, mid+1, end);
            stn.sum = stn.left.sum + stn.right.sum;
        }
        return stn;
    }

    /**
     * Update the value of an index of array on tree
     * @param index
     * @param val
     */
    public void update(int index, int val){
        updateTree(root, index, val);
    }
    private void updateTree(SegmentTreeNode root, int index, int val){
        if(root==null) return;
        if(root.start==index && root.end==index){
            root.sum = val;
        }else{
            int mid = root.start + (root.end-root.start)/2;
            if(index<=mid){
                updateTree(root.left, index, val);
            }else{
                updateTree(root.right, index, val);
            }
            root.sum = root.left.sum + root.right.sum;
        }
    }

    /**
     * Find the sum in the tree
     * @param start
     * @param end
     * @return
     */
    public int rangeSum(int start, int end){
        return findSum(root, start, end);
    }
    private int findSum(SegmentTreeNode root, int start, int end){
        if(start>end) return 0;
        if(start > root.end || end < root.start) return 0;
        if(root.start==start && root.end==end) return root.sum;
        int mid = root.start + (root.end-root.start)/2;
        if(end<=mid){
            return findSum(root.left, start, end);
        }else if(start>mid){
            return findSum(root.right, start, end);
        }else{
            return findSum(root.left, start, mid) + findSum(root.right, mid+1, end);
        }
    }


    public static void main(String[] args){
        int[] arr = {0,9,5,7,3};
        SegmentTree tree = new SegmentTree(arr);
        System.out.println(tree.rangeSum(4,4));
    }


}
