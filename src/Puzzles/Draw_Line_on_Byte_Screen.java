package Puzzles;

/**
 * Created by Tin on 7/19/16.
 */
public class Draw_Line_on_Byte_Screen {
    public static void main(String[] args) {
        byte[] screen = {0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,75};
        printScreen(screen, 24);
        System.out.println("==== Draw line ====");
        drawLine(screen, 24, 3, 17, 2);
        printScreen(screen, 24);
    }
    public static void drawLine(byte[] screen, int width, int x1, int x2, int y){
        int rowLen = width/8;
        int firstIndex = rowLen*y+x1/8;
        for(int i=7-x1%8+1;i>=0;i--){
            byte mask = (byte)(1<<i);
            screen[firstIndex] |= mask;
        }
        int lastIndex = rowLen*y+x2/8;
        for(int i=firstIndex+1;i<lastIndex;i++){
            byte mask = -1;
            screen[i] |= mask;
        }
        for(int i=7;i>7-x2%8;i--){
            byte mask = (byte)(1<<i);
            screen[lastIndex] |= mask;
        }
    }
    public static void printScreen(byte[] screen, int width){
        int rowLen = width/8;
        for(int i=0;i<screen.length;i++){
            if(i!=0 && i%rowLen==0) System.out.println();
            for(int j=7;j>=0;j--){
                byte mask = (byte)(1<<j);
                byte pixels = screen[i];
                pixels &= mask;
                if(pixels==0) {
                    System.out.print(".");
                }else{
                    System.out.print("o");
                }
            }
        }
        System.out.println();
    }
}
