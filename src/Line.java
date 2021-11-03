// ID 031824915

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Elad Sheffer
 * This class represents a line comprised of 2 points
 */
public class Line {

    //constants
    private static final double TOLERANCE = 0.01;

    //attributes
    private Point start;
    private Point end;
    private double slope;
    private double intercept;
    private Color color;

    // constructors

    /**
     * Creates a segment of a line with start and end point.
     *
     * @param start - start point of this line
     * @param end   - end point of this line
     */
    public Line(Point start, Point end) {

        this.start = start;
        this.end = end;

        //determining the slope of intercept of the line according to its 2 points
        this.slope = this.slope();
        this.intercept = this.intercept();

        this.color = Color.BLACK;
    }

    /**
     * Creates a segment of a line with start and end point.
     *
     * @param x1 - the x value of point 1
     * @param y1 - the y value of point 1
     * @param x2 - the x value of point 2
     * @param y2 - the y value of point 2
     */
    public Line(double x1, double y1, double x2, double y2) {

        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);

        //determining the slope of intercept of the line according to its 2 points
        this.slope = this.slope();
        this.intercept = this.intercept();

        this.color = Color.BLACK;
    }

    /**
     * Returns the length of the line.
     *
     * @return the length of the line
     */
    public double length() {

        //the distance between 2 points
        return this.end.distance(this.start);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    //accessors

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Returns the slope of the line.
     *
     * @return the slope of the line
     */
    public double getSlope() {
        return slope;
    }

    /**
     * Returns the intercept of the line.
     *
     * @return the intercept of the line
     */
    public double getIntercept() {
        return intercept;
    }

    //mutators

    /**
     * Sets the start point of this line.
     *
     * @param startPoint start point of this line
     */
    public void setStart(Point startPoint) {
        this.start = startPoint;
    }

    /**
     * Sets the end point of this line.
     *
     * @param endPoint end point of this line
     */
    public void setEnd(Point endPoint) {
        this.end = endPoint;
    }

    //methods

    /**
     * Returns true if x equals y, false - otherwise.
     *
     * @param x double var
     * @param y double var
     * @return true if x equals y, false - otherwise
     */
    private boolean equals(double x, double y) {
        return Math.abs(x - y) <= TOLERANCE;
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other - line to determine intersection with
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;

    }

    /**
     * Returns the intersection point if the lines intersect,and null otherwise.
     *
     * @param other line to determine intersection with
     * @return the intersection point if the lines intersect,and null otherwise.
     */
    public Point intersectionWith(Line other) {

        if (other == null) {
            return null;
        }

        //if the 2 lines are with the same slope:
        //if they have the same intercept:
        //if they have only one joint point - returning the point
        //if they have more than one joint point - returning null
        //if they don't have the same intercept (but have the same slope) - they are parallel, thus returning null
        if (equals(this.slope, other.slope)) {
            if (equals(this.intercept, other.intercept)) {
                if (this.start.equals(other.end)) {
                    return this.start;
                } else if (this.end.equals(other.start)) {
                    return this.end;
                }
            }
            return null;
        }

        //if one of the lines is vertical - it has an infinite slope, and the calculation is different
        if (this.slope == Double.POSITIVE_INFINITY) {
            return this.intersectionWithInfiniteSlope(other);
        }

        if (other.slope == Double.POSITIVE_INFINITY) {
            return other.intersectionWithInfiniteSlope(this);
        }

        //if the lines don't have the same slope or none of the slopes is infinite:
        //calculating the x value of their intersection point and then the y value of the line equation of
        //this line
        double x = (other.intercept - this.intercept) / (this.slope - other.slope);
        double y = this.slope * x + this.intercept;

        //checking if the intersection point is within the segments of the 2 lines
        //if yes - returning the intersection point. null - otherwise
        if (this.isPointInSegment(x, y) && other.isPointInSegment(x, y)) {
            return new Point(x, y);
        }

        return null;
    }

    /**
     * Returns the intersection point between this vertical line if the lines intersect,and null otherwise.
     *
     * @param other line to determine intersection with
     * @return the intersection point between this vertical line if the lines intersect,and null otherwise.
     */
    private Point intersectionWithInfiniteSlope(Line other) {

        //if this line is vertical - it has a fixed value of x along the line
        double x = this.start.getX();

        //the y value of the intersection is calculated by setting the x value of this line in
        //the other's line equation
        double y = other.slope * x + other.intercept;

        //checking if the intersection point is within the segments of the 2 lines
        //if yes - returning the intersection point. null - otherwise
        if (this.isPointInSegment(x, y) && other.isPointInSegment(x, y)) {
            return new Point(x, y);
        }

        return null;
    }

    /**
     * returns true is the lines are equal, false otherwise.
     *
     * @param other line to compare to
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }

        //returning true if 2 of the points are equal, false otherwise
        return this.start.equals(other.start) && this.end.equals(other.end);
    }

    /**
     * calculates the slope of the line according to the formula.
     *
     * @return the slope of the line according to the formula
     */
    private double slope() {

        //if the denominator is 0 - the slope is infinite - i.e. - the line is vertical
        if (equals(this.end.getX(), this.start.getX())) {
            return Double.POSITIVE_INFINITY;
        }

        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     * calculates the intercept of the line according to the formula.
     *
     * @return the intercept of the line according to the formula
     */
    private double intercept() {
        return this.start.getY() - this.slope() * this.start.getX();
    }

    /**
     * returns true if the point is inside the segment, false otherwise.
     *
     * @param x value of the point to check
     * @param y value of the point to check
     * @return true if the point is inside the segment, false otherwise
     */
    public boolean isPointInSegment(double x, double y) {

        //checking if the x value of the point is between the x values of the ends of the line
        //and if the y value of the point is between the y values of the ends of the line
        double startX = Math.min(this.start.getX(), this.end.getX());
        double endX = Math.max(this.start.getX(), this.end.getX());
        double startY = Math.min(this.start.getY(), this.end.getY());
        double endY = Math.max(this.start.getY(), this.end.getY());

        Point p = new Point(x, y);

        return (x >= startX && x <= endX && y >= startY && y <= endY) || (p.equals(this.start) || p.equals(this.end));
    }


    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the  start of the line.
     *
     * @param rect rectangle given
     * @return the closest intersection point to the  start of the line. null - if none.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        //list of all intersection points of this line with rectangle given
        ArrayList<Point> interceptionPoints = (ArrayList<Point>) rect.intersectionPoints(this);

        if (interceptionPoints.size() == 0) {
            return null;
        }

        //first point in list is the closest to start point of the line
        Point closestPoint = interceptionPoints.get(0);
        double minDistance = interceptionPoints.get(0).distance(this.start);

        //looping over the list and checking if other point is closer to start point of the line than the first point
        //in the list
        for (Point interceptionPoint : interceptionPoints) {
            double distance = interceptionPoint.distance(this.start);
            if (distance < minDistance) {
                minDistance = distance;
                closestPoint = interceptionPoint;
            }
        }

        return closestPoint;
    }

    /**
     * Draws the line on the given DrawSurface.
     *
     * @param surface the line is drawn on
     */
    public void drawOn(DrawSurface surface) {
        //setting the color of the line on the surface
        surface.setColor(this.color);

        //setting the line on the surface with start point and end point
        surface.drawLine((int) this.start.getX(), (int) this.start.getY(), (int) this.end.getX(),
                (int) this.end.getY());
    }

    @Override
    /**
     * returns a String that represents this line.
     * @return String that represents this line
     * @overrides toString() in class java.lang.Object
     */
    public String toString() {

        //returning the start and end points, then the equation of the line
        //if the slope is infinite - the line is "X = ..."
        //if not - the line is "Y = slope * X + intercept"
        //if the intercept is 0 - ignoring it
        //omitting the negative sign of the intercept and adding the sign to the addition operation
        return "Start: " + this.start + "\tEnd: " + this.end
                + (this.slope == Double.POSITIVE_INFINITY ? "\t\tX = "
                + (this.start.getX() == (int) this.start.getX() ? Integer.toString((int) this.start.getX())
                : this.start.getX())
                : ("\t\tY = " + (this.slope == 0 ? (this.start.getX() == (int) this.start.getX()
                ? Integer.toString((int) this.start.getY()) : this.start.getY()) : (this.slope + "*X "
                + (this.intercept > 0 ? " + " : this.intercept < 0 ? " - " : "")
                + (this.intercept == 0 ? ""
                : this.intercept < 0 ? Math.abs(this.intercept) : this.intercept)))));

    }
}
