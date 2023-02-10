import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class BallCanvas extends JPanel {
    private volatile ArrayList<Ball> balls = new ArrayList<>();

    public void add(Ball b){
        this.balls.add(b);
    }
    public synchronized void removeBall(Ball b){
        this.balls.remove(b);
    }
    @Override
    public synchronized void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.darkGray);
        g2.fill(new Ellipse2D.Double(-20, -20, 100, 100));
        for (Ball b : balls) {
            b.draw(g2);
        }
    }
}