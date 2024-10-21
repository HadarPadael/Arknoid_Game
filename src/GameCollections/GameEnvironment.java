package GameCollections;
import java.util.ArrayList;

import Collision.CollisionInfo;
import GameInterfaces.Collidable;
import Geometrics.Line;
import Geometrics.Point;

/**
 * @author Hadar Padael.
 * 212916753
 * a class which contains all the Collidables of the game.
 */
public class GameEnvironment {
    //class properties:
    private final ArrayList<Collidable> environment;
    //constructor:
    /**
     * constructor method.
     */
    public GameEnvironment() {
        this.environment = new ArrayList<>();
    }

    /**
     * accessor method.
     * @return environment Environment.
     */
    //accessor:
    public ArrayList<Collidable> getEnvironment() {
        return environment;
    }

    //other methods:
    // add the given collidable to the environment.
    /**
     * a method which adds a collidable to the game.
     * @param c Collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.

    /**
     * a method which calculates and returns the info on the closest collision of the Collidable
     * with the balls line trajectory.
     * @param trajectory Line.
     * @return info CollisionInfo.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (environment.stream().anyMatch(Collidable -> trajectory.isColliding(Collidable.getCollisionRectangle()))) {
            Collidable wanted = getClosestCollidable(trajectory, getCollidingList(trajectory));
            Point hit = trajectory.closestIntersectionToStartOfLine(wanted.getCollisionRectangle());
            return new CollisionInfo(hit, wanted);
        } else {
          return null;
        }
    }

    /**
     * a method which goes over the Collidables list
     * and returns only those which are colliding with trajectory.
     * @param trajectory Line.
     * @return List of Colliding.
     */
    public ArrayList<Collidable> getCollidingList(Line trajectory) {
        ArrayList<Collidable> colliding = new ArrayList<>();
        for (Collidable c : environment) {
            if (trajectory.isColliding(c.getCollisionRectangle())) {
                colliding.add(c);
            }
        }
        return colliding;
    }

    /**
     * a method which goes over the colliding Collidables list of the above method,
     * and detects which of them is the closest to the balls line trajectory start.
     * @param trajectory Line.
     * @param colliding List.
     * @return Collidable closest.
     */
    public Collidable getClosestCollidable(Line trajectory, ArrayList<Collidable> colliding) {
        double distance = trajectory.start().distance(colliding.get(0).getCollisionRectangle().getUpperLeft());
        Collidable closest = colliding.get(0);
        for (Collidable c : colliding) {
            if (trajectory.start().distance(c.getCollisionRectangle().getUpperLeft()) < distance) {
                distance = trajectory.start().distance(c.getCollisionRectangle().getUpperLeft());
                closest = c;
            }
        }
        return closest;
    }

}