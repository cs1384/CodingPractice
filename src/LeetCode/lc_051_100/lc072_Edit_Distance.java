package LeetCode.lc_051_100;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 3/28/16.
 */
public class lc072_Edit_Distance {
    public static void main(String[] args) {
//        System.out.println(minDistance("ebf", "abe"));
        System.out.println(minDistance("", "a"));
//        System.out.println(minDistance("zozo", "zozo"));
//        System.out.println(minDistance("zoologicoarchaeologist", "zoogeologist"));
//        System.out.println(minDistance("pneumonoultramicroscopicsilicovolcanoconiosis", "ultramicroscopically"));
    }
    public static int minDistance2(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] cost = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++)
            cost[i][0] = i;
        for(int i = 1; i <= n; i++)
            cost[0][i] = i;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(word1.charAt(i) == word2.charAt(j))
                    cost[i + 1][j + 1] = cost[i][j];
                else {
                    int a = cost[i][j];
                    int b = cost[i][j + 1];
                    int c = cost[i + 1][j];
                    cost[i + 1][j + 1] = a < b ? (a < c ? a : c) : (b < c ? b : c);
                    cost[i + 1][j + 1]++;
                }
            }
        }
        Printer.printMatrix(cost);
        return cost[m][n];
    }
    public static int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        //each cell stores the edit distance between word1.substring(0,i) and word2.substring(0,j)
        int[][] dp = new int[len1+1][len2+1];
        for(int j=0;j<=len2;j++) dp[0][j] = j;
        for(int i=0;i<=len1;i++) dp[i][0] = i;
//        Printer.printMetrix(dp);
        for(int charIndex1=0;charIndex1<len1;charIndex1++){
            for(int charIndex2=0;charIndex2<len2;charIndex2++){
                if(word1.charAt(charIndex1) == word2.charAt(charIndex2))
                    dp[charIndex1+1][charIndex2+1] = dp[charIndex1][charIndex2];
                else {
                    int before = Math.min(dp[charIndex1][charIndex2], Math.min(dp[charIndex1][charIndex2+1], dp[charIndex1+1][charIndex2]));
                    dp[charIndex1+1][charIndex2+1] = before + 1;
                }
//                System.out.println("\""+word1.substring(0,i+1) +"\" to \""+word2.substring(0,j+1)+"\"");
//                System.out.println("====> dp["+i+"]["+j+"]="+dp[i][j]);
            }
//            Printer.printMetrix(dp);
        }
        return dp[len1][len2];
    }
}
