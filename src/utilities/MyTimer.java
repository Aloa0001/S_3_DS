package utilities;

public class MyTimer {

    private long startTime, stopTime;

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        stopTime = System.nanoTime();
    }

    public long getTime() { return (stopTime - startTime);}

    public String toString() {
        return getTime() + " nanoseconds";
    }
}
