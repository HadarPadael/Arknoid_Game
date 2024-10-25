package Geometrics;
/**
 * @author Hadar Padael.
 * 212916753
 * class which defines a point object and its functionalities.
 */
public class Point {
    //class properties:
    private final double x;
    private final double y;

    /**
     * constructor method.
     * @param x double
     * @param y double
     */
    // constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //accessors:

    /**
     * Return the x value of this point.
     * @return x value || y value of point
     */
    public double getX() {
        return x;
    }
    /**
     * Return the y value of this point.
     * @return x value || y value of point
     */
    public double getY() {
        return y;
    }

    //other methods:

    /**
     * distance -- return the distance of this point to the other point.
     * @param other point
     * @return distance between two points.
     */
    public double distance(Point other) {
        double dx, dy;
        dx = this.x - other.x;
        dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * equals -- return true is the points are equal, false otherwise.
     * @param other point
     * @return boolean value - is one point equals to another?
     */
    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }
}
