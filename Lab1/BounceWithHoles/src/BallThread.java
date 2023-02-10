import java.time.Duration;
import java.time.Instant;

public class BallThread extends Thread {
    private final Ball b;
    private HoleHittingCounter c;

    public BallThread(Ball ball, HoleHittingCounter counter){
        b = ball;
        c = counter;
    }
    @Override
    public void run(){
        try{
            //Instant start = Instant.now();
            while (true){
                b.move();
                if (b.checkForHittingTheHole(-20, -20)){
                    break;
                }
                // System.out.println("Thread name = " + Thread.currentThread().getName());
                Thread.sleep(5);
            }
            this.c.increment();
            b.dispose();

            //Instant finish = Instant.now();
            //long timeElapsed = Duration.between(start, finish).toMillis();
            //System.out.println("Time elapsed: " + timeElapsed);
        } catch(InterruptedException ignored){

        }
    }
}