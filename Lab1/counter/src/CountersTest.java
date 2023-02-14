import java.util.ArrayList;

public class CountersTest {
    public static void main(String[] args) {
        final int numberOfIterations = Integer.MAX_VALUE-1;

        ArrayList<RunCounterOperationsThread> threads = new ArrayList<>();
        threads.add(new RunCounterOperationsThread(
                SimpleCounter.class,
                IncrementationThread.class,
                DecrementationThread.class,
                numberOfIterations,
                "Simple counter: "));
        threads.add(new RunCounterOperationsThread(
                AtomicCounter.class,
                IncrementationThread.class,
                DecrementationThread.class,
                numberOfIterations,
                "Atomic counter: "));
        threads.add(new RunCounterOperationsThread(
                SimpleCounter.class,
                SyncBlocksIncThread.class,
                SyncBlocksDecThread.class,
                numberOfIterations,
                "Synchronized block counter: "));
        threads.add(new RunCounterOperationsThread(
                SyncMethodsCounter.class,
                IncrementationThread.class,
                DecrementationThread.class,
                numberOfIterations,
                "Synchronized method counter: "));
        threads.add(new RunCounterOperationsThread(
                LockedCounter.class,
                IncrementationThread.class,
                DecrementationThread.class,
                numberOfIterations,
                "Locked counter: "));

        for (RunCounterOperationsThread t : threads) { t.start(); }
        try
        {
            for (RunCounterOperationsThread t : threads) { t.join(); }
        }
        catch (InterruptedException ignored){
            System.out.println(ignored.toString());
        }
    }
}