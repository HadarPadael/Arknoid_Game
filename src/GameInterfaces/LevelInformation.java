package GameInterfaces;
import GameObjects.Block;
import GameObjects.Features.Velocity;
import java.util.List;

/**
 * @author Hadar Padael.
 * 212916753
 * an interface for LevelInformation.
 */
public interface LevelInformation {
    /**
     * getter.
     * @return numberOfBalls.
     */
    int numberOfBalls();
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()

    /**
     * getter.
     * @return  List<Velocity>.
     */
    List<Velocity> initialBallVelocities();

    /**
     * getter.
     * @return paddleSpeed
     */
    int paddleSpeed();

    /**
     * getter.
     * @return paddleWidth
     */
    int paddleWidth();

    // the level name will be displayed at the top of the screen.

    /**
     * getter.
     * @return levelName.
     */
    String levelName();

    // Returns a sprite with the background of the level

    /**
     * getter.
     * @return getBackground.
     */
    Sprite getBackground();
    // The Blocks that make up this level, each block contains
    // its size, color and location.

    /**
     * getter.
     * @return List<Block>.
     */
    List<Block> blocks();
    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();

    /**
     * getter.
     * @return numberOfBlocksToRemove.
     */
    int numberOfBlocksToRemove();
}