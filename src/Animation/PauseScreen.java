package Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import GameInterfaces.Animation;

/**
 * @author Hadar Padael.
 * 212916753
 * a class which defines the EndScreen.
 */
public class PauseScreen extends KeyPressStoppableAnimation implements Animation {
    /**
     * constructor method.
     * @param sensor keyboardSensor.
     * @param message String.
     */
    public PauseScreen(KeyboardSensor sensor,String message) {
        super(sensor, message);
    }
    /**
     * a method which describes what will happen in a frame of PauseScreen.
     * @param d DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        super.doOneFrame(d);
    }
    /**
     * a method which returns a boolean value, according to which the animation will proceed or stop.
     * @return stop Boolean.
     */
    public boolean shouldStop() {
        return super.shouldStop();
    }
}
