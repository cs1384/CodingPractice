package LeetCode.lc_001_050;

/**
 * Created by ytliu on 3/25/16.
 */
public class lc006_ZigZag_Conversion {
    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING",3));
        System.out.println(convert("AB",1));

    }
    public static String convert(String s, int numRows) {
        if(numRows==1) return s;
        StringBuilder[] stringBuilders = new StringBuilder[numRows];
        for(int i=0;i<numRows;i++) stringBuilders[i] = new StringBuilder();
        int index = 0;
        boolean up = true;
        for(char c : s.toCharArray()){
            stringBuilders[index].append(c);
            if(up){
                if(index==numRows-1){
                    up = false;
                    index--;
                }else{
                    index++;
                }
            }else{
                if(index==0){
                    up = true;
                    index++;
                }else{
                    index--;
                }
            }
        }
        StringBuilder res = new StringBuilder();
        for(int i=0;i<numRows;i++) res.append(stringBuilders[i].toString());
        return res.toString();
    }

}
