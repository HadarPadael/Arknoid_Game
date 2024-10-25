package Trackers;
import java.awt.Color;

import GameFlow.GameLevel;
import GameInterfaces.Sprite;
import Geometrics.Point;
import Geometrics.Rectangle;
import Trackers.Base.Counter;
import biuoop.DrawSurface;
/**
 * @author Hadar Padael.
 * 212916753
 * a class for the printing of the score to the screen of the game.
 */
public class ScoreIndicator implements Sprite {
    //class property/
    private final Counter score;
    private final Rectangle rectangle;
    private String levelName;
//constructor/

    /**
     * a constructor method.
     * @param score Counter.
     */
    public ScoreIndicator(Counter score, String levelName) {
        this.score = score;
        this.rectangle = new Rectangle(new Point(200, 0), 400, 15);
        this.levelName = levelName;
    }

    /**
     * a method which draws the score onto the games screen.
     * @param surface DrawSurface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.LIGHT_GRAY);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawText((int) (this.rectangle.getUpperLeft().getX()
                        + this.rectangle.getWidth() / 2 - 20),
                (int) (this.rectangle.getUpperLeft().getY()
                        + this.rectangle.getHeight() / 2 + 5),
                "Score: "
                        + Integer.toString(this.score.getValue()), 13);
        surface.drawText((int) (this.rectangle.getUpperLeft().getX()
                + this.rectangle.getWidth() - 20),
                (int) (this.rectangle.getUpperLeft().getY()
                        + this.rectangle.getHeight() / 2 + 5),
                "Level Name: "
                        + levelName, 13);
    }

    /**
     * part of sprite interface implementation.
     */
    public void timePassed() {
    }

    /**
     * a method which adds the indicator to the game.
     * @param g Game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}