// ID 031824915

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Elad Sheffer
 * This class represents keypress stoppable animation
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Creates keypress stoppable animation.
     *
     * @param sensor    keyboard sensor
     * @param key       key
     * @param animation animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * Does one frame.
     *
     * @param d surface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.sensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * Stops the game.
     *
     * @return true if the game should stop, false - otherwise
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}