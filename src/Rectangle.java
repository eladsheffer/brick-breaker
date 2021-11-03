// ID 031824915

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elad Sheffer
 * This class represents a rectangle
 */
public class Rectangle {

    //constants
    private static final int NUM_OF_SIDES = 4;
    private static final int TOP = 0;
    private static final int BOTTOM = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;

    //attributes
    private Point upperLeft;
    private double width;
    private double height;
    private Line[] lines;
    private Velocity velocity;

    //constructors

    /**
     * Creates a new rectangle with location and width.
     *
     * @param upperLeft point of the rectangle
     * @param width     of the rectangle
     * @param height    of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.lines = new Line[NUM_OF_SIDES];
        this.velocity = new Velocity(0, 0);
        determineLinesOfRectangle();

    }

    /**
     * Creates a new rectangle with location and width.
     *
     * @param upperLeft point of the rectangle
     * @param width     of the rectangle
     * @param height    of the rectangle
     * @param velocity  of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height, Velocity velocity) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.lines = new Line[NUM_OF_SIDES];
        this.velocity = velocity;
        determineLinesOfRectangle();

    }

    //accessors

    /**
     * Returns the width and width of the rectangle.
     *
     * @return Return the width and height of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the width and height of the rectangle.
     *
     * @return the width and height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the upper-right point of the rectangle.
     *
     * @return the upper-right point of the rectangle.
     */
    public Point getUpperRight() {
        double x = this.upperLeft.getX() + this.width;
        double y = this.upperLeft.getY();

        return new Point(x, y);
    }

    /**
     * Returns the lower-left point of the rectangle.
     *
     * @return the lower-left point of the rectangle.
     */
    public Point getLowerLeft() {
        double x = this.upperLeft.getX();
        double y = this.upperLeft.getY() + this.height;

        return new Point(x, y);
    }

    /**
     * Returns the lower-right point of the rectangle.
     *
     * @return the lower-right point of the rectangle.
     */
    public Point getLowerRight() {
        double x = this.upperLeft.getX() + this.width;
        double y = this.upperLeft.getY() + this.height;

        return new Point(x, y);
    }


    /**
     * Returns the lines of the rectangle.
     *
     * @return the lines of the rectangle.
     */
    public Line[] getLinesOfRectangle() {
        return this.lines;
    }

    /**
     * Returns the velocity of the rectangle.
     *
     * @return the velocity of the rectangle
     */
    public Velocity getVelocity() {
        return velocity;
    }

    //mutators

    /**
     * Sets the upper left point of the rectangle.
     *
     * @param upperLeftPoint upper left point of the rectangle
     */
    public void setUpperLeft(Point upperLeftPoint) {
        this.upperLeft = upperLeftPoint;
        determineLinesOfRectangle();
    }

    /**
     * Sets the width point of the rectangle.
     *
     * @param w width point of the rectangle
     */
    public void setWidth(double w) {
        this.width = w;
    }

    /**
     * Sets the height point of the rectangle.
     *
     * @param h height point of the rectangle
     */
    public void setHeight(double h) {
        this.height = h;
    }

    /**
     * Sets the velocity of the rectangle.
     *
     * @param v velocity to set
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the rectangle.
     *
     * @param dx to set
     * @param dy to set
     */
    public void setVelocity(double dx, double dy) {
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);
    }

    //methods

    /**
     * Determines lines of rectangle.
     */
    private void determineLinesOfRectangle() {
        if (this.lines == null) {
            return;
        }

        //Sides of the rectangle
        this.lines[TOP] = new Line(this.upperLeft.getX() + this.getVelocity().getDx(),
                this.upperLeft.getY() + this.getVelocity().getDy(),
                this.getUpperRight().getX() + this.getVelocity().getDx(),
                this.getUpperRight().getY() + this.getVelocity().getDy());

        this.lines[BOTTOM] =
                new Line(this.getLowerLeft().getX() + this.getVelocity().getDx(),
                        this.getLowerLeft().getY() + this.getVelocity().getDy(),
                        this.getLowerRight().getX() + this.getVelocity().getDx(),
                        this.getLowerRight().getY() + this.getVelocity().getDy());

        this.lines[LEFT] = new Line(this.upperLeft.getX() + this.getVelocity().getDx(),
                this.upperLeft.getY() + this.getVelocity().getDy(),
                this.upperLeft.getX() + this.getVelocity().getDx(),
                this.getLowerLeft().getY() + this.getVelocity().getDy());

        this.lines[RIGHT] =
                new Line(this.getUpperRight().getX() + this.getVelocity().getDx(),
                        this.getUpperRight().getY() + this.getVelocity().getDy(),
                        this.getLowerRight().getX() + this.getVelocity().getDx(),
                        this.getLowerRight().getY() + this.getVelocity().getDy());
    }

    /**
     * Returns a (possibly empty) List of intersection points with the specified line.
     *
     * @param line which intersects with the rectangle.
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public List<Point> intersectionPoints(Line line) {

        List<Point> intersectionPoints = new ArrayList<Point>();

        //looping over the sides of rectangle, checking intersection points with each side
        //and adding intersection point to the list
        for (Line lineOfRectangle : this.lines) {
            Point interceptionPoint = lineOfRectangle.intersectionWith(line);
            if (interceptionPoint != null) {
                intersectionPoints.add(interceptionPoint);
            }
        }

        return intersectionPoints;
    }


    @Override
    /**
     * returns a String that represents this rectangle.
     * @return String that represents this rectangle
     * @overrides toString() in class java.lang.Object
     */
    public String toString() {
        StringBuilder linesString = new StringBuilder();
        for (int i = 0; i < this.lines.length - 1; i++) {
            linesString.append(this.lines[i].toString()).append("\n");
        }

        linesString.append(this.lines[this.lines.length - 1]);

        return "Rectangle: " + "upperLeft = " + this.upperLeft + ", width = " + Integer.toString((int) this.width)
                + ", height = " + Integer.toString((int) this.height)
                + "\nlines:\n" + linesString;
    }
}