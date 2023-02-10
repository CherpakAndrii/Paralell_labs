public class SyncBlocksDecThread extends DecrementationThread{
    public SyncBlocksDecThread(ICounter counter, int iterations) {
        super(counter, iterations);
    }

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++){
            synchronized (counter){
                counter.decrement();
            }
        }
    }
}
