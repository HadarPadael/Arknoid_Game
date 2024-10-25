package GameFlow;
import Animation.AnimationRunner;
import Animation.EndScreen;
import GameInterfaces.LevelInformation;
import Trackers.Base.Counter;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 *  @author Hadar Padael.
 *  212916753
 * a class which is in charge of running the game in a fluent way.
 */
public class GameFlow {
   private final AnimationRunner animationRunner;
   private final KeyboardSensor keyboardSensor;
   private final GUI gui;
   private final Counter score;

    /**
     * constructor method.
     */
    public GameFlow() {
        this.gui = new GUI("My kick-ass assignment", 800, 600);
        this.animationRunner = new AnimationRunner(gui, 60);
        this.keyboardSensor = gui.getKeyboardSensor();
        this.score = new Counter(0);
    }

    /**
     * a method which tha loops through the levels of the game.
     * @param levels List of levels.
     */
    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.gui, score);
            level.initialize(800, 600, 20,  15);

           while (level.getBallsCounter().getValue() > 0 && level.getBlocksCounter().getValue() > 0) {
               level.run();
           }

            if (level.getBallsCounter().getValue() == 0) {
                animationRunner.run(new EndScreen(keyboardSensor,
                        "Game Over. Your score is" +  " " + score.getValue()));
                gui.close();
            }
        }
        animationRunner.run(new EndScreen(keyboardSensor, "You Win! Your score is" + " " + score.getValue()));
        gui.close();
    }
}