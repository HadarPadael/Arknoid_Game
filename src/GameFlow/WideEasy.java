package GameFlow;
import GameInterfaces.LevelInformation;
import GameInterfaces.Sprite;
import GameObjects.Block;
import GameObjects.Features.Velocity;
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
 * a class which defines the second level of the game.
 */
public class WideEasy implements LevelInformation {
    /**
     * getter method
     * @return int numberOfBalls.
     */
    @Override
    public int numberOfBalls() {
        return 10;
    }
    /**
     * getter method
     * @return List of Velocities of balls.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new LinkedList<>();
        for (int i = 0; i < this.numberOfBalls(); ++i) {
            velocities.add(new Velocity(generateVelocities()[0], -generateVelocities()[1]));
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
        return 500;
    }
    /**
     * getter method
     * @return String levelName.
     */
    @Override
    public String levelName() {
        return "Wide Easy";
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
        Block[] blocksToAdd = (initializeRowsOfBlocks());
        ArrayList<Block> blocks = new ArrayList<>();
        addBlocksToList(blocksToAdd, blocks);
        return blocks;
    }

    /**
     * a method which adds the blocks to the list of blocks.
     * @param blocksToAdd Block[].
     * @param blocks ArrayList<Block>.
     */
    public void addBlocksToList(Block[] blocksToAdd, ArrayList<Block> blocks) {
        for (int i = 0; i < blocksToAdd.length; ++i) {
            blocks.add(blocksToAdd[i]);
        }
    }
    /**
     * getter method
     * @return int numberOfBlocks.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 15;
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
     * a method which initializes rows of blocks.
     */
    public Block[] initializeRowsOfBlocks() {
        //CREATE ROWS OF BLOCKS IN DESCENDING ORDER/
        Color[] colors = {Color.red, Color.orange, new Color(102, 0, 153), Color.green,
                Color.blue, Color.pink, Color.magenta};
        Block[] row = new Block[15];
        ArrayList<Block> arrayOfRow = new ArrayList<>(0);
        for (int i = 0, j = 0; i < 15; ++i) {
            if (i % 2 != 0) {
                --j;
            } else if (j == 7) {
                --j;
            }
            row[i] = new Block(new Geometrics.Rectangle(new Point((725 - i * 50), 200),
                    50, 20), colors[j]);
            row[i].initializeArrayOfBlock(arrayOfRow);
            arrayOfRow.add(row[i]);
            ++j;
        }
        return row;
    }
    /**
     * a method which defines all details regarding the level background.
     * @return Sprite background.
     */
    public Sprite defineBackgroundSprite() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(51,204,255));
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(Color.YELLOW);
                for (int i = 0; i < 140; ++i) {
                    d.drawLine(170, 110, 20 + (i * 5), 200);
                }
                d.setColor(new Color(255, 255, 204));
                d.fillCircle(170, 110, 60);
                d.setColor(Color.yellow);
                d.fillCircle(170, 110, 50);
                d.setColor(new Color(255, 255, 153));
                d.fillCircle(170, 110, 40);
            }

            @Override
            public void timePassed() { }
        };
    }
}
