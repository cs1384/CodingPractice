package LeetCode.lc_251_300;

/**
 * Created by Tin on 7/19/16.
 */
public class lc277_Find_the_Celebrity {
    /*
    http://blog.csdn.net/xudli/article/details/48749295
     */
    public static void main(String[] args) {
        called = 0;
        findCelebrity1(3);
        System.out.println(called+" calls");
        called = 0;
        findCelebrity(3);
        System.out.println(called+" calls");
    }
    static int called = 0;
    public static int findCelebrity(int n) {
        called = 0;
        int[] tracker = new int[n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j || tracker[j]==-1) continue;
                if(know(i,j)){
                    tracker[i] = -1;
                    tracker[j]++;
                }else{
                    tracker[j] = -1;
                }
            }
        }
        for(int i=0;i<n;i++){
            if(tracker[i]==n-1){
                int celebrity = i;
                for(int j=0;j<n;j++){
                    if(i==j) continue;
                    if(know(i,j)) celebrity = -1;
                }
                if(celebrity!=-1) return celebrity;
            }
        }
        return -1;
    }
    /*
    call n^2 times
     */
    public static int findCelebrity1(int n) {
        int[] tracker = new int[n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j) continue;
                if(know(i,j)){
                    tracker[i] = -1;
                    if(tracker[j]!=-1) tracker[j]++;
                }else{
                    tracker[j] = -1;
                }
            }
        }
        for(int i=0;i<n;i++){
            if(tracker[i]==n-1) return i;
        }
        return -1;
    }
    public static boolean know(int a, int b){
        called++;
        if(a==0 && b==1) return true;
        if(a==0 && b==2) return false;
        if(a==1 && b==0) return false;
        if(a==1 && b==2) return false;
        if(a==2 && b==0) return true;
        if(a==2 && b==1) return true;
        return true;
    }
}
