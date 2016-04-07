package LeetCode.lc_201_250;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 4/4/16.
 */
public class lc210_Course_Schedule_II {
    public static void main(String[] args) {
//        Printer.printArray(findOrder(2, new int[][]{{1,0}}));
//        Printer.printArray(findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}}));
        //        Printer.printArray(findOrder(2, new int[][]{{0,1},{1,0}}));
//        Printer.printArray(findOrder(4, new int[][]{{0,1},{1,2},{2,3},{3,1}}));
        Printer.printArray(findOrder(8, new int[][]{{1,0},{2,6},{1,7},{5,1},{6,4},{7,0},{0,5},{5,1},{6,4}}));
    }
    static class Course{
        int number;
        int visitStatus;
        boolean required;
        List<Course> requires;
        Course(int number){
            this.number = number;
            visitStatus = -1;
            requires = new LinkedList<>();
            required = false;
        }
    }
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Course> courses = new LinkedList<>();
        for(int i=0;i<numCourses;i++) courses.add(new Course(i));

        for(int[] prerequisite : prerequisites){
            int courseNumber = prerequisite[0];
            int requireCourse = prerequisite[1];
            courses.get(requireCourse).required = true;
            courses.get(courseNumber).requires.add(courses.get(requireCourse));
        }

        Queue<Course> stack = new LinkedList<>();
        for(Course course : courses){
            if(course.required) continue;
            if(!topologicalDFS(course, stack)) return new int[]{};
        }
        if(stack.size()!=numCourses) return new int[]{};

        int[] res = new int[numCourses];
        int i = 0;
        while(!stack.isEmpty()){
            res[i] = stack.poll().number;
            i++;
        }
        return res;
    }
    private static boolean topologicalDFS(Course course, Queue<Course> stack){
        course.visitStatus = 0;
//        System.out.println("start "+course.number);
        for(Course require : course.requires){
            switch (require.visitStatus){
                case 1:
                    break;
                case 0:
                    return false;
                case -1:
                    if(!topologicalDFS(require, stack)) return false;
                    break;
            }
        }
//        System.out.println("finish "+course.number);
        course.visitStatus = 1;
        stack.offer(course);
        return true;
    }

}
