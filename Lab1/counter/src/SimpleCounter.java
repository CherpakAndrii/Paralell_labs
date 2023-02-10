public class SimpleCounter implements ICounter{
    protected Integer value = 0;
    public void increment() {
        value++;
    }
    public void decrement() {
        value--;
    }
    public int getValue() {
        return value;
    }
}
