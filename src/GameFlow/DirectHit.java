package GameFlow;
import GameInterfaces.LevelInformation;
import GameInterfaces.Sprite;
import GameObjects.Block;
import GameObjects.Features.Velocity;
import Geometrics.Rectangle;
import Geometrics.Point;
import biuoop.DrawSurface;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *  @author Hadar Padael.
 *  212916753
 * a class which defines the first level of the game.
 */
public class DirectHit implements LevelInformation {

    /**
     * getter method
     * @return int numberOfBalls.
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * getter method
     * @return List of Velocities of balls.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new LinkedList<>();
        for (int i = 0; i < this.numberOfBalls(); ++i) {
            velocities.add(new Velocity(0, -generateVelocities()[1]));
        }
        return velocities;
    }

    /**
     * getter method
     * @return int paddleSpeed.
     */
    @Override
    public int paddleSpeed() {
        return 6;
    }

    /**
     * getter method
     * @return int paddleWidth.
     */
    @Override
    public int paddleWidth() {
        return 100;
    }

    /**
     * getter method
     * @return String levelName.
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * getter method
     * @return Sprite background.
     */
    @Override
    public Sprite getBackground() {
        return defineBackgroundSprite();
    }

    /**
     * getter method
     * @return List of Blocks.
     */
    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks =  new ArrayList<>();
        Block block = new Block(new Rectangle(new Point(385,135), 30, 30), Color.red);
        block.initializeArrayOfBlock( new ArrayList<>(0));
        blocks.add(block);
        return blocks;
    }

    /**
     * getter method
     * @return int numberOfBlocks.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    /**
     * a method which generates random velocities for the balls.
     * @return int[] velocities.
     */
    public int[] generateVelocities() {
        Random rand = new Random();
        int[] velocities = {rand.nextInt(14) - 7, rand.nextInt(14) - 7};
        while(velocities[0] == velocities[1] || velocities[0] == 0 || velocities[1] == 0
                || velocities[1] < 0) {
            velocities[0] = rand.nextInt(14) - 7;
            velocities[1] = rand.nextInt(14) - 7;
        }
        return velocities;
    }

    /**
     * a method which defines all details regarding the level background.
     * @return Sprite background.
     */
    public Sprite defineBackgroundSprite() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.black);
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(Color.blue);
                for (int i = 0; i < 3; ++i) {
                    d.drawCircle(400, 150, 45 * (1 + i));
                }
                d.drawLine(200, 150, 600, 150);
                d.drawLine(400, 0, 400, 300);
            }

            @Override
            public void timePassed() { }
        };
    }
}
