// ID 031824915

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Elad Sheffer
 * This class represents a pause screen
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Creates a pause screen.
     * @param k keyboard
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * Does one frame.
     *
     * @param d surface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);

    }

    /**
     * Stops the game.
     *
     * @return true if the game should stop, false - otherwise
     */
    public boolean shouldStop() {
        return this.stop;
    }
}