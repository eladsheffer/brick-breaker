// ID 031824915

/**
 * @author Elad Sheffer
 * This class represents collision info
 */
public class CollisionInfo {

    //attributes
    private Point collisionPoint;
    private Collidable collidable;

    //constructors

    /**
     * Creates a collision info with collision point and collidable.
     *
     * @param collisionPoint collision point
     * @param collidable     collidable
     */
    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collisionPoint = collisionPoint;
        this.collidable = collidable;
    }

    //accessors

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }

    //methods

    @Override
    /**
     * returns a String that represents this collision info.
     * @return String that represents this collision info
     * @overrides toString() in class java.lang.Object
     */
    public String toString() {
        return "CollisionInfo:\t" + "collisionPoint = " + this.collisionPoint + ", collidable = " + this.collidable;
    }
}