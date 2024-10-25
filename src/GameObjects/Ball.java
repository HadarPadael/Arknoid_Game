package GameObjects;
import GameCollections.GameEnvironment;
import GameFlow.GameLevel;
import GameInterfaces.Sprite;
import GameObjects.Features.Velocity;
import Geometrics.Line;
import Geometrics.Point;
import Geometrics.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Hadar Padael.
 * 212916753
 * a class which contains all actions and parameters of Ball, including bounds and movement.
 */
public class Ball implements Sprite {
    //class properties:
    private Point center;
    private final int rad;
    private final Color color;
    private Velocity v;
    private final GameEnvironment environment;

    // constructors:
    /**
     * constructor method.
     * @param center point
     * @param r int
     * @param color rbg
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.rad = r;
        this.color = color;
        this.environment = new GameEnvironment();
    }

    /**
     * constructor method.
     * @param x1 double
     * @param y1 double
     * @param color rbg
     * @param rad radius
     * @param environment GameEnvironment.
     */
    public Ball(double x1, double y1, Color color, int rad, GameEnvironment environment) {
        this.color = color;
        this.rad = rad;
        this.center = new Point(x1, y1);
        this.environment = environment;
    }
  // accessors:
    /**
     * accessor.
     * @return int x
     */
    public int getX() {
        return (int) Math.round(this.center.getX());
    }
    /**
     * accessor.
     * @return int y
     */
    public int getY() {
        return (int) Math.round(this.center.getY());
    }
    /**
     * accessor.
     * @return size
     */
    public int getSize() {
        return rad;
    }
    /**
     * accessor.
     * @return color rbg
     */
    public Color getColor() {
        return this.color;
    }
    /**
     * accessor.
     * @return GameEnvironment environment.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }
    //other methods:
    /**
     * draw the ball on the given DrawSurface.
     * @param surface DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        int i = this.getX(), i1 = this.getY(), i2 = this.getSize();
        surface.setColor(Color.black);
        surface.drawCircle(i, i1, i2);
        surface.setColor(this.color);
        surface.fillCircle(i, i1, i2);
    }

    /**
     * setter.
     * @param v velocity
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * setter.
     * @param dx double
     * @param dy double
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * getter.
     * @return v
     */
    public Velocity getVelocity() {
        return v;
    }

    /**
     * a method which sets the values for the line which represents the Balls course.
     * @return Line trajectory.
     */
    public Line trajectory() {
        Point startOfTrajectory = new Point(
                Math.floor(this.center.getX()), Math.floor(this.center.getY()));
        Point endOfTrajectory = new Point(
                Math.floor(this.center.getX() + this.v.getDx()),
                Math.floor(this.center.getY() + this.v.getDy()));
        return new Line(startOfTrajectory, endOfTrajectory);
    }

    /**
     * checks to see if movement is allowed and changes it if required, moves the ball using set velocity.
     */
    public void moveOneStep() {
        Line trajectory = trajectory();
        if (environment.getClosestCollision(trajectory) != null) {
            Point p = environment.getClosestCollision(trajectory).collisionPoint();
            center = updateCenter(p);
            setVelocity(environment.getClosestCollision(trajectory).collisionObject()
                    .hit(this, p, getVelocity()));
            getVelocity().applyToPoint(center);

        } else {
            center = getVelocity().applyToPoint(center);
        }
    }

    /**
     * a method which updates the location of the balls center, taking into consideration its direction.
     * @param p Point center
     * @return updated Point p center.
     */
    public Point updateCenter(Point p) {
        if (getVelocity().getDx() < 0 && getVelocity().getDy() < 0) {
            return new Point(p.getX() + rad, p.getY() + rad);
        } else if (getVelocity().getDx() > 0 && getVelocity().getDy() > 0) {
            return new Point(p.getX() - rad, p.getY() - rad);
        } else if (getVelocity().getDy() < 0 && getVelocity().getDx() > 0) {
            return new Point(p.getX() - rad, p.getY() + rad);
        } else { //getVelocity().getDy() > 0 && getVelocity().getDx() < 0)
            return new Point(p.getX() + rad, p.getY() - rad);
        }
    }

    /**
     * a method which activates the movement of the ball.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * a method for adding the ball to the game.
     * @param g game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * a method which removes the ball from game.
     * @param game GameLevel.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }


}
