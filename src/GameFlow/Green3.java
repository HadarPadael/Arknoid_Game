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
 * a class which defines the third level of the game.
 */
public class Green3 implements LevelInformation {
    /**
     * getter method
     * @return int numberOfBalls.
     */
    @Override
    public int numberOfBalls() {
        return 2;
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
        return 10;
    }
    /**
     * getter method
     * @return int paddleWidth.
     */
    @Override
    public int paddleWidth() {
        return 80;
    }
    /**
     * getter method
     * @return String levelName.
     */
    @Override
    public String levelName() {
        return "Green 3";
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
        for(int j = 10, coordinate = 100, color = 0; j > 5; --j) {
            Block[] blocksToAdd = (initializeRowsOfBlocks(j, coordinate, color));
            addBlocksToList(blocksToAdd, blocks);
            coordinate += 20;
            ++color;
        }
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
        return 40;
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
                d.setColor(new Color(0, 204, 0));
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(Color.white);
                d.fillRectangle(50, 450, 100, 250);
                d.setColor(Color.black);
                //horizontal/
                for (int i = 0; i < 10; ++i) {
                    d.fillRectangle(50, 450 + (i * 20), 100, 8);
                }
                //vertical/
                for (int i = 0; i < 6; ++i) {
                    d.fillRectangle(50 + (i * 20) , 450, 8, 250);
                }
                d.setColor(new Color(51, 51, 51));
                d.fillRectangle(90,390,30, 60);
                d.setColor(new Color(102, 102, 102));
                d.fillRectangle(100,220,10, 170);

                d.setColor(new Color(255, 204, 51));
                d.fillCircle(105, 210, 10);
                d.setColor(Color.RED);
                d.fillCircle(105, 210, 8);
                d.setColor(Color.white);
                d.fillCircle(105, 210, 4);
            }

            @Override
            public void timePassed() { }
        };
    }

    /**
     * a method which initializes rows of blocks.
     * @param j int.
     * @param coordinate int.
     * @param color int.
     */
    public Block[] initializeRowsOfBlocks(int j, int coordinate, int color) {
        //CREATE ROWS OF BLOCKS IN DESCENDING ORDER/
        Color[] colors = {Color.GRAY, Color.red, Color.yellow, Color.blue, Color.pink};
        Block[] row = new Block[j];
        ArrayList<Block> arrayOfRow = new ArrayList<>(0);
        for (int i = 0; i < j; ++i) {
            row[i] = new Block(new Geometrics.Rectangle(new Point((730 - i * 50), coordinate), 50, 20),
                    colors[color]);
            row[i].initializeArrayOfBlock(arrayOfRow);
            arrayOfRow.add(row[i]);
        }
        return row;
    }
}
