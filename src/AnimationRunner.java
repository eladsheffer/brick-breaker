// ID 031824915

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Elad Sheffer
 * This class represents an Animation Runner
 */
public class AnimationRunner {

    private static final int WIDTH_SCREEN = 800;
    private static final int HEIGHT_SCREEN = 600;

    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Creates an animation runner.
     */
    public AnimationRunner() {
        this.gui = new GUI("Arkanoid", WIDTH_SCREEN, HEIGHT_SCREEN);
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * Returns the gui.
     *
     * @return the gui
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Return width of screen.
     *
     * @return width of screen
     */
    public static int getWidthScreen() {
        return WIDTH_SCREEN;
    }

    /**
     * Return height of screen.
     *
     * @return height of screen
     */
    public static int getHeightScreen() {
        return HEIGHT_SCREEN;
    }

    /**
     * Runs the animation.
     *
     * @param animation to run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}