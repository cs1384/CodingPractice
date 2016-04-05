package LeetCode.lc_201_250;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Created by ytliu on 4/3/16.
 */
public class lc207_Course_Schedule {
    public static void main(String[] args) {
        System.out.println(canFinish2(10, new int[][]{{5,8},{3,5},{1,9},{4,5},{0,2},{1,9},{7,8},{4,9}}));

    }
    public static boolean canFinish2(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> courseToPrerequisites = new HashMap<>();
        int[] requiredByHowMany = new int[numCourses];
        for(int[] prerequisite : prerequisites){
            int course = prerequisite[0];
            int require = prerequisite[1];
            if(!courseToPrerequisites.containsKey(course))
                courseToPrerequisites.put(course, new LinkedList<>());
            courseToPrerequisites.get(course).add(require);
            requiredByHowMany[require]++;
        }

        Queue<Integer> notBeingRequired = new LinkedList<>();
        for(int i=0;i<requiredByHowMany.length;i++){
            if(requiredByHowMany[i]==0) notBeingRequired.offer(i);
        }

        int numCourseFinished = 0;
        while(!notBeingRequired.isEmpty()){
            int course = notBeingRequired.poll();
            numCourseFinished++;
            if(!courseToPrerequisites.containsKey(course)) continue;
            for(int require : courseToPrerequisites.get(course)){
                if(--requiredByHowMany[require]==0){
                    notBeingRequired.add(require);
                }
            }
        }
        return numCourseFinished==numCourses;
    }

    // previous version by my own, DFS
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
        // map each course to its prerequisites
        for(int i=0;i<prerequisites.length;i++){
            int course = prerequisites[i][1];
            if(!map.containsKey(course)){
                map.put(course, new HashSet<Integer>());
            }
            map.get(course).add(prerequisites[i][0]);
        }
        int[] visited = new int[numCourses];
        for(int key : map.keySet()){
            if(!canFinish_DFS(map, visited, key)) return false;
        }
        return true;
    }
    private boolean canFinish_DFS(Map<Integer, Set<Integer>> map, int[] visited, int course){
        // the course has been explored, which means it can
        if(visited[course]==1) return true;
        // the course is being explored, which means we are visiting it twice and thus form a circle
        if(visited[course]==-1) return false;
        // if there's no prerequisites for this course
        if(!map.containsKey(course)) return true;
        // start exploring
        visited[course] = -1;
        for(int c : map.get(course)){
            // if any of its prerequisites forms a circle, return false
            if(!canFinish_DFS(map, visited, c)) return false;
        }
        // finish exploring
        visited[course] = 1;
        return true;
    }
}
