// ID 031824915

/**
 * @author Elad Sheffer
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {

    //attributes
    private double dx;
    private double dy;

    //constructors

    /**
     * Creates velocity with x and y ingredients.
     *
     * @param dx - x value of the velocity
     * @param dy - y value of the velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Creates a static velocity with angle and volume instead of (dx, dy) ingredients.
     *
     * @param angle - the angle of the velocity
     * @param speed - the volume of the velocity
     * @return new Velocity instance
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        //the dx ingredient is the co-sinus of the angle times the speed
        double dx = speed * Math.sin(Math.toRadians(angle));

        //the dy ingredient is the sinus of the angle times the speed
        double dy = -speed * Math.cos(Math.toRadians(angle));

        //calling the constructor with the dx, dy ingredients
        return new Velocity(dx, dy);
    }

    //accessors

    /**
     * Returns the x value of this velocity.
     *
     * @return the x value of this velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Returns the y value of this velocity.
     *
     * @return the y value of this velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Returns the speed of the velocity according to dx, dy ingredients.
     *
     * @return the speed of the velocity according to dx, dy ingredients
     */
    public double getSpeed() {
         double s = Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
        return s;
    }

    /**
     * Returns the angle of the velocity according to dx, dy ingredients.
     *
     * @return the angle of the velocity according to dx, dy ingredients
     */
    public double getAngle() {

       double a = Math.toDegrees(Math.atan(this.dx / this.dy));
       return a;
    }

    //mutators

    /**
     * sets the x value of the velocity.
     *
     * @param dxValue - x value of the velocity
     */
    public void setDx(double dxValue) {
        this.dx = dxValue;
    }

    /**
     * sets the y value of the velocity.
     *
     * @param dyValue - y value of the velocity
     */
    public void setDy(double dyValue) {
        this.dy = dyValue;
    }

    /**
     * Takes a point with position (x,y) and returns a new point with position (x+dx, y+dy).
     *
     * @param p - old point (x, y)
     * @return a new point with addition (dx, dy) to the old point (x, y)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Sets angle of velocity.
     * @param angle of velocity to set
     */
    public void setAngleOfVelocity(double angle) {
        this.dx = this.getSpeed() * Math.sin(Math.toRadians(angle));
        this.dy = -this.getSpeed() * Math.cos(Math.toRadians(angle));
    }

    @Override
    /**
     * returns a String that represents this velocity.
     * @return String that represents this velocity
     * @overrides toString() in class java.lang.Object
     */
    public String toString() {
        return "(" + this.dx + " ," + this.dy + ")" + "\nSpeed: " + this.getSpeed() + "\tAngle: "
                + this.getAngle();
    }
}