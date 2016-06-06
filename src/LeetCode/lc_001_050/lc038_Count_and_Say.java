package LeetCode.lc_001_050;

/**
 * Created by ytliu on 6/5/16.
 */
public class lc038_Count_and_Say {
    public static void main(String[] args) {
        System.out.println(countAndSay(1)); //1
        System.out.println(countAndSay(2)); //11
        System.out.println(countAndSay(3)); //21
        System.out.println(countAndSay(4)); //1211
    }
    public static String countAndSay(int n) {
        String from = "1";
        for(int i=1;i<n;i++){
            StringBuilder stringBuilder = new StringBuilder();
            int count = 0;
            char pre = from.charAt(0);
            for(char c : from.toCharArray()){
                if(c==pre) count++;
                else{
                    stringBuilder.append((char)('0'+count));
                    stringBuilder.append(pre);
                    count = 1;
                }
                pre = c;
            }
            stringBuilder.append((char)('0'+count));
            stringBuilder.append(pre);
            from = stringBuilder.toString();
        }
        return from;
    }
}
