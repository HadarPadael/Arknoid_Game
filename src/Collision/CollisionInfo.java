package Collision;
import GameInterfaces.Collidable;
import Geometrics.Point;

/**
 * @author Hadar Padael.
 * 212916753
 * a class which holds info regarding collision Point and Object.
 */
public class CollisionInfo {
    //class properties:
    private final Point collision;
    private final Collidable collidable;
//constructor:

    /**
     * constructor method.
     * @param hit Point
     * @param c Collidable.
     */
    public CollisionInfo(Point hit, Collidable c) {
        this.collision = hit;
        this.collidable = c;
    }
    //accessors:
    // the point at which the collision occurs.

    /**
     * accessor.
     * @return collision Point.
     */
    public Point collisionPoint() {
        return this.collision;
    }

    // the collidable object involved in the collision.

    /**
     * accessor.
     * @return collision Collidable.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}