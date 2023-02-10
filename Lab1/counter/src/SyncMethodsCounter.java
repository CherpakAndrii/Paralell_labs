public class SyncMethodsCounter implements ICounter
{
    private Integer value = 0;
    public synchronized void increment()
    {
        value++;
    }
    public synchronized void decrement()
    {
        value--;
    }
    public int getValue()
    {
        return value;
    }
}
