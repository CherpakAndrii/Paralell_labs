import java.util.concurrent.atomic.AtomicBoolean;

public class SymbolOutputThread extends Thread {
    private final int count;
    private final char symbol;
    private final boolean expectedFlagValue;
    private final AtomicBoolean flag;

    public SymbolOutputThread(int count, char symbol, AtomicBoolean flag, boolean flagValue) {
        this.count = count;
        this.symbol = symbol;
        expectedFlagValue = flagValue;
        this.flag = flag;
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < count; i++) {
            while (flag.get() != expectedFlagValue) {
                Thread.yield();
            }
            System.out.print(symbol);
            flag.set(!expectedFlagValue);
        }
    }
}
