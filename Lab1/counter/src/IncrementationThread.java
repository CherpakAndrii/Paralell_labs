public class IncrementationThread extends CounterThread {
    public IncrementationThread(ICounter counter, int iterations) {
        super(counter, iterations);
    }
    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {
            counter.increment();
        }
    }
}
