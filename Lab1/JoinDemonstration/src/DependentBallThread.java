public class DependentBallThread extends BallThread
{
    private final BallThread parentThread;
    public DependentBallThread(Ball ball, HoleHittingCounter counter, BallThread parent)
    {
        super(ball, counter);
        parentThread = parent;
    }

    @Override
    public void run(){
        try{
            parentThread.join();
            super.run();
        } catch(InterruptedException ignored){
            super.interrupt();
        }
        finally {
            b.dispose();
        }
    }

}
