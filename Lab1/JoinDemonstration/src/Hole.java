import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Hole {
    public int size;
    public volatile int  x, y;
    public Position position;
    private final BallCanvas canvas;
    public Hole(Position pos, BallCanvas c, int s){
        position = pos;
        canvas = c;
        size = s;
    }

    public synchronized void updateCoordinates(){
        int maxX = canvas.getWidth();
        int maxY = canvas.getHeight();
        switch (position) {
            case N -> {
                x = maxX / 2 - size / 2;
                y = -size / 5;
            }
            case S -> {
                x = maxX / 2 - size / 2;
                y = maxY - size / 5 * 4;
            }
            case W -> {
                x = -size / 5;
                y = maxY / 2 - size / 2;
            }
            case E -> {
                x = maxX - size / 5 * 4;
                y = maxY / 2 - size / 2;
            }
            case NW -> {
                x = -size / 5;
                y = -size / 5;
            }
            case NE -> {
                x = maxX - size / 5 * 4;
                y = -size / 5;
            }
            case SW -> {
                x = -size / 5;
                y = maxY - size / 5 * 4;
            }
            case SE -> {
                x = maxX - size / 5 * 4;
                y = maxY - size / 5 * 4;
            }
            case C -> {
                x = maxX / 2 - size / 2;
                y = maxY / 2 - size / 2;
            }
            default -> {
                x = 0;
                y = 0;
            }
        }
    }

    public void draw (Graphics2D g2){
        g2.setColor(Color.darkGray);
        g2.fill(new Ellipse2D.Double(x,y,size,size));
    }

    public enum Position {
        N, S, W, E, NW, NE, SW, SE, C
    }
}
