import java.time.Duration;
import java.time.Instant;

public class BallThread extends Thread {
    public final Ball b;
    protected HoleHittingCounter c;
    public volatile int delay = 5;

    public BallThread(Ball ball, HoleHittingCounter counter){
        b = ball;
        c = counter;
    }
    @Override
    public synchronized void run(){
        try{
            //Instant start = Instant.now();
            while (true){
                b.move();
                if (b.checkForHittingHoles()){
                    break;
                }
                // System.out.println("Thread name = " + Thread.currentThread().getName());
                Thread.sleep(delay);
            }
            this.c.increment();
            b.dispose();

            //Instant finish = Instant.now();
            //long timeElapsed = Duration.between(start, finish).toMillis();
            //System.out.println("Time elapsed: " + timeElapsed);
        } catch(InterruptedException ignored){

        }
        finally {
            b.dispose();
        }
    }
}