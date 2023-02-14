import java.lang.reflect.InvocationTargetException;

public class RunCounterOperationsThread extends Thread{
    private ICounter counter;
    private IncrementationThread incThread;
    private DecrementationThread decThread;
    private String message;
    public RunCounterOperationsThread(
            Class<? extends ICounter> counterClass,
            Class<? extends IncrementationThread> incrementationThreadClass,
            Class<? extends DecrementationThread> decrementationThreadClass,
            int numOfIterations,
            String msg)
    {
        try {
            message = msg;
            counter = counterClass.getDeclaredConstructor().newInstance();
            Class[] cArgs = new Class[2];
            cArgs[0] = ICounter.class;
            cArgs[1] = int.class;
            incThread = incrementationThreadClass.getDeclaredConstructor(cArgs).newInstance(counter, numOfIterations);
            decThread = decrementationThreadClass.getDeclaredConstructor(cArgs).newInstance(counter, numOfIterations);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException ignored) {
            System.out.println(ignored.toString());
        }
    }

    @Override
    public void run() {
        try {
            super.run();
            incThread.start();
            decThread.start();

            incThread.join();
            decThread.join();
            System.out.println(message + counter.getValue());
        }
        catch (InterruptedException ignored){
            System.out.println(ignored.toString());
        }
    }
}
