package Puzzles;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tin on 8/11/16.
 */
public class Min_Working_Time_for_Divisible_Robot {
    /*
    Problem Statement

You are given a list of tasks as an integer array, task_costs. Every i-th element of task_costs represents a task and requires task_costs[i] seconds to complete. All tasks listed in the array are independent of other tasks.

It is required to finish all the tasks independently and as soon as possible. You are given a single worker robot to start taking the tasks and finish them one at a time. However if you like, you can divide the worker robot in two. Each resulting robot can then be further divided into two and so on. There is a cost, in seconds, of dividing a robot in two, represented by robot_divide_cost.

You can assign an independent task to any available robot, however you can't interrupt or divide a robot while it is working on the assigned task. At the same time you can't assign a task to any robot while its in the process of getting divided.

To keep things simple you can't allow multiple robots to work on the same task. At any given time only one robot can work on a task and finish it. Once any particular robot finishes a task it can't be assigned any further tasks.

Given a list of tasks and cost of dividing a robot, find the least amount of time to finish all tasks.


Sample Input

2000,200,20,2
2

Sample Output

2002

Explanation

One possible way to finish all tasks in minimal time would be as follows:

We start with one robot and immediately split the robot in two (robot_A & robot_B) to start working on task 1 and task 2. Since the cost of dividing the robot is 2 seconds, we will have two robots working on first two tasks after 2 seconds. After 202 seconds from the start, robot_B will be finished and can be split again.

We split robot_B consuming another 2 seconds. At 204 seconds from start we get robot_C and immediately assign task 3 to robot_C. However we can't use robot_B again as it has already finished one task and have to split it again to get robot_D. At 206 seconds from start we get robot_D and assign it task 4.
     */
    public static void main(String[] args) {
        minTimeForDivisibleRobotToWork("2000",2);
        minTimeForDivisibleRobotToWork("2000,200,20,2",2);
        minTimeForDivisibleRobotToWork("1610,1612,1697,1703,1712,1911",100);
        minTimeForDivisibleRobotToWork("3101,3051,2297,2156,1861",3465);
        minTimeForDivisibleRobotToWork("1725,2375,544,990,1562,2146,1696,1200,1101,1349,780,998,621,1539,2400,2064,635,974,1863,1201,2706,2597,2291,1858,2330,2067,2643,1167,1592,2441,1929,1838,2404,785,792,1782,987,1493,2058,2122,784", 604);
        while(true) minTimeForDivisibleRobotToWorkSystemIn();
    }
//     1712,1911,1703,1610,1697,1612
//     1610,1612,1697,1703,1712,1911
//     100

//    time      0       100     200     300
//    use       0       0       0       6   -> 2211
//    total     1       2       4       8

//    time      0       100     200     300
//    use       0       0       1       5   -> 2111
//    total     1       2       4       7

//    time      0       100     200     300
//    use       0       0       2       4   -> 2111
//    total     1       2       4       6

//    time      0       100     200     300
//    use       0       1       0       5   -> 2012
//    total     1       2       3       6


//    3051,1861,3101,2156,2297
//    3101,3051,2297,2156,1861
//    3465

//    time    0       3465    6930    10395
//    use     0       0       0       5 -> 13446
//    total   1       2       4       8
//
//    time    0       3465    6930    10395
//    use     0       0       1       4 -> 13446
//    total   1       2       4       7
//
//    time    0       3465    6930    10395
//    use     0       0       2       3 -> 12692
//    total   1       2       4       6
//
//    time    0       3465    6930    10395
//    use     0       0       3       2 -> 12551
//    total   1       2       4       5
//
//    use     0       1       0       4 -> 13446
//    total   1       2       3       5
//
//    time    0       3465    6930    10395
//    use     0       1       1       3 -> 12692
//    total   1       2       3       5
    public static void minTimeForDivisibleRobotToWork(String string, int divCost){
        String[] tasks = string.split(",");
        int numTasks = tasks.length;
        int[] taskCost = new int[numTasks];
        for(int i=0;i<numTasks;i++) taskCost[i] = Integer.parseInt(tasks[i]);
        // cache based on [index][numDivisions][availableMachines]
        int[][][] cache = new int[numTasks][numTasks][numTasks];
        if(numTasks==1){
            System.out.println(taskCost[0]);
        }else {
            Arrays.sort(taskCost);
            System.out.println(minHelper(divCost, divCost, cache, taskCost, numTasks - 1, 2));
        }
    }
    private static int minHelper(int divCost, int divTotalCost, int[][][] cache,
                                 int[] taskCosts, int index, int available){
        // if available machine is more than tasks, working on all of them immediately is the fastest
        if(available>=index+1) return divTotalCost+taskCosts[index];
        if(cache[index][divTotalCost/divCost-1][available-1]!=0) return cache[index][divTotalCost/divCost-1][available-1];
        // use no machine in this level, usually the longest
        int res = minHelper(divCost, divTotalCost+divCost, cache, taskCosts, index, available*2);
        int levelCost = divTotalCost+taskCosts[index];
        int use = available-1; // start from using most since it is more likely to be the fastest
        while(use>0){
            int restCost = minHelper(divCost, divTotalCost+divCost, cache, taskCosts, index-use, (available-use)*2);
            res = Math.min(res, Math.max(levelCost, restCost));
            //the restCost will only be getting less and useless
            if(levelCost>restCost) break;
            use--;
        }
        cache[index][divTotalCost/divCost-1][available-1] = res;
        return res;
    }

    public static void minTimeForDivisibleRobotToWorkSystemIn(){
        Scanner scanner = new Scanner(System.in);
        String[] tasks = scanner.nextLine().split(",");
        int divCost = scanner.nextInt();
        int numTasks = tasks.length;
        int[] taskCost = new int[numTasks];
        for(int i=0;i<numTasks;i++) taskCost[i] = Integer.parseInt(tasks[i]);

        // cache based on [index][numDivisions][availableMachines]
        int[][][] cache = new int[numTasks][numTasks][numTasks];
        if(numTasks==1){
            System.out.println(taskCost[0]);
        }else {
            Arrays.sort(taskCost);
            System.out.println(minHelper(divCost, divCost, cache, taskCost, numTasks - 1, 2));
        }
    }

}
