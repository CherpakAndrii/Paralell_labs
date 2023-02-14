public class SymbolsOutput {
    public static void main(String[] args) {
        Console console = new Console();
        SymbolOutputThread thread1 = new SymbolOutputThread(100000, '|', false, console);
        SymbolOutputThread thread2 = new SymbolOutputThread(100000, '-', true, console);

        thread1.start();
        thread2.start();
    }
}