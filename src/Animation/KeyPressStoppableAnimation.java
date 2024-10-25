package Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import GameInterfaces.Animation;

/**
 *  @author Hadar Padael.
 *  212916753
 * a class which defines the KeyPressStoppable object. Is using decorator pattern.
 */
public abstract class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor sensor;
    private boolean stop;
    private final String message;
    private boolean isAlreadyPressed;

    /**
     * constructor method.
     * @param sensor KeyboardSensor.
     * @param message String.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor,String message) {
        this.sensor = sensor;
        this.message = message;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * a method which describes what will happen in a frame.
     * @param d DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, message,  32);
        if (this.sensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            if(!isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            isAlreadyPressed = false;
        }
    }
    /**
     * a method which returns a boolean value, according to which the animation will proceed or stop.
     * @return stop Boolean.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
