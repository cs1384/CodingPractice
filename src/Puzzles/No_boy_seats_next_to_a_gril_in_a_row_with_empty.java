package Puzzles;

import java.util.LinkedList;
import java.util.Queue;

import LeetCode.util.Printer;

/**
 * Created by ytliu on 5/9/16.
 */
public class No_boy_seats_next_to_a_gril_in_a_row_with_empty {
    /*
    He tells the class members to all sit in the same
row, but none of the boys want to sit next to any girls.
If there are 7 boys, 6 girls, and 19 seats in the row,
how many ways can the class arrange themselves so
that no boy is sitting adjacent to a girl?
     */
    public static void main(String[] args) {
        /*
            Small case 1: 2 boys, 2 girls and 5 seats
            Small case 2: 2 boys, 2 girls and 6 seats
         */

        /*
        Thought 1:
        P(19,13) - C(7,1)C(6,1)C(18,1)C(2,1)P(17,11)
        all_permutation - choose_one_boy * choose_one_girl * chose_one_pairing_seats * switch_seat * rest_people_permutation
        Not working because for example in small case 1, binding B1G1 together has one combination as same as one for binding B2G2
        -> duplicate
         */

        /*
        Thought 2:
        Pick the slot in between empties to fill in boys or girls, for small case 2, we can get answer 48 by bruteForce()
        ? E ? E ?
        C(3,1)C(1,1)2! * ( C(2,1)C(1,1)2! + C(2,2)C(1,1)2! ) = 6 * (4+2) = 36
        choose_one_slot_for_boys*one_way_to_cut_them*ways_to_arrange_them * ways_to_fill_up_girls
        C(3,2)C(1,1)2! * C(1,1)C(1,1)2! = 6*2 = 12

         */

        //coding solutions
        /*
        Find all permutations and check if it follows the conditions, too slow
         */
//        bruteForce();
        /*
        Build the possible arrangement
         */
        simplifiedWork();

    }
    public static void simplifiedWork(){
        totalWays = 0L;
        rowSeats = 6;
        helper(0, 'E', 2, 2, 2, 1L);
        System.out.println(totalWays);

        totalWays = 0L;
        rowSeats = 19;
        helper(0, 'E', 7, 6, 6, 1L);
        System.out.println(totalWays);
    }
    static long totalWays = 0L;
    static int rowSeats = 19;
    private static void helper(int position, char pre, int boys, int girls, int empties, long ways){
        if(position==rowSeats){
//            totalWays++;
            totalWays += ways;
            return;
        }
        switch (pre){
            case 'E':
                if(empties>0) helper(position+1, 'E', boys, girls, empties-1, ways*1);
                if(boys>0) helper(position+1, 'B', boys-1, girls, empties, ways*boys);
                if(girls>0) helper(position+1, 'G', boys, girls-1, empties, ways*girls);
                break;
            case 'B':
                if(empties>0) helper(position+1, 'E', boys, girls, empties-1, ways*1);
                if(boys>0) helper(position+1, 'B', boys-1, girls, empties, ways*boys);
                break;
            case 'G':
                if(empties>0) helper(position+1, 'E', boys, girls, empties-1, ways*1);
                if(girls>0) helper(position+1, 'G', boys, girls-1, empties, ways*girls);
                break;
        }
    }

    public static void bruteForce(){
        Queue<String> people = new LinkedList<String>();
        people.offer("G1");
        people.offer("G2");
//        people.offer("G3");
//        people.offer("G4");
//        people.offer("G5");
//        people.offer("G6");
        people.offer("B1");
        people.offer("B2");
//        people.offer("B3");
//        people.offer("B4");
//        people.offer("B5");
//        people.offer("B6");
//        people.offer("B7");

        String[] seats = new String[seatNum];
        int empty = seatNum-people.size();
        printout(people, seats, 0, empty);
        System.out.println(count);
        System.out.println(noAdjacent);
    }
    static int count = 0;
    static int noAdjacent = 0;
    static int seatNum = 5;
    public static void printout(Queue<String> people, String[] seats, int position, int empty){
        if(position==seats.length){
            Printer.printArray(seats);
            if(!adjacent(seats)){
                System.out.println("^^^^^^^^^^^^^");
                noAdjacent++;
            }
            count++;
            return;
        }
        int i = 0;
        int top = people.size();
        while(i<top){
            String p = people.poll();
            Queue<String> queue = new LinkedList<String>(people);
            seats[position] = p;
            printout(queue, seats, position+1, empty);
            people.offer(p);
            i++;
        }
        if(empty>0){
            seats[position] = "__";
            printout(people, seats, position+1, empty-1);
        }
    }
    private static boolean adjacent(String[] seat){
        for(int i=0;i<seat.length;i++){
            if(isBoy(seat[i])){
                if(i>0 && isGirl(seat[i-1])) return true;
                if(i<seat.length-1 && isGirl(seat[i+1])) return true;
            }
        }
        return false;
    }
    private static boolean isBoy(String s){
        return s.contains("B");
    }
    private static boolean isGirl(String s){
        return s.contains("G");
    }
}
