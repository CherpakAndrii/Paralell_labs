import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class BallCanvas extends JPanel {
    private volatile ArrayList<Ball> balls = new ArrayList<>();
    public volatile ArrayList<Hole> holes;

    public void add(Ball b){
        this.balls.add(b);
    }
    public synchronized void removeBall(Ball b){
        this.balls.remove(b);
    }
    public synchronized void initializeHoles(ArrayList<Hole> holes){
        this.holes = holes;
    }
    public synchronized void updateHolesCoordinates(){
        for (Hole h : holes) {
            h.updateCoordinates();
        }
    }

    @Override
    public synchronized void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        for (Hole h : holes) {
            h.draw(g2);
        }
        for (Ball b : balls) {
            b.draw(g2);
        }
    }
}