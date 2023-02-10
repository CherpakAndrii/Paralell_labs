public class CountersTest {
    public static void main(String[] args) {
        final int numberOfIterations = 10000; //Integer.MAX_VALUE-1;
        ICounter counter = new SimpleCounter();
        IncrementationThread incThread = new IncrementationThread(counter, numberOfIterations);
        DecrementationThread decThread = new DecrementationThread(counter, numberOfIterations);
        runTest(counter, incThread, decThread);

        counter = new SimpleCounter();
        incThread = new SyncBlocksIncThread(counter, numberOfIterations);
        decThread = new SyncBlocksDecThread(counter, numberOfIterations);
        runTest(counter, incThread, decThread);

        counter = new SyncMethodsCounter();
        incThread = new IncrementationThread(counter, numberOfIterations);
        decThread = new DecrementationThread(counter, numberOfIterations);
        runTest(counter, incThread, decThread);

        counter = new LockedCounter();
        incThread = new IncrementationThread(counter, numberOfIterations);
        decThread = new DecrementationThread(counter, numberOfIterations);
        runTest(counter, incThread, decThread);
    }

    public static void runTest(ICounter c, IncrementationThread i, DecrementationThread d)
    {
        try{
            i.start();
            d.start();

            i.join();
            d.join();
            System.out.println(c.getValue());
        }
        catch (InterruptedException ignored){ }
    }
}