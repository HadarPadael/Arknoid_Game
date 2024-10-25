package Animation;
import GameInterfaces.Animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Hadar Padael.
 * 212916753
 * a class which defines the AnimationRunner type.
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;

    /**
     * constructor method.
     * @param gui GUI.
     * @param framesPerSecond int.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        //INITIALIZE GUI AND SLEEPER/
        this.gui = gui;
        this.sleeper = new Sleeper();
        this.framesPerSecond = framesPerSecond;
    }

    // Run the game -- start the animation loop.

    /**
     * a method for running the game altogether.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);
            gui.show(d);

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }


}
