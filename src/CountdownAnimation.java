import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * @author Elad Sheffer
 * This class represents a countdown animation
 * The CountdownAnimation will display the given gameScreen, for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will appear on the screen for (numOfSeconds / countFrom)
 * seconds, before it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private Sleeper sleeper;
    private int count;

    /**
     * Creates countdown animation.
     *
     * @param numOfSeconds of the countdown.
     * @param countFrom    number to count from
     * @param gameScreen   sprite collection
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.count = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.sleeper = new Sleeper();
    }

    /**
     * Does one frame.
     *
     * @param d surface to draw on
     */
    public void doOneFrame(DrawSurface d) {

        long timeForSleep = (long) (1000 * numOfSeconds / countFrom);

        this.gameScreen.drawAllOn(d);
        d.setColor(Color.GRAY);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(count), 32);
        if (count < this.countFrom) {
            sleeper.sleepFor(timeForSleep);
        }
        this.count--;
        if (this.count < 0) {
            this.stop = true;
        }
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