package task_3;

import testTasks.TestMain;
import utilities.MyTimer;

import java.io.FileNotFoundException;

public class TestRunTimeMain {

    public static void main(String[] args) throws FileNotFoundException{
        int[] sizes = {5,10,20,40};
        runTest(1,sizes);
        TestMain.main(null);
    }
    private static void runHash(int iterations, int sizeOfPuzzle) throws FileNotFoundException {
        MyTimer timer = new MyTimer();
        timer.startTimer();
        for (int i = 0; i < iterations; i++){
            WordPuzzle wp = new WordPuzzle(sizeOfPuzzle);
        }
        timer.stopTimer();
        long averageTimeHash = timer.getTime()/iterations;
        System.out.println("AVERAGE RUNTIME FOR HASH, FOR SIZE "+sizeOfPuzzle+" is: "+averageTimeHash+" nanoseconds\n");

    }
    private static void runArray(int iterations, int sizeOfPuzzle) throws FileNotFoundException {
        MyTimer timer = new MyTimer();
        for (int i = 0; i < iterations; i++){
            WordPuzzle wp = new WordPuzzle(sizeOfPuzzle);
        }
        timer.stopTimer();
        long averageTimeArray = timer.getTime()/iterations;
        System.out.println("AVERAGE RUNTIME FOR ARRAY, FOR SIZE "+sizeOfPuzzle+" is: "+averageTimeArray+" nanoseconds\n");
    }
    private static void runTest(int iterations, int[] maxSizePuzzle) throws FileNotFoundException{
        for (int x: maxSizePuzzle){
            runHash(iterations,x);
        }
        for (int x: maxSizePuzzle){
            runArray(iterations,x);
        }
    }
}
