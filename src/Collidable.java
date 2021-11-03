// ID 031824915

/**
 * @author Elad Sheffer
 * This interface represents a collidable
 */
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param hitter          ball hitting the block
     * @param collisionPoint  with block
     * @param currentVelocity given velocity at time of collision
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
