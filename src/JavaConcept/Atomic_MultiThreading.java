package JavaConcept;

/**
 * Created by Tin on 7/6/16.
 */

/**
 * wait(), notify(), notifyAll()
 * http://www.tutorialspoint.com/java/java_multithreading.htm
 * producer-consumer problem
 * http://www.tutorialspoint.com/javaexamples/thread_procon.htm
 *
 */
public class Atomic_MultiThreading {
    /*
    a++/++a is not an atomic operation. That means it can not guarantee to be
    done without interruption.
    long and double is not
    http://stackoverflow.com/questions/16729364/atomic-operations-and-multithreading
     */
    interface AtomicTest{
        void incrA();
        void decrA();
        int getValue();
        void setValue(int value);
    }
    static class AssignSelfChange implements AtomicTest {
        int value = 0;
        /**
         * 1. take value out and return value
         * 2. change value to value+1
         * 3. assign value to returning value
         * 4. nothing change
         */
        @Override
        public void incrA(){ value = value++; }
        @Override
        public void decrA(){ value = value--; }
        @Override
        public int getValue(){ return value; }
        @Override
        public void setValue(int value) { this.value = value; }
    }
    static class ChangeSelf implements AtomicTest{
        int value = 0;
        @Override
        public void incrA(){ value++; }
        @Override
        public void decrA(){ value--; }
        @Override
        public int getValue(){ return value; }
        @Override
        public void setValue(int value) { this.value = value; }
    }
    static class AssignSelfChange_ThreadSafe implements AtomicTest{
        int value = 0;
        @Override
        synchronized public void incrA(){ value = value++; }
        @Override
        synchronized public void decrA(){ value = value--; }
        @Override
        public int getValue(){ return value; }
        @Override
        synchronized public void setValue(int value) { this.value = value; }
    }
    static class ChangeSelf_ThreadSafe implements AtomicTest{
        int value = 0;
        @Override
        synchronized public void incrA(){ value++; }
        @Override
        synchronized public void decrA(){ value--; }
        @Override
        public int getValue(){ return value; }
        @Override
        synchronized public void setValue(int value) { this.value = value; }
    }

    static class MyThreadIncr extends Thread {
        AtomicTest atomicTest;
        MyThreadIncr(String name, AtomicTest atomicTest){
            this.atomicTest = atomicTest;
            setName(name);
        }
        @Override
        public void run() {
//            synchronized (atomicTest) { //fix1 for problem2
                atomicTest.setValue(0);
                for (int j = 0; j < 1000000; j++) atomicTest.incrA();
                System.out.println(this.getName() + " finished -> " + atomicTest.getValue());
//            if(atomicTest.getValue()!=0) System.out.println("===== not zero =====");
//            }
        }
    }
    static class MyThreadDecr extends Thread {
        AtomicTest atomicTest;
        MyThreadDecr(String name, AtomicTest atomicTest){
            this.atomicTest = atomicTest;
            setName(name);
        }
        @Override
        public void run() {
            for (int j = 0; j < 1000000; j++) atomicTest.decrA();
            System.out.println(this.getName()+" finished -> "+atomicTest.getValue());
        }
    }

    public static void main(String[] args) throws InterruptedException {

//        testAtomic(new AssignSelfChange()); //problem1
//        testAtomic(new ChangeSelf()); //fix for problem1

//        testSingleThreadStart(new AssignSelfChange());//problem2
//        fix1 is in the Thread implementation
//        testSingleThreadJoin(new AssignSelfChange()); //fix2 for the problem2

        /*
        This fix does not work for problem2 since the threads can still change the value
         */
//        testSingleThreadStart(new AssignSelfChange_ThreadSafe());
//        testSingleThreadStart(new ChangeSelf_ThreadSafe());//problem2

//        testTwoThreadsJoin(new ChangeSelf());
        testTwoThreadsJoin(new ChangeSelf_ThreadSafe());
    }
    public static void testSingleThreadStart(AtomicTest atomicTest) throws InterruptedException {
        for(int i=0;i<100;i++) {
//            synchronized (new Object()) {
                atomicTest.setValue(0);
                Thread thread1 = new MyThreadIncr("Thread-" + i, atomicTest);
                thread1.start();
//            }
        }
        System.out.println("testSingleThreadStart() FINISHED");
    }
    public static void testSingleThreadJoin(AtomicTest atomicTest) throws InterruptedException {
        for(int i=0;i<100;i++) {
            atomicTest.setValue(0);
            Thread thread1 = new MyThreadIncr("Thread-"+i, atomicTest);
            thread1.start();
            thread1.join();
            System.out.println("Main Finished -> "+atomicTest.getValue());
//            if(atomicTest.getValue()!=0) System.out.println("===== not zero =====");
        }
        System.out.println("testSingleThreadJoin() FINISHED");
    }
    public static void testTwoThreadsJoin(AtomicTest atomicTest) throws InterruptedException {
        for(int i=0;i<1000;i++) {
            atomicTest.setValue(0);
            Thread thread1 = new MyThreadIncr("Thread1-"+i, atomicTest);
            Thread thread2 = new MyThreadDecr("Thread2-"+i, atomicTest);
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            System.out.println("Main Finished -> "+atomicTest.getValue());
        }
    }
    public static void testAtomic(AtomicTest atomicTest){
        for(int i=0;i<1000000;i++) atomicTest.incrA();
        System.out.println(atomicTest.getValue());
    }
}
