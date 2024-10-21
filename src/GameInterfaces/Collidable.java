package GameInterfaces;
import GameObjects.Ball;
import GameObjects.Features.Velocity;
import Geometrics.Point;
import Geometrics.Rectangle;
import biuoop.DrawSurface;
/**
 * @author Hadar Padael.
 * 212916753
 * An interface for all Collidables of the game.
 */
public interface Collidable {
    // Return the "collision shape" of the object.

    /**
     * a method for getting collidables rectangle.
     * @return Rectangle.
     */
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     * a method which gets the velocity in which the ball will deflect from the collidable.
     * @param collisionPoint Point.
     * @param currentVelocity Velocity.
     * @return Velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * a method for drawing the collidable on the surface.
     * @param d DrawSurface.
     */
    void drawOn(DrawSurface d);
}