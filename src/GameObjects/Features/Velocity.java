package GameObjects.Features;
import Geometrics.Point;

/**
 * @author Hadar Padael.
 * 212916753
 * a class which defines velocity of balls.
 */
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    //class properties:
    private final double dx;
    private final double dy;

    // constructor:
    /**
     * constructor method.
     * @param dx double
     * @param dy double
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    //accessors:
    /**
     * accessor.
     * @return double dx
     */
    public double getDx() {
        return dx;
    }
    /**
     * accessor.
     * @return double dy
     */
    public double getDy() {
        return dy;
    }

    //other methods:
    /**
     * a method which converts angle and speed to dx and dy coordinates for speed.
     * @param angle double
     * @param speed double
     * @return velocity(dx, dy).
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle));
        double dy = speed * (-1) * Math.sin(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }


    /**
     * a method which applies the dx and dy onto the point of the ball.
     * @param p point
     * @return new point(x,y).
     */
    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}
