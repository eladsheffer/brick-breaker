// ID 031824915

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Elad Sheffer
 * This class represents a ball
 */
public class Ball implements Sprite {

    //constants
    private static final int DEFAULT_RADIUS = 1;
    private static final int TOP = 0;
    private static final int BOTTOM = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;
    private static final double TOLERANCE = 0.01;

    //attributes
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameLevel gameLevel;

    //constructors

    /**
     * Creates a ball with a center point, radius and color. Velocity is created with 0 default value
     *
     * @param center point of the ball
     * @param radius of the ball
     * @param color  the ball
     */
    public Ball(Point center, int radius, Color color) {

        this.center = center;
        if (radius > 0) {
            this.radius = radius;
        } else {
            this.radius = DEFAULT_RADIUS;
        }
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Creates a ball with x and y values of the center point, radius and color.
     * Velocity is created with 0 default value
     *
     * @param x      value of the center point
     * @param y      value of the center point
     * @param radius of the ball
     * @param color  of the ball
     * @param gameLevel   the ball is in
     */
    public Ball(int x, int y, int radius, Color color, GameLevel gameLevel) {
        this.center = new Point(x, y);
        if (radius > 0) {
            this.radius = radius;
        } else {
            this.radius = DEFAULT_RADIUS;
        }
        this.color = color;
        this.velocity = new Velocity(0, 0);

        this.gameLevel = gameLevel;
    }

    //accessors

    /**
     * Returns the x value of the center of the ball.
     *
     * @return the x value of the center of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Returns the y value of the center of the ball.
     *
     * @return the x value of the center of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Returns the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Returns the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    //mutators

    /**
     * Sets the center point of the ball.
     *
     * @param centerPoint point of the ball
     */
    public void setCenter(Point centerPoint) {
        this.center = centerPoint;
    }

    /**
     * Sets the color of the ball.
     *
     * @param c color of the ball
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * Sets the radius of the ball.
     *
     * @param r of the ball
     */
    public void setRadius(int r) {
        this.radius = r;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball with dx and dy ingredients.
     *
     * @param dx ingredient of the velocity
     * @param dy ingredient of the velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);
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
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the ball is drawn on
     */
    public void drawOn(DrawSurface surface) {
        //setting the color of the ball on the surface
        surface.setColor(this.color);

        //setting the ball on the surface as a full circle with center point and radius
        surface.fillCircle((int) center.getX(), (int) center.getY(), radius);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Add sprite to gameLevel.
     *
     * @param g gameLevel to add ball to
     */
    @Override
    public void addToGame(GameLevel g) {
        if (g != null) {
            g.addSprite(this);
            g.getBallCounter().increase(1);
        }
    }

    /**
     * Remove ball from gameLevel.
     *
     * @param g gameLevel to remove ball from
     */
    public void removeFromGame(GameLevel g) {
        if (g != null) {
            g.removeSprite(this);
            g.getBallCounter().decrease(1);
        }
    }

    /**
     * Moves the ball the next step on the surface according to the velocity.
     */
    public void moveOneStep() {

        //computing the predicted trajectory
        Line trajectory = computeTrajectory();

        //checking the borders
        if (reachedBorder(trajectory)) {
            return;
        }

        //getting the closest collision info
        //CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        CollisionInfo collisionInfo = this.gameLevel.getEnvironment().getClosestCollision(trajectory);

        if (collisionInfo != null && collisionInfo.collisionObject() != null) {

            //changing the velocity according to the collision info (point, type of collidiable
            // and position on the collidable)
            Velocity newVelocity =
                    collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.velocity);
            this.velocity = newVelocity;
        }

        //setting the next position of the center of the ball (new velocity after collision)
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Checks if the ball reached the borders of the screen.
     * If so - reverse the velocity and returns true. False - otherwise
     *
     * @param trajectory of the ball
     * @return true if the ball reached the borders of the screen. False - otherwise.
     */
    private boolean reachedBorder(Line trajectory) {

        boolean reachedBorder = false;

        //checking top and bottom borders of the screen

        double upperLeftY = this.gameLevel.getBorders()[BOTTOM].getCollisionRectangle().getUpperLeft().getY();
        double lowerLeftY = this.gameLevel.getBorders()[TOP].getCollisionRectangle().getLowerLeft().getY();
        double upperLeftX = this.gameLevel.getBorders()[RIGHT].getCollisionRectangle().getUpperLeft().getX();
        double upperRightX = this.gameLevel.getBorders()[LEFT].getCollisionRectangle().getUpperRight().getX();

        if ((this.velocity.getDy() > 0
                && (trajectory.start().getY() >= upperLeftY || equals(trajectory.start().getY(), upperLeftY)))
                || (this.velocity.getDy() < 0 && (trajectory.start().getY() <= lowerLeftY))
                || equals(trajectory.start().getY(), lowerLeftY)) {
            this.velocity.setDy(-this.velocity.getDy());
            reachedBorder = true;
        }

        //checking left and right borders of the screen
        if (this.velocity.getDx() > 0
                && (trajectory.start().getX() >= upperLeftX || equals(trajectory.start().getX(), upperLeftX))
                || (this.velocity.getDx() < 0 && (trajectory.start().getX() <= upperRightX))
                || equals(trajectory.start().getX(), upperRightX)) {
            this.velocity.setDx(-this.velocity.getDx());
            reachedBorder = true;
        }

        return reachedBorder;
    }

    /**
     * Returns predicted trajectory.
     *
     * @return predicted trajectory
     */
    private Line computeTrajectory() {

        //current position of the center of the ball
        Point start = new Point(this.center.getX(), this.center.getY());

        //position of the tip of the ball (center + radius) at the end of trajectory
        Point end = new Point(start.getX() + 1.2 * this.velocity.getDx(), start.getY() + 1.2 * this.velocity.getDy());

        //adding the radius towards the movement of the ball
        if (this.velocity.getDx() > 0) {
            end.setX(end.getX() + radius);
        } else if (this.velocity.getDx() < 0) {
            end.setX(end.getX() - radius);
        }

        if (this.velocity.getDy() > 0) {
            end.setY(end.getY() + radius);
        } else if (this.velocity.getDy() < 0) {
            end.setY(end.getY() - radius);
        }

        Line trajectory = new Line(start, end);

        return trajectory;
    }

    @Override
    /**
     * returns a String that represents this ball.
     * @return String that represents this ball
     * @overrides toString() in class java.lang.Object
     */
    public String toString() {
        return "Ball:\tCenter: " + this.center + "\tRadius: " + this.radius + "\tColor: " + this.color;
    }
}
