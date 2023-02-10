import javax.swing.*;

public class HoleHittingCounter {
    public volatile int value;
    public final JLabel counterLabel;
    public HoleHittingCounter(JLabel ctrLabel){
        value = 0;
        counterLabel = ctrLabel;
    }
    public synchronized void increment(){
        value++;
        counterLabel.setText(this.toString());
    }

    public synchronized void clear(){
        value = 0;
        counterLabel.setText(this.toString());
    }

    @Override
    public String toString() {
        return "Balls in hole: " + value;
    }
}
