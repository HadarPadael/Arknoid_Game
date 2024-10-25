package GameFlow;
import GameCollections.GameEnvironment;
import GameCollections.SpriteCollection;
import GameInterfaces.Collidable;
import GameInterfaces.LevelInformation;
import GameInterfaces.Sprite;
import GameObjects.Ball;
import GameObjects.Block;
import GameObjects.Paddle;
import Geometrics.Point;
import Geometrics.Rectangle;
import Removers.BallRemover;
import Removers.BlockRemover;
import Trackers.Base.Counter;
import Trackers.ScoreIndicator;
import Trackers.ScoreTrackingListener;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import GameInterfaces.Animation;
import Animation.AnimationRunner;
import Animation.CountdownAnimation;
import Animation.PauseScreen;
import java.awt.Color;

/**
 * @author Hadar Padael.
 * 212916753
 * a class which manages the entire game - initializes and runs it.
 */
public class GameLevel implements Animation {
    //class properties:
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Counter blocksCounter;
    private final Counter ballsCounter;
    private final BlockRemover blockRemover;
    private final BallRemover ballRemover;
    private final Counter score;
    private final ScoreTrackingListener scoreTracker;
    private final ScoreIndicator indicator;
    private final AnimationRunner runner;
    private boolean running;
    private final GUI gui;
    private final biuoop.KeyboardSensor keyboard;
    private final LevelInformation levelInformation;
//constructor:
    /**
     * constructor method.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, GUI gui, Counter score) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.blocksCounter = new Counter(0);
        this.blockRemover = new BlockRemover(this, blocksCounter);
        this.ballsCounter = new Counter(0);
        this.ballRemover = new BallRemover(this, ballsCounter);
        this.score = score;
        this.scoreTracker = new ScoreTrackingListener(score);
        this.indicator = new ScoreIndicator(score, levelInformation.levelName());
        this.gui = gui;
        this.runner = animationRunner;
        keyboard = keyboardSensor;
        this.levelInformation = levelInformation;
    }

    /**
     * getter method.
     * @return Counter BlockCounter.
     */
    public Counter getBlocksCounter() {
        return blocksCounter;
    }
    /**
     * getter method.
     * @return Counter BallCounter.
     */
    public Counter getBallsCounter() {
        return ballsCounter;
    }
//other methods:
    /**
     * a method for adding a Collidable to game.
     * @param c Collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * a method for adding a Sprite to game.
     * @param s Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * a method for setting the games background.
     * @param d Surface.
     */
    public void setBackground(DrawSurface d) {
        levelInformation.getBackground().drawOn(d);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.

    /**
     * a method for initializing all Collidables of the game.
     * @param horizontalBound double.
     * @param verticalBound double.
     * @param marginsSize int.
     * @param paddleHeight int.
     */
    public void initialize(double horizontalBound, double verticalBound, int marginsSize, int paddleHeight) {

        initializeBalls(horizontalBound, verticalBound, marginsSize, paddleHeight);
        initializeMargins(horizontalBound, verticalBound, marginsSize);

        for (Block block : levelInformation.blocks()) {
            block.addToGame(this);
            block.addHitListener(this.blockRemover);
            block.addHitListener(this.scoreTracker);
            this.blocksCounter.increase(1);
        }
        //CREATE PADDLE/
        Paddle paddle = new Paddle(this.gui, new Rectangle(new Point(horizontalBound / 2
                - (levelInformation.paddleWidth() / 2.0), verticalBound - marginsSize - paddleHeight),
                levelInformation.paddleWidth(), paddleHeight), Color.ORANGE,
                marginsSize, levelInformation.paddleSpeed());
        //ADD PADDLE TO GAME/
        paddle.addToGame(this);
    }

    // Run the game -- start the animation loop.

    /**
     * a method for running the game altogether.
     */
    public void run() {
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(1, 3, sprites,
                levelInformation.getBackground()));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * a method for removing a Collidable from the game.
     * @param c Collidable.
     */
    public void removeCollidable(Collidable c) {
        environment.getEnvironment().remove(c);
    }
    /**
     * a method for removing a Sprite from the game.
     * @param s Sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.getSprites().remove(s);
    }



    /**
     * a method which initialized the balls.
     * @param horizontalBound double.
     * @param verticalBound double.
     * @param marginsSize int.
     * @param paddleHeight int.
     */
    public void initializeBalls(double horizontalBound, double verticalBound, int marginsSize, int paddleHeight) {
        //CREATE BALLS/
        Ball[] balls = new Ball[levelInformation.numberOfBalls()];
        for(int i = 0; i < levelInformation.numberOfBalls(); ++i) {
            balls[i] = new Ball(horizontalBound / 2 + (20 * i), verticalBound - marginsSize - paddleHeight - 8,
                    Color.white, 7, environment);
            balls[i].setVelocity(levelInformation.initialBallVelocities().get(i));
            balls[i].addToGame(this);
            this.ballsCounter.increase(1);
        }

    }

    /**
     * a method which initializes the margin blocks.
     * @param horizontalBound double.
     * @param verticalBound double.
     * @param marginsSize int.
     */
    public void initializeMargins(double horizontalBound, double verticalBound, int marginsSize) {
        //CREATE MARGIN BLOCKS/
        Block topMargin = new Block(new Rectangle(new Point(0, 0), horizontalBound, marginsSize),
                Color.lightGray);
        Block deathRegion = new Block(new Rectangle(new Point(0, verticalBound),
                horizontalBound - 2 * marginsSize, marginsSize), Color.lightGray);
        Block leftMargin = new Block(new Rectangle(new Point(0, marginsSize),
                marginsSize, verticalBound - marginsSize), Color.lightGray);
        Block rightMargin = new Block(new Rectangle(new Point(horizontalBound - marginsSize, marginsSize),
                marginsSize, verticalBound - marginsSize), Color.lightGray);

        //ADD MARGINS TO GAME/
        topMargin.addToGame(this);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);
        leftMargin.addToGame(this);
        rightMargin.addToGame(this);
        indicator.addToGame(this);
    }

    /**
     * a method which describes what will happen in a frame.
     * @param d DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        setBackground(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (levelInformation.blocks().size() - this.blocksCounter.getValue()
                == levelInformation.numberOfBlocksToRemove()) {
            score.increase(100);
            this.running = false;
        } else if (this.ballsCounter.getValue() == 0) {
            this.running = false;
        } else if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard, "paused -- press space to continue"));
        }
    }
    /**
     * a method which returns a boolean value, according to which the animation will proceed or stop.
     * @return stop Boolean.
     */
    public boolean shouldStop() {
        return !this.running;
    }
}
