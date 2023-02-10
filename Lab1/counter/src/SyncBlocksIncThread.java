public class SyncBlocksIncThread extends IncrementationThread {
    public SyncBlocksIncThread(ICounter counter, int iterations){
        super(counter, iterations);
    }

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {
            synchronized (counter){
                counter.increment();
            }
        }
    }
}
