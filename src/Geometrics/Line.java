package Geometrics;
/**
 * @author Hadar Padael.
 * 212916753
 * a class which definds a line and its methods regarding its intersections and more.
 */
public class Line {
    //class properties:
    private final Point start;
    private final Point end;

    // constructors:
    /**
     * constructor method.
     * @param start point
     * @param end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor method.
     * @param x1 double
     * @param y1 double
     * @param x2 double
     * @param y2 double
     */
    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }

    //accessors:
    /**
     *  Return the length of the line.
     * @return double value for length
     */
    public double length() {
        return end.distance(start);
    }

    /**
     * Returns the middle point of the line.
     * @return middle point
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);

    }

    /**
     * Returns the start point of the line.
     * @return start point
     */
    public Point start() {
        return new Point(this.start.getX(), this.start.getY());
    }

    /**
     * Returns the end point of the line.
     * @return end point
     */
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());
    }

    //other methods:
    /**
     * Returns true if the lines intersect, false otherwise.
     * @param other line
     * @return true/false
     */
    public boolean isIntersecting(Line other) {
        //find slope of theoretical segments (this start to other start, this start to this end)
        double m1 = (this.start.getY() - other.start.getY()) / (this.start.getX() - other.start.getX()),
                m2 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());

        //if m1 == m2 and the distance between the two points matches with the x & y locations described we can assume//
        //two lines to be unified, thus having infinity of intersection points//
        if (intersectionWith(other) == null) {
            if (this.equals(other)) {
                return true;
            } else if ((m1 == m2) && (this.start.getX() < other.start.getX())
                    && (this.start.distance(other.start) < this.start.distance(this.end))) {
                return true;
            } else {
                return (m1 == m2) && (this.start.getX() > other.start.getX())
                        && (this.start.distance(other.start) < other.start.distance(other.end));
            }
        }
        return true;
    }


    /**
     * Returns the intersection point if the lines intersect,and null otherwise.
     * @param other line
     * @return point or null
     */
    public Point intersectionWith(Line other) {
        double a1 = this.start.getY() - this.end.getY(),
                b1 = this.end.getX() - this.start.getX(),
                c1 = (a1 * this.end.getX()) + (b1 * this.end.getY()),
                a2 = other.start.getY() - other.end.getY(),
                b2 = other.end.getX() - other.start.getX(),
                c2 = (a2 * other.end.getX()) + (b2 * other.end.getY()),
                denominator = (a1 * b2) - (a2 * b1);
        //if denominator = 0 > parallel.
        //is there an intersection in end/start point? if so, return it.
        if (denominator == 0) {
            if ((this.end.equals(other.start) || this.end.equals(other.end))) {
                return this.end;
            } else if (this.start.equals(other.start) || this.start.equals(other.end)) {
                return this.start;
            } else {
                return null;
            }
        }
        // calculate point of intersection
        double intersectX = (b2 * c1 - b1 * c2) / denominator,
                intersectY = (a1 * c2 - a2 * c1) / denominator,
                //calculate distances on both y-axis and x-axis;
                rx0 = (intersectX - this.end.getX()) / (this.start.getX() - this.end.getX()),
                ry0 = (intersectY - this.end.getY()) / (this.start.getY() - this.end.getY()),
                rx1 = (intersectX - other.end.getX()) / (other.start.getX() - other.end.getX()),
                ry1 = (intersectY - other.end.getY()) / (other.start.getY() - other.end.getY());

        //if not parallel, check if intersection point is within the bounds of the segment.
        //if distance between start point and intersection point on either x-axis or y-axis,
        // is between 0 and 1 for both segments> return point of intersection, else>null.
        if (((rx0 >= 0 && rx0 <= 1) || (ry0 >= 0 && ry0 <= 1))
                && ((rx1 >= 0 && rx1 <= 1) || (ry1 >= 0 && ry1 <= 1))) {
            return new Point(intersectX, intersectY);
        } else {
            return null;
        }
    }


    /**
     * equals -- return true is the lines are equal, false otherwise.
     * @param other line
     * @return true or false
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start)) && (this.end.equals(other.end));
    }

        // If this line does not intersect with the rectangle, return null.
        // Otherwise, return the closest intersection point to the
        // start of the line.

    /**
     * a method which calculates the closest point of intersection to the start of the line.
     * @param rect Rectangle.
     * @return Point of intersection.
     */
        public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect.intersectionPoints(this).isEmpty()) {
            return null;
        } else if (rect.intersectionPoints(this).size() == 1) {
            return rect.intersectionPoints(this).get(0);
            //there could be either 0/1/2 intersection points. therefore, here: else-> 2 intersection points/
        } else {
            double distance = rect.intersectionPoints(this).get(0).distance(this.start);
            Point intersect = rect.intersectionPoints(this).get(0);
            if (rect.intersectionPoints(this).get(1).distance(this.start) < distance) {
                    intersect = rect.intersectionPoints(this).get(1);
            }
            return intersect;
        }
    }

    /**
     * a method which checks if line and Rectangle collide in terms of t/f.
     * @param rect Rectangle.
     * @return boolean value.
     */
    public boolean isColliding(Rectangle rect) {
        return closestIntersectionToStartOfLine(rect) != null;
    }

    /**
     * a method which checks if a point is in a vertical line, in terms of t/f.
     * @param collision Point.
     * @return boolean value.
     */
    public boolean pointIsInVertical(Point collision) {
        if (collision.getX() == start.getX()) {
            return (collision.getY() > start.getY()) && (collision.getY() < end.getY());
        }
        return false;
    }

    /**
     * a method which checks if a point is in a horizontal line, in terms of t/f.
     * @param collision Point.
     * @return boolean value.
     */
    public boolean pointIsInHorizontal(Point collision) {
        if (collision.getY() == start.getY()) {
            return (collision.getX() > start.getX()) && (collision.getX() < end.getX());
        }
        return false;
    }
}


