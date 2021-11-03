// ID 031824915

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Elad Sheffer
 * This class represents a block
 */
public class Block implements Collidable, Sprite, HitNotifier {

    //constants
    private static final int TOP = 0;
    private static final int BOTTOM = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;

    //attributes
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;
    private boolean isBorder;

    //constructors

    /**
     * Creates a Block.
     *
     * @param upperLeft point of the block.
     * @param width     of th block
     * @param height    of the block
     * @param color     of the block
     */
    public Block(Point upperLeft, double width, double height, Color color) {

        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<>();
        this.isBorder = false;
    }

    /**
     * Creates a Block.
     *
     * @param upperLeft point of the block.
     * @param width     of th block
     * @param height    of the block
     * @param color     of the block
     * @param isBorder  true if the block is border, false - otherwise
     */
    public Block(Point upperLeft, double width, double height, Color color, boolean isBorder) {

        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<>();
        this.isBorder = isBorder;
    }

    //accessors

    @Override
    /**
     * Return the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Returns hit listeners.
     *
     * @return hit listeners.
     */
    public List<HitListener> getHitListeners() {
        return hitListeners;
    }

    //methods

    @Override
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

        //checking if the point of collision in on the top or bottom of the block
        //and if so - reversing the vertical velocity of the ball
        if ((lines[TOP].isPointInSegment(collisionPoint.getX(), collisionPoint.getY())
                && currentVelocity.getDy() > 0)
                || (lines[BOTTOM].isPointInSegment(collisionPoint.getX(), collisionPoint.getY())
                && currentVelocity.getDy() < 0)) {
            newVelocity.setDy(-currentVelocity.getDy());
        }

        //checking if the point of collision in on the left or right of the block
        //and if so - reversing the horizontal velocity of the ball
        if ((lines[LEFT].isPointInSegment(collisionPoint.getX(), collisionPoint.getY())
                && currentVelocity.getDx() > 0)
                || (lines[RIGHT].isPointInSegment(collisionPoint.getX(), collisionPoint.getY())
                && currentVelocity.getDx() < 0)) {
            newVelocity.setDx(-currentVelocity.getDx());
        }

        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * Draws the block on the given DrawSurface.
     *
     * @param surface the block is drawn on
     */
    public void drawOn(DrawSurface surface) {
        //setting the color of the block on the surface
        surface.setColor(this.color);

        //setting the block on the surface
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX() + 2,
                (int) this.rectangle.getUpperLeft().getY() + 2,
                (int) this.rectangle.getWidth() - 3, (int) this.rectangle.getHeight() - 3);

        //setting the frame of block on the surface
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());


    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Add block to game.
     *
     * @param g game to add block to
     */
    @Override
    public void addToGame(GameLevel g) {
        if (g != null) {
            this.addToGameAsBorder(g);
            g.getBlockCounter().increase(1);
        }
    }

    /**
     * Add block to game.
     *
     * @param g game to add block to
     */
    public void addToGameAsBorder(GameLevel g) {
        if (g != null) {
            g.addSprite(this);
            g.addCollidable(this);
        }
    }

    /**
     * Remove block from game.
     *
     * @param g to remove block from
     */
    public void removeFromGame(GameLevel g) {
        if (g != null) {
            g.removeSprite(this);
            g.removeCollidable(this);
            g.getBlockCounter().decrease(1);
        }
    }

    @Override
    /**
     * returns a String that represents this block.
     * @return String that represents this block
     * @overrides toString() in class java.lang.Object
     */
    public String toString() {
        return "Block: " + this.rectangle + "\ncolor: " + this.color;
    }

    /**
     * Adds hl as a listener to hit events.
     *
     * @param hl hit listener to add.
     */
    @Override
    public void addHitListener(HitListener hl) {
        if (this.hitListeners != null) {
            hitListeners.add(hl);
        }
    }

    /**
     * Removes hl from the list of listeners to hit events.
     *
     * @param hl hit listener to remove.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        if (this.hitListeners != null) {
            hitListeners.remove(hl);
        }
    }

    /**
     * Notifies the listeners about hit event.
     *
     * @param hitter the ball hitting
     */
    private void notifyHit(Ball hitter) {

        if (this.hitListeners == null) {
            return;
        }

        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
