package LeetCode.lc_251_300;

/**
 * Created by Tin on 6/27/16.
 */
public class lc299_Bulls_and_Cows {
    public static void main(String[] args) {
        System.out.println(getHint("1807", "7810"));
        System.out.println(getHint("1123", "0111"));
        System.out.println(getHint("1122", "1222"));
    }
    public static String getHint(String secret, String guess) {
        int[] require = new int[10];
        int len = secret.length();
        int bull = 0, cow = 0;
        for(int i=0;i<len;i++){
            if(secret.charAt(i)==guess.charAt(i)) bull++;
            else{
                int sNum = secret.charAt(i)-'0';
                int gNum = guess.charAt(i)-'0';
                if(require[sNum]++<0) cow++;
                if(require[gNum]-->0) cow++;
            }
        }
        return bull+"A"+cow+"B";
    }
    public static String getHint1(String secret, String guess) {
        int[] map = new int[10];
        int len = secret.length();
        int bull = 0, cow = 0;
        for(int i=0;i<len;i++){
            if(secret.charAt(i)==guess.charAt(i)) bull++;
            else map[secret.charAt(i)-'0']++;
        }
        for(int i=0;i<len;i++){
            int digit = guess.charAt(i)-'0';
            if(digit!=secret.charAt(i)-'0' && map[digit]>0){
                cow++;
                map[digit]--;
            }
        }
        return bull+"A"+cow+"B";
    }
}
