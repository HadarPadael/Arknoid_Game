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
 * a class which defines the forth level of the game.
 */
public class FinalHour implements LevelInformation {
    /**
     * getter method
     * @return int numberOfBalls.
     */
    @Override
    public int numberOfBalls() {
        return 3;
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
        return 12;
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
        return "Level Name: Final Hour";
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
        for(int i = 0, coordinate = 100, color = 0, j = 15; i < 7; ++i) {
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
        return 100;
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
     * @param j int.
     * @param coordinate int.
     * @param color int.
     */
    public Block[] initializeRowsOfBlocks(int j, int coordinate, int color) {
        //CREATE ROWS OF BLOCKS IN DESCENDING ORDER/
        Color[] colors = {Color.yellow, Color.white, Color.cyan, Color.green, Color.magenta, Color.blue, Color.orange};
        Block[] row = new Block[j];
        ArrayList<Block> arrayOfRow = new ArrayList<>(0);
        for (int i = 0; i < j; ++i) {
            row[i] = new Block(new Geometrics.Rectangle(new Point((725 - i * 50), coordinate), 50, 20),
                    colors[color]);
            row[i].initializeArrayOfBlock(arrayOfRow);
            arrayOfRow.add(row[i]);
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
                d.setColor(new Color(51, 204, 255));
                d.fillRectangle(0, 0, 800, 600);
                for (int i = 0; i < 400; ++i) {
                    d.setColor(new Color(51, 153,255));
                    if (i < 200 && i > 100) {
                        d.setColor(new Color(0, 0, 204));
                    } else if (i > 200){
                        d.setColor(new Color(0, 0,153));
                    }
                    d.drawLine(20, (i), 380 - (i), 600);
                }
                for (int i = 0; i < 400; ++i) {
                    d.setColor(new Color(51, 153,255));
                    if (i < 200 && i > 100) {
                        d.setColor(new Color(0, 0, 204));
                    } else if (i > 200) {
                        d.setColor(new Color(0, 0,153));
                    }
                    d.drawLine(780, (i), 380 + (i), 600);
                }

                d.setColor(new Color(255, 255, 153));
                d.drawLine(610, 390, 650, 500 );
                d.drawLine(610, 390, 630, 520 );
                d.drawLine(610, 390, 550, 540 );

                d.drawLine(190, 390, 230, 500 );
                d.drawLine(190, 390, 210, 520 );
                d.drawLine(190, 390, 130, 540 );

                d.setColor(Color.lightGray);
                d.fillCircle(580, 400, 30);
                d.setColor(Color.gray);
                d. fillCircle(610, 390, 40);
                d.setColor(new Color(102, 102, 102));
                d.fillCircle(650, 400, 30);
                d.fillCircle(630, 420, 20);

                d.setColor(Color.lightGray);
                d.fillCircle(220, 400, 30);
                d.setColor(Color.gray);
                d. fillCircle(190, 390, 40);
                d.setColor(new Color(102, 102, 102));
                d.fillCircle(150, 400, 30);
                d.fillCircle(170, 420, 20);


            }

            @Override
            public void timePassed() { }
        };
    }
}
