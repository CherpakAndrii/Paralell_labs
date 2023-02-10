import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BounceFrame extends JFrame {
    private final BallCanvas canvas;
    public static final int WIDTH = 900;
    public static final int HEIGHT = 700;
    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program: Thread priority test");
        this.canvas = new BallCanvas();
        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton buttonStart100 = new JButton("Start 100");
        JButton buttonStart1000 = new JButton("Start 1000");
        JButton buttonStart10000 = new JButton("Start 10000");
        JButton buttonStop = new JButton("Stop");
        buttonStart100.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Ball b = new Ball(canvas, BallType.HIGH_PRIORITY);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.start();

                for (int i = 0; i < 100; i++){
                    b = new Ball(canvas, BallType.NORMAL);
                    canvas.add(b);

                    thread = new BallThread(b);
                    thread.start();
                    // System.out.println("Thread name = " + thread.getName());
                }
            }
        });
        buttonStart1000.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 1000; i++){
                    Ball b = new Ball(canvas, BallType.NORMAL);
                    canvas.add(b);

                    BallThread thread = new BallThread(b);
                    thread.start();
                    // System.out.println("Thread name = " + thread.getName());
                }
                Ball b = new Ball(canvas, BallType.HIGH_PRIORITY);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.start();
            }
        });
        buttonStart10000.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 10000; i++){
                    Ball b = new Ball(canvas, BallType.HIGH_PRIORITY);
                    canvas.add(b);

                    BallThread thread = new BallThread(b);
                    thread.start();
                    // System.out.println("Thread name = " + thread.getName());
                }
                Ball b = new Ball(canvas, BallType.NORMAL);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.start();
            }
        });
        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        buttonPanel.add(buttonStart100);
        buttonPanel.add(buttonStart1000);
        buttonPanel.add(buttonStart10000);
        buttonPanel.add(buttonStop);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}