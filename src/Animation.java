// ID 031824915

import biuoop.DrawSurface;

/**
 * @author Elad Sheffer
 * This interface represents Animation.
 */
public interface Animation {

    /**
     * Does one frame.
     * @param d surface to draw on
     */
    void doOneFrame(DrawSurface d);

    /**
     * Stops the game.
     * @return true if the game should stop, false - otherwise
     */
    boolean shouldStop();
}