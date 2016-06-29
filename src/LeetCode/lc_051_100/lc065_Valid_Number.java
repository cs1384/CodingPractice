package LeetCode.lc_051_100;

/**
 * Created by Tin on 6/16/16.
 */
public class lc065_Valid_Number {
    public static void main(String[] args) {
        //requirement
        // sign/none integer/none '.' integer/none, cant be both none
        // sign/none integer/decimal 'e' sign/none integer

        //general
        System.out.println(isNumber(" ")+" is f");
        System.out.println(isNumber(". 1")+" is f");
        // decimal
        System.out.println(isNumber(".")+" is f");
        // scientific
        System.out.println(isNumber("e")+" is f");
        System.out.println(isNumber("0e")+" is f");
        System.out.println(isNumber("3.09e")+" is f");

        // decimal
        System.out.println(isNumber(".1")+" is t");
        System.out.println(isNumber("3.")+" is t");
        // scientific
        System.out.println(isNumber("005047e-6")+" is t");
        System.out.println(isNumber("+005047e-6")+" is t");

    }
    /*
    requirement:
    sign/none integer/none '.' integer/none, cant be both none in later two parts
    sign/none integer/decimal 'e' sign/none integer
     */
    public static boolean isNumber(String s) {
        s = s.trim().toLowerCase();
        if(s.length()==0) return false; // nothing is not a number

        //should be shown in order
        boolean signed = false;
        boolean digitBefore = false;
        boolean decimal = false;
        //boolean digitAfter = false;
        boolean scientific = false;
        boolean digitAfter = false;

        for(char c : s.toCharArray()){
            if(!Character.isDigit(c)){
                if(c=='e'){
                    if(scientific) return false; // e shows before
                    if(!decimal && !digitBefore) return false; // not decimal but no digitBefore
                    // decimal before e, validate this decimal reset everything for scientific
                    else{
                        //
                        if(!digitBefore && !digitAfter) return false;
                        digitBefore = true;
                        digitAfter = false; // re-gether digitAfter
                        decimal = false; // close decimal mode to scientific mode
                        signed = false; // scientific allows second sign
                    }
                    scientific = true;
                }else if(c=='.'){
                    // can not happen after assure scientific
                    if(scientific || decimal) return false;
                    decimal = true;
                }else if(c=='+' || c=='-'){
                    if(scientific){
                        if(digitAfter || signed) return false;
                    }else if(digitBefore || digitAfter || decimal || signed) return false;
                    signed = true;
                }else{
                    // space in not allowed as well
                    return false;
                }
            }else{
                if(decimal || scientific) digitAfter = true;
                else digitBefore = true;
            }
        }
        // post checks
        if(scientific && !digitAfter) return false;
        if(decimal && (!digitBefore && !digitAfter)) return false;
        return true;
    }
}
