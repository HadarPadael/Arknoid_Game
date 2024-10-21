package GameObjects;
import GameFlow.GameLevel;
import GameInterfaces.Collidable;
import GameInterfaces.HitListener;
import GameInterfaces.HitNotifier;
import GameInterfaces.Sprite;
import GameObjects.Features.Velocity;
import Geometrics.Point;
import Geometrics.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Hadar Padael.
 * 212916753
 * a class which contains all actions and parameters of Block, which is both a Collidable and a Sprite.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //class properties:
    private final Rectangle rect;
    private final Color color;
    private final ArrayList<HitListener> hitListeners;
    private ArrayList<Block> rowOfBlock;
//constructor:
    /**
     * constructor method.
     * @param rect Rectangle, which is a form of block.
     * @param color Color of block.
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
        hitListeners = new ArrayList<>();
    }
    //accessor:
    // Return the "collision shape" of the object.

    /**
     * accessor.
     * @return Rectangle of block.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }
    /**
     * accessor.
     * @return ArrayList<Block>.
     */
    public ArrayList<Block> getRowOfBlock() {
        return this.rowOfBlock;
    }

    //other methods:

    /**
     * a method which initializes each blocks array parameter.
     * by doing this, it allows each block to know to which row it belongs.
     * @param rowOfBlock ArrayList.
     */
    public void initializeArrayOfBlock(ArrayList<Block> rowOfBlock) {
        this.rowOfBlock = rowOfBlock;
    }

    /**
     * checks if Point collision is equal to one of the rectangles corners.
     * @param collisionPoint point.
     * @return boolean value t/f.
     */
    public boolean isEqualToCorner(Point collisionPoint) {
        Point upperRight = rect.getTop().intersectionWith(rect.getRight());
        Point downRight = rect.getBottom().intersectionWith(rect.getRight());
        Point downLeft = rect.getBottom().intersectionWith(rect.getLeft());

        return collisionPoint.equals(rect.getUpperLeft()) || (collisionPoint.equals(upperRight))
                || (collisionPoint.equals(downRight)) || (collisionPoint.equals(downLeft));
    }

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     * a method which gets the velocity in which the ball will deflect from the block.
     * @param collisionPoint point.
     * @param currentVelocity velocity of ball.
     * @return updated Velocity (of deflection)
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        if (rect.getLeft().pointIsInVertical(collisionPoint)
                || rect.getRight().pointIsInVertical(collisionPoint)) {
          return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else if (rect.getTop().pointIsInHorizontal(collisionPoint)
                || rect.getBottom().pointIsInHorizontal(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (isEqualToCorner(collisionPoint)) {
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
        return currentVelocity;
    }

    /**
     * a method for drawing the block on the surface.
     * @param surface Surface.
     */
    public void drawOn(DrawSurface surface) {
        int i = (int) Math.round(rect.getUpperLeft().getX()),
                i1 =  (int) Math.round(rect.getUpperLeft().getY()),
                i2 = (int) Math.round(rect.getWidth()),
                i3 = (int) Math.round(rect.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle(i, i1, i2, i3);
        surface.setColor(this.color);
        surface.fillRectangle(i, i1, i2, i3);
    }
    /**
     * a method for adding the ball to the game.
     * @param g Game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    /**
     * an implementation of Sprite interfaces method.
     */
    public void timePassed() { }

    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    // Add hl as a listener to hit events.

    /**
     * a method to add a hit listener to block.
     * @param hl HitListener.
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }
    // Remove hl from the list of listeners to hit events.

    /**
     * a method to remove a hit listener to block.
     * @param hl HitListener.
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * a method which notifies all hit listeners of the hit.
     * @param hitter Ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        ArrayList<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
