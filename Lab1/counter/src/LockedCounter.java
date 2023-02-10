import java.util.concurrent.locks.ReentrantLock;

public class LockedCounter implements ICounter{
    protected Integer value = 0;
    ReentrantLock lock = new ReentrantLock();
    public void increment() {
        lock.lock();
        try{
            value++;
        }
        finally {
            lock.unlock();
        }
    }
    public void decrement() {
        lock.lock();
        try{
            value--;
        }
        finally {
            lock.unlock();
        }
    }
    public int getValue() {
        return value;
    }
}
