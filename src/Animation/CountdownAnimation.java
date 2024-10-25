package Animation;
import GameCollections.SpriteCollection;
import GameInterfaces.Sprite;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import GameInterfaces.Animation;
import java.awt.*;

// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.

/**
 *  @author Hadar Padael.
 *  212916753
 * a class for countdown animation before each level.
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private int countFrom;
    private final SpriteCollection gameScreen;
    private boolean stop = false;
    private final Sprite background;

    /**
     * constructor method.
     *
     * @param numOfSeconds double.
     * @param countFrom    int.
     * @param gameScreen   SpriteCollection.
     * @param background   Sprite.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, Sprite background) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.background = background;
    }

    /**
     * a method which describes what will happen in a frame of countdown.
     * @param d DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        background.drawOn(d);
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.white);
        Sleeper sleeper = new Sleeper();
        do {
            if (countFrom == 0) {
                d.drawText((d.getWidth() / 2) - 150, (d.getHeight() / 2) + 20, "GO!", 200);
            } else if (countFrom == -1) {
                this.stop = true;
            } else {
                d.drawText((d.getWidth() / 2) - 30, (d.getHeight() / 2) + 20, String.valueOf(countFrom), 200);
            }
            --countFrom;
            sleeper.sleepFor((long) (numOfSeconds * 500));
        } while (countFrom < -2);

    }

    /**
     * a method which returns a boolean value, according to which the animation will proceed or stop.
     * @return stop Boolean.
     */
    public boolean shouldStop() {
        return this.stop;
    }

}