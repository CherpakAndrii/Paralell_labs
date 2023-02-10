import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BounceFrame extends JFrame {
    private final BallCanvas canvas;
    public static final int WIDTH = 900;
    public static final int HEIGHT = 700;
    public static HoleHittingCounter holeHittingCounter;

    private ArrayList<BallThread> threads = new ArrayList<>();
    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce programm");
        this.canvas = new BallCanvas();
        ArrayList<Hole> holes = new ArrayList<>();
        holes.add(new Hole(Hole.Position.NW, canvas, 50));
        holes.add(new Hole(Hole.Position.NE, canvas, 50));
        holes.add(new Hole(Hole.Position.SW, canvas, 50));
        holes.add(new Hole(Hole.Position.SE, canvas, 50));
        this.canvas.initializeHoles(holes);
        //System.out.println("In Frame Thread name = " + Thread.currentThread().getName());
        Container content = this.getContentPane();
        JPanel counterPanel = new JPanel();
        JLabel counterLabel = new JLabel("Balls in hole: 0");
        holeHittingCounter = new HoleHittingCounter(counterLabel);
        counterPanel.add(counterLabel);
        JButton ClearCounterButton = new JButton("Clear");
        ClearCounterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                holeHittingCounter.clear();
            }
        });
        counterPanel.add(ClearCounterButton);
        counterPanel.setBackground(Color.lightGray);
        content.add(counterPanel, BorderLayout.NORTH);
        content.add(this.canvas, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton buttonStart = new JButton("Start");
        JButton buttonStart10 = new JButton("Start 10");
        JButton buttonSpeedUp = new JButton("Speed Up");
        JButton buttonStop = new JButton("Stop");
        /*JButton buttonStart100 = new JButton("Start 100");
        JButton buttonStart1000 = new JButton("Start 1000");
        JButton buttonStart10000 = new JButton("Start 10000");*/
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Ball blueBall = new Ball(canvas, Ball.BallColor.BLUE);
                Ball redBall = new Ball(canvas, Ball.BallColor.RED);
                canvas.add(blueBall);
                canvas.add(redBall);

                BallThread blueBallThread = new BallThread(blueBall, holeHittingCounter);
                DependentBallThread redBallThread = new DependentBallThread(redBall, holeHittingCounter, blueBallThread);
                threads.add(blueBallThread);
                threads.add(redBallThread);
                redBallThread.start();
                blueBallThread.start();
            }
        });
        buttonStart10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball ball = new Ball(canvas, Ball.BallColor.BLUE);
                canvas.add(ball);
                BallThread ballThread = new BallThread(ball, holeHittingCounter);
                threads.add(ballThread);
                ballThread.start();
                BallThread prev = ballThread;
                for (int i = 1; i < 10; i++){
                    ball = new Ball(canvas, i%2==0? Ball.BallColor.BLUE: Ball.BallColor.RED);
                    canvas.add(ball);
                    ballThread = new DependentBallThread(ball, holeHittingCounter, prev);
                    threads.add(ballThread);
                    ballThread.start();
                    prev = ballThread;
                }
            }
        });
        buttonSpeedUp.addActionListener(new ActionListener() {
            @Override
            public synchronized void actionPerformed(ActionEvent e) {
                for (int i = 0; i < threads.size(); i++){
                    if (threads.get(i).getState() == Thread.State.TERMINATED){
                        threads.remove(i);
                        i--;
                    }
                    else if (threads.get(i).getState() == Thread.State.RUNNABLE || threads.get(i).getState() == Thread.State.TIMED_WAITING) {
                        threads.get(i).delay = 1;
                        // threads.get(i).b.SpeedUp();
                    }
                }
            }
        });
        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (BallThread thread: threads){
                    thread.interrupt();
                }
                threads.clear();
                canvas.repaint();
            }
        });

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStart10);
        buttonPanel.add(buttonSpeedUp);
        buttonPanel.add(buttonStop);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void updateHolesCoordinates(){
        canvas.updateHolesCoordinates();
}
}