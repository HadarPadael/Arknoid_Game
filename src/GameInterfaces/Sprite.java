package GameInterfaces;
import biuoop.DrawSurface;
/**
 * @author Hadar Padael.
 * 212916753
 * An interface for all Sprites of the game.
 */
public interface Sprite {
    // draw the sprite to the screen

    /**
     * a method which draws the sprite on the surface.
     * @param d Surface.
     */
    void drawOn(DrawSurface d);
    // notify the sprite that time has passed

    /**
     * a method which updates the sprites state in accordance to the games rules.
     */
    void timePassed();
}
