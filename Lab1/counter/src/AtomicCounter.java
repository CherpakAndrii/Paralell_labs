import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter implements ICounter {
    protected AtomicInteger value = new AtomicInteger(0);
    public void increment() {
        value.incrementAndGet();
    }
    public void decrement() {
        value.decrementAndGet();
    }
    public int getValue() {
        return value.intValue();
    }
}
