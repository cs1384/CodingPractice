package LeetCode.lc_051_100;

import LeetCode.util.Printer;
import com.sun.tools.javac.util.StringUtils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Tin on 6/17/16.
 */
public class lc071_Simplify_Path {
    public static void main(String[] args) {
        System.out.println(simplifyPath("/a/./b/../../c/")); // "/c"
        System.out.println(simplifyPath("/home/"));// "/home"
        System.out.println(simplifyPath("/home")); // "/home"
        System.out.println(simplifyPath("/..")); // "/"
        System.out.println(simplifyPath("/abc/...")); // "/abc/..."
    }
    public static String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] dirs = path.split("/");
        for(String dir : dirs){
            if(dir.isEmpty() || dir.equals(".")){
                // do nohting
            }else if(dir.equals("..")){
                if(!stack.isEmpty()) stack.pop();
            }else{
                stack.push(dir);
            }
        }
        if(stack.isEmpty()) return "/";
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.insert(0,"/"+stack.pop());
        }
        return sb.toString();
    }
}
