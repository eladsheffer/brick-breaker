// ID 031824915

/**
 * @author Elad Sheffer
 * @version 1.0
 * @since 2021-03-15
 * This class represents a point
 */
public class Point {

    //constants
    private static final double TOLERANCE = 0.01;

    //attributes
    private double x;
    private double y;

    //constructors

    /**
     * Creates a new 2 dimensional Point object.
     *
     * @param x axes
     * @param y axes
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //accessors

    /**
     * Returns the x value of this point.
     *
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y value of this point.
     *
     * @return the y value of this point
     */
    public double getY() {
        return this.y;
    }

    //mutators

    /**
     * sets the x value of the point.
     *
     * @param xValue x value of the point
     */
    public void setX(double xValue) {
        this.x = xValue;
    }

    /**
     * sets the y value of the point.
     *
     * @param yValue y value of the point
     */
    public void setY(double yValue) {
        this.y = yValue;
    }

    //methods

    /**
     * returns the distance of this point to the other point.
     *
     * @param other - point to measure distance from
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * returns true is the points are equal, false otherwise.
     *
     * @param other - point to compare to
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }

        //if the points are equal within certain level of tolerance - returning true
        return Math.abs(this.x - other.x) <= TOLERANCE && Math.abs(this.y - other.y) <= TOLERANCE;
    }

    @Override
    /**
     * returns a String that represents this point.
     *
     * @return String that represents this point
     * in the following format: (x, y)
     * if the values are close to integer - representing them as integers
     * @overrides toString() in class java.lang.Object
     */
    public String toString() {
        return "(" + (Math.abs(this.x - (int) this.x) <= TOLERANCE ? Integer.toString((int) this.x) : this.x) + ", "
                + (Math.abs(this.y - (int) this.y) <= TOLERANCE ? Integer.toString((int) this.y) : this.y) + ")";
    }
}


