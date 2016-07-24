package JavaConcept;

/**
 * Created by Tin on 7/13/16.
 */
/*
http://javarevisited.blogspot.com/2011/08/enum-in-java-example-tutorial.html
 */
public class Enum {
    /*
    Can implements but not extends
     */
    enum Coin{ // implicitly extend Java.Lang.Enum
        QUATER(25), DIME(10), NICKEL(5), PENNY(1);

        int value;
        // need to be private, so no "new" for Enum
        private Coin(int value){ this.value = value; }
        @Override
        public String toString(){
            return this.name();
        }
    }
    enum Day{
        Monday, Thuesday, Wednesday, Thursday, Friday, Saturday, Sunday;


        Day next;
        static {
            Monday.next = Thuesday;
            Thuesday.next = Wednesday;
            Wednesday.next = Thursday;
            Thursday.next = Friday;
            Friday.next = Saturday;
            Saturday.next = Sunday;
            Sunday.next = Monday;
        }
        /*
        Self-defined method
         */
        public Day next(){
            return this.next;
        }

        public static void main(String[] args) {
            System.out.println("Can even have a main method.");
        }
    }

    enum Operation implements Runnable{
        PLUS {
            @Override
            public void run() {
                System.out.println("test");
            }
        }, MINUS {
            @Override
            public void run() {
                System.out.println("test");
            }
        }, TIMES {
            @Override
            public void run() {
                System.out.println("test");
            }
        }, DIVIDE {
            @Override
            public void run() {
                System.out.println("test");
            }
        };

        double calculate(double x, double y) {
            switch (this) {
                case PLUS: return x + y;
                case MINUS: return x - y;
                case TIMES: return x * y;
                case DIVIDE: return x / y;
            }
            return 0.0;
        }
    }
    /*
    Enum can be used to create singleton class becuase of its natures: it is static and final.
     And JVM gaurantee it is thread safe.
    http://stackoverflow.com/questions/4709175/what-are-enums-and-why-are-they-useful
     */
    enum SingletonClass{
        INSTANCE;
        int certainMethod(){
            return 10;
        }
    }

    public static void main(String[] args) {
        // loop
        System.out.println("Days in a week -> ");
        for(Day day : Day.values()) System.out.print(day + ", ");
        System.out.println();

        // self-defined method
        Day today = Day.Thursday;
        System.out.println("The day after "+today+" is "+(today.next));

        // switch
        for(Coin coin : Coin.values()){
            switch (coin){
                case QUATER:
                case DIME:
                    System.out.println(coin.name()+" is useful");
                    break;
                case NICKEL:
                case PENNY:
                    System.out.println(coin.name()+" is useless");
                    break;
            }
        }

        // equal operations
        for(Day day : Day.values()){
            if(day==Day.Saturday) System.out.println(day+" is in weekend.");
            if(day.equals(Day.Sunday)) System.out.println(day+" is also in weekend.");
        }

        // Convert a String to Enum object
        Operation operation = Operation.valueOf("MINUS");
        System.out.println(operation.calculate(10.0, 6.5));





    }
}
