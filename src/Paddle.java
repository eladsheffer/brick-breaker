// ID 031824915

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author Elad Sheffer
 * This class represents a paddle
 */
public class Paddle implements Sprite, Collidable {

    //constants
    private static final int TOP = 0;
    private static final int BOTTOM = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;
    private static final int ZONE_1 = 330;
    private static final int ZONE_2 = 300;
    private static final int ZONE_3 = 0;
    private static final int ZONE_4 = 30;
    private static final int ZONE_5 = 60;
    private static final double NUM_OF_ZONES = 5.0;

    //attributes
    private KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    private GameLevel gameLevel;
    private int speed;

    //constructor

    /**
     * Creates a paddle.
     *
     * @param keyboard  object to move the paddle with
     * @param upperLeft point of the paddle
     * @param width     of the paddle
     * @param height    of the paddle
     * @param color     of the paddle
     * @param speed     of the paddle
     * @param gameLevel      on which the paddle is drawn
     */
    public Paddle(KeyboardSensor keyboard, Point upperLeft, double width, double height, Color color, int speed,
                  GameLevel gameLevel) {
        this.keyboard = keyboard;
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.speed = speed;
        this.gameLevel = gameLevel;
    }

    //accessors

    /**
     * Return the speed of the paddle.
     *
     * @return the speed of the paddle
     */
    public int getSpeed() {
        return speed;
    }

    //mutators

    /**
     * Sets the speed of the paddle.
     *
     * @param s of the paddle
     */
    public void setSpeed(int s) {
        this.speed = s;
    }

    //methods

    /**
     * Moves the paddle towards the left edge of the screen.
     */
    public void moveLeft() {

        //getting the left border of the screen
        double leftBorder = this.gameLevel.getBorders()[LEFT].getCollisionRectangle().getUpperRight().getX();

        //checking if the paddle reached the left border of the screen
        if (this.rectangle.getUpperLeft().getX() <= leftBorder) {
            return;
        }

        //moving the paddle to the left
        this.rectangle.setVelocity(-this.speed, 0);
        this.rectangle.setUpperLeft(this.rectangle.getVelocity().applyToPoint(this.rectangle.getUpperLeft()));
    }

    /**
     * Moves the paddle towards the right edge of the screen.
     */
    public void moveRight() {

        //getting the right border of the screen
        double rightBorder = this.gameLevel.getBorders()[RIGHT].getCollisionRectangle().getUpperLeft().getX();

        //checking if the paddle reached the right border of the screen
        if (this.rectangle.getUpperRight().getX() >= rightBorder) {
            return;
        }

        //moving the paddle to the right
        this.rectangle.setVelocity(this.speed, 0);
        this.rectangle.setUpperLeft(this.rectangle.getVelocity().applyToPoint(this.rectangle.getUpperLeft()));
    }

    // Sprite

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {

        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param surface the block is drawn on
     */
    public void drawOn(DrawSurface surface) {
        //setting the color of the paddle on the surface
        surface.setColor(this.color);

        //setting the paddle on the surface
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX() + 2,
                (int) this.rectangle.getUpperLeft().getY() + 2,
                (int) this.rectangle.getWidth() - 3, (int) this.rectangle.getHeight() - 3);

        //setting the frame of block on the surface
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    // Collidable

    /**
     * Return the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param hitter          ball hitting the block
     * @param collisionPoint  with block
     * @param currentVelocity given velocity at time of collision
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint == null) {
            return currentVelocity;
        }

        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());

        //the lines on the block are set in an array
        Line[] lines = this.getCollisionRectangle().getLinesOfRectangle();

        //measuring in which zone of the paddle the ball hit
        double distance = collisionPoint.getX() - this.rectangle.getUpperLeft().getX();
        double length = this.rectangle.getWidth();

        //checking if the point of collision in on the top or bottom of the paddle
        //and if so - changing the angle of the velocity of the ball according to the zone it hit
        if ((lines[TOP].isPointInSegment(collisionPoint.getX(), collisionPoint.getY())
                && currentVelocity.getDy() > 0)
                || (lines[BOTTOM].isPointInSegment(collisionPoint.getX(), collisionPoint.getY())
                && currentVelocity.getDy() < 0)) {

            if (distance / length <= 1 / NUM_OF_ZONES) {
                newVelocity = Velocity.fromAngleAndSpeed(ZONE_1, currentVelocity.getSpeed());
            } else if (distance / length > 1 / NUM_OF_ZONES && distance / length <= 2 / NUM_OF_ZONES) {
                newVelocity = Velocity.fromAngleAndSpeed(ZONE_2, currentVelocity.getSpeed());
            } else if (distance / length > 2 / NUM_OF_ZONES && distance / length <= 3 / NUM_OF_ZONES) {
                newVelocity = Velocity.fromAngleAndSpeed(ZONE_3, currentVelocity.getSpeed());
            } else if (distance / length > 3 / NUM_OF_ZONES && distance / length <= 4 / NUM_OF_ZONES) {
                newVelocity = Velocity.fromAngleAndSpeed(ZONE_4, currentVelocity.getSpeed());
            } else if (distance / length > 4 / NUM_OF_ZONES) {
                newVelocity = Velocity.fromAngleAndSpeed(ZONE_5, currentVelocity.getSpeed());
            }
        }

        //checking if the point of collision in on the left or right of the paddle
        //and if so - changing the angle of the velocity of the ball according to the zone it hit
        if (lines[LEFT].isPointInSegment(collisionPoint.getX(), collisionPoint.getY())
                && currentVelocity.getDx() > 0) {
            newVelocity = Velocity.fromAngleAndSpeed(ZONE_1, currentVelocity.getSpeed());
        } else if (lines[RIGHT].isPointInSegment(collisionPoint.getX(), collisionPoint.getY())
                && currentVelocity.getDx() < 0) {
            newVelocity = Velocity.fromAngleAndSpeed(ZONE_5, currentVelocity.getSpeed());
        }

        return newVelocity;
    }

    /**
     * Add this paddle to the gameLevel.
     *
     * @param g to add to
     */
    public void addToGame(GameLevel g) {
        if (g != null) {
            g.addSprite(this);
            g.addCollidable(this);
        }
    }

    /**
     * returns a String that represents this paddle.
     *
     * @return String that represents this paddle
     * @overrides toString() in class java.lang.Object
     */
    @Override
    public String toString() {
        return "Paddle: " + this.rectangle + "\ncolor: " + color + "\nvelocity: " + this.rectangle.getVelocity();
    }
}