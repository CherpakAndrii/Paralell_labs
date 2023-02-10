import java.awt.*;
import java.awt.geom.Ellipse2D;

class Ball {
    private final Component canvas;
    public final BallType type;
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private int x = 0;
    private int y= 0;
    private int dx = 2;
    private int dy = 2;


    public Ball(Component c, BallType type){
        this.canvas = c;
        this.type = type;

        /*if(Math.random()<0.5){
            x = new Random().nextInt(this.canvas.getWidth());
            y = 0;
        }else{
            x = 0;
            y = new Random().nextInt(this.canvas.getHeight());
        }*/
        x = 100;
        y = 100;
    }

    public void draw (Graphics2D g2){
        if (type == BallType.HIGH_PRIORITY)
        {
            g2.setColor(Color.red);
        }
        else{
            g2.setColor(Color.blue);
        }
        g2.fill(new Ellipse2D.Double(x,y,XSIZE,YSIZE));

    }

    public void move(){
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
}

enum BallType{
    NORMAL,
    HIGH_PRIORITY
}