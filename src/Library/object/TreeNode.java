package Library.object;

/**
 * Created by Tin on 6/16/16.
 */
public class TreeNode<T> {
    public T value;
    public TreeNode<T> left = null;
    public TreeNode<T> right = null;
    public TreeNode(T value){
        this.value = value;
    }
    @Override
    public String toString(){
        return value.toString();
    }
}
