public abstract class CounterThread extends Thread
{
    protected final ICounter counter;
    protected int iterations;
    protected CounterThread(ICounter counter, int iterations) {
        this.counter = counter;
        this.iterations = iterations;
    }
}
