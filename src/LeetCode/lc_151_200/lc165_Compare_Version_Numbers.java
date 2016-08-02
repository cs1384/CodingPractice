package LeetCode.lc_151_200;

/**
 * Created by Tin on 7/29/16.
 */
public class lc165_Compare_Version_Numbers {
    public static void main(String[] args) {
//        System.out.println(compareVersion("1.2", "13.37")); //-1
        System.out.println(compareVersion("01", "1")); //0
//        System.out.println(compareVersion("1.1", "1.10")); //-1
//        System.out.println(compareVersion("1.2", "1.10")); //-1
//        System.out.println(compareVersion("1.0", "1")); //0
    }
    public static int compareVersion(String version1, String version2) {
        int i = 0, j = 0;
        int len1 = version1.length(), len2 = version2.length();
        while(i<len1 || j<len2){
            int num1 = 0;
            while(i<len1 && version1.charAt(i)!='.'){
                num1 = num1*10+(version1.charAt(i++)-'0');
            }
            int num2 = 0;
            while(j<len2 && version2.charAt(j)!='.'){
                num2 = num2*10+(version2.charAt(j++)-'0');
            }
            if(num1>num2) return 1;
            else if(num1<num2) return -1;
            i++;j++; //skip '.'
        }
        return 0;
    }
    /*
    Use of parseInt()
     */
    public static int compareVersion2(String version1, String version2) {
        String[] strings1 = version1.split("\\.");
        String[] strings2 = version2.split("\\.");
        int len = Math.max(strings1.length, strings2.length);
        for(int i=0;i<len;i++){
            int num1 = i<strings1.length?Integer.parseInt(strings1[i]):0;
            int num2 = i<strings2.length?Integer.parseInt(strings2[i]):0;
            if(num1>num2) return 1;
            else if(num1<num2) return -1;
        }
        return 0;
    }
    /*
    Assume the version number could be not an integer
     */
    public static int compareVersion1(String version1, String version2) {
        String[] strings1 = version1.split("\\.");
        String[] strings2 = version2.split("\\.");
        int op = 0;
        for(;op<strings1.length&&op<strings2.length;op++){
            int i = 0, j = 0;
            String s1 = strings1[op], s2 = strings2[op];
            int len1 = s1.length(), len2 = s2.length();
            while(i+1<len1 && s1.charAt(i)=='0') i++;
            while(j+1<len2 && s2.charAt(j)=='0') j++;
            if(len1-i!=len2-j) return len1-i>len2-j?1:-1;
            while(i<s1.length() && j<s2.length()){
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(j);
                if(c1!=c2){
                    if(c1>c2) return 1;
                    else return -1;
                }
                i++;j++;
            }
        }
        if(strings1.length==strings2.length) return 0;
        String[] strings = strings1.length>strings2.length?strings1:strings2;
        while(op<strings.length){
            String s = strings[op++];
            int i = 0, len = s.length();
            while(i+1<len && s.charAt(i)=='0') i++;
            if(i==len-1 && s.charAt(i)=='0') continue;
            else return strings1.length>strings2.length?1:-1;
        }
        return 0;
    }

}
