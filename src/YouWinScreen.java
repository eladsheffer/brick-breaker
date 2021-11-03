// ID 031824915

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author Elad Sheffer
 * This class represents a you win screen
 */
public class YouWinScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;

    /**
     * Creates a you win screen.
     *
     * @param k     keyboard sensor
     * @param score score
     */
    public YouWinScreen(KeyboardSensor k, Counter score) {
        this.keyboard = k;
        this.score = score;
        this.stop = false;
    }

    /**
     * Does one frame.
     *
     * @param d surface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLUE.darker().darker());
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.RED);
        d.fillCircle(d.getWidth() / 2 - 100, d.getHeight() / 2, 200);
        d.setColor(Color.WHITE);
        d.drawText(d.getWidth() / 4, d.getHeight() / 2, "You Win!!!", 32);
        d.drawText(d.getWidth() / 4, d.getHeight() / 2 + 40, "Your Score is " + this.score, 32);
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