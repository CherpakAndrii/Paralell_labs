import java.util.concurrent.atomic.AtomicBoolean;

public class SymbolsOutput {
    public static AtomicBoolean flag = new AtomicBoolean(false);
    public static void main(String[] args) {
        SymbolOutputThread thread1 = new SymbolOutputThread(100000, '|', flag,false);
        SymbolOutputThread thread2 = new SymbolOutputThread(100000, '-', flag,true);

        thread1.start();
        thread2.start();
    }
}