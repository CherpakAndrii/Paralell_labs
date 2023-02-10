import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

class Ball {
    private final BallCanvas canvas;
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private int x;
    private int y;
    protected volatile int dx = 2;
    protected volatile int dy = 2;
    private BallColor color;


    public Ball(BallCanvas c, BallColor color){
        this.canvas = c;
        this.color = color;
        if(Math.random()<0.5){
            x = new Random().nextInt(this.canvas.getWidth());
            y = 0;
        }else{
            x = 0;
            y = new Random().nextInt(this.canvas.getHeight());
        }
    }

    public void draw (Graphics2D g2){
        g2.setColor(color == BallColor.BLUE? Color.blue : Color.red);
        g2.fill(new Ellipse2D.Double(x,y,XSIZE,YSIZE));
    }

    public void dispose(){
        this.canvas.removeBall(this);
        this.canvas.repaint();
    }

    public boolean checkForHittingHoles(){
        for (Hole h : this.canvas.holes) {
            if (checkForHittingTheHole(h)){
                return true;
            }
        }
        return false;
    }
    private boolean checkForHittingTheHole(Hole hole){
        return x >= hole.x && x <= hole.x + hole.size && y >= hole.y && y <= hole.y + hole.size;
    }

    public synchronized void move(){
        x+=dx;
        y+=dy;
        if(x<0){
            x = 0;
            dx = -dx;
        }
        if(x+XSIZE>=this.canvas.getWidth()){
            x = this.canvas.getWidth()-XSIZE;
            dx = -dx;
        }
        if(y<0){
            y=0;
            dy = -dy;
        }
        if(y+YSIZE>=this.canvas.getHeight()){
            y = this.canvas.getHeight()-YSIZE;
            dy = -dy;
        }
        this.canvas.repaint();
    }

    public synchronized void SpeedUp()
    {
        dx *= 5;
        dy *= 5;
    }

    public enum BallColor {
        RED, BLUE
    }
}