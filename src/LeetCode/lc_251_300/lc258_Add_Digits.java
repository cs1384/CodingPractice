package LeetCode.lc_251_300;

public class lc258_Add_Digits {
    public static void main(String[] args) {
        lc258_Add_Digits addDigits = new lc258_Add_Digits();
        System.out.println(addDigits.addDigits(38));
    }

    public int addDigits(int num) {
        while(num/10 != 0) {
            int newNum = 0;
            do {
                newNum += num % 10;
                num /= 10;
            } while(num != 0);
            num = newNum;
        }
        return num;
    }
}
