import java.time.Duration;
import java.time.Instant;

public class BallThread extends Thread {
    private final Ball b;

    public BallThread(Ball ball){
        b = ball;
        setPriority(b.type == BallType.HIGH_PRIORITY ? Thread.MAX_PRIORITY : Thread.MIN_PRIORITY);
    }
    @Override
    public void run(){
        try{
            Instant start = Instant.now();
            for(int i=1; i<10000; i++){
                b.move();
                // System.out.println("Thread name = " + Thread.currentThread().getName());
                Thread.sleep(5);

            }

            Instant finish = Instant.now();
            long timeElapsed = Duration.between(start, finish).toMillis();
            System.out.println("Time elapsed: " + timeElapsed + ", Priority: " + (b.type==BallType.HIGH_PRIORITY ? "High" : "Low"));
        } catch(InterruptedException ignored){

        }
    }
}