// ID 031824915

import java.util.ArrayList;

/**
 * @author Elad Sheffer
 * This class represents a game environment
 */
public class GameEnvironment {

    //attributes
    private ArrayList<Collidable> collidables;

    //constructor

    /**
     * Creates a game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    //accessors

    /**
     * Returns the collidable list.
     *
     * @return the collidable list
     */
    public ArrayList<Collidable> getCollidables() {
        return collidables;
    }

    //methods

    /**
     * Add the given collidable to the environment.
     *
     * @param c collidable
     */
    public void addCollidable(Collidable c) {
        if (this.collidables != null) {
            collidables.add(c);
        }
    }

    /**
     * Remove the given collidable from the environment.
     *
     * @param c collidable
     */
    public void removeCollidable(Collidable c) {
        if (this.collidables != null) {
            collidables.remove(c);
        }
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory of the object moving
     * @return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        if (this.collidables == null || this.collidables.size() == 0 || trajectory == null) {
            return null;
        }

        double minDistance = Double.MAX_VALUE;
        Collidable collidableOfCollision = null;

        //setting the closest intersection with the first collidable in the list of collidables
        Point closestCollisionPoint =
                trajectory.closestIntersectionToStartOfLine(collidables.get(0).getCollisionRectangle());

        if (closestCollisionPoint != null) {
            minDistance = trajectory.start().distance(closestCollisionPoint);
            collidableOfCollision = collidables.get(0);
        }

        //looping over the collidable list and finding the collidable with the closest collision point
        for (int i = 1; i < collidables.size(); i++) {
            double distance = Double.MAX_VALUE;
            Point collisionPoint =
                    trajectory.closestIntersectionToStartOfLine(collidables.get(i).getCollisionRectangle());
            if (collisionPoint != null) {
                distance = trajectory.start().distance(collisionPoint);
            }
            if (distance < minDistance) {
                minDistance = distance;
                closestCollisionPoint = collisionPoint;
                collidableOfCollision = collidables.get(i);
            }
        }

        //creating collision info with the collision point and collidable object
        CollisionInfo collisionInfo = new CollisionInfo(closestCollisionPoint, collidableOfCollision);

        return collisionInfo;
    }

    @Override
    /**
     * returns a String that represents this game environment.
     * @return String that represents this game environment
     * @overrides toString() in class java.lang.Object
     */
    public String toString() {
        StringBuilder collidableString = new StringBuilder();
        for (int i = 0; i < this.collidables.size(); i++) {
            collidableString.append(i + 1).append(". ").append(this.collidables.get(i)).append("\n");
        }
        return "\nGameEnvironment:\n" + "collidables:\n" + collidableString;
    }
}