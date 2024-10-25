package Geometrics;
import GameObjects.Ball;
import GameObjects.Features.Velocity;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Hadar Padael.
 * 212916753
 * a class which contains all actions and parameters of Rectangle,
 * which is the base form of our Collidables.
 */
public class Rectangle {
    //class properties:
    private final Line top;
    private final Line bottom;
    private final Line left;
    private final Line right;

    //constructor
    // Create a new rectangle with location and width/height.

    /**
     * constructor method.
     * @param upperLeft Point
     * @param width double.
     * @param height double.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.top = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY());
        this.bottom = new Line(upperLeft.getX(), upperLeft.getY() + height,
                upperLeft.getX() + width, upperLeft.getY() + height);
        this.left = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX(), upperLeft.getY() + height);
        this.right = new Line(upperLeft.getX() + width, upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY() + height);
    }

    //accessors:

    /**
     * accessor.
     * @return Line top.
     */
    public Line getTop() {
        return top;
    }

    /**
     * accessor.
     * @return Line bottom.
     */
    public Line getBottom() {
        return bottom;
    }

    /**
     * accessor.
     * @return Line left.
     */
    public Line getLeft() {
        return left;
    }

    /**
     * accessor.
     * @return Line right.
     */
    public Line getRight() {
        return right;
    }

    // Return the width and height of the rectangle

    /**
     * accessor.
     * @return rectangles width.
     */
    public double getWidth() {
        return this.top.length();
    }

    /**
     * accessor.
     * @return rectangles height.
     */
    public double getHeight() {
        return this.left.length();
    }

    // Returns the upper-left point of the rectangle.

    /**
     * accessor.
     * @return upper left Point.
     */
    public Point getUpperLeft() {
        return this.top.intersectionWith(this.left);
    }

    //other methods:

    // Return a (possibly empty) List of intersection points
    // with the specified line.

    /**
     * a method which returns a list of intersection points of the rectangle with the line.
     * @param line Line.
     * @return List.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<>();
        Line[] lineA = {top, bottom, left, right};
        for (Line l : lineA) {
            if (l.intersectionWith(line) != null) {
                list.add(l.intersectionWith(line));
            }
        }
        return list;
    }

}