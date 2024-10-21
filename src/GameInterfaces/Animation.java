package GameInterfaces;
import biuoop.DrawSurface;

/**
 * @author Hadar Padael.
 * 212916753
 * an interface for Animation.
 */
public interface Animation {
    /**
     * a method which describes what will happen in a frame.
     * @param d DrawSurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * a method which returns a boolean value, according to which the animation will proceed or stop.
     * @return boolean value.
     */
    boolean shouldStop();
}