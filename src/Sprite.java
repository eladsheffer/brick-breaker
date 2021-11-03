// ID 031824915

import biuoop.DrawSurface;

/**
 * @author Elad Sheffer
 * This interface represents a sprite.
 */
public interface Sprite {

    /**
     * Draw the sprite to the screen.
     * @param d surface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Add sprite to gameLevel.
     * @param gameLevel to add to
     */
    void addToGame(GameLevel gameLevel);
}