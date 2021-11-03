// ID 031824915

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Elad Sheffer
 * This class represents background
 */
public class Background implements Sprite {

    //attributes
    private Color color;

    //constructors

    /**
     * Creates background.
     *
     * @param color of the background
     */
    public Background(Color color) {
        this.color = color;
    }

    //methods

    /**
     * Draw the sprite to the screen.
     *
     * @param d surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Add sprite to gameLevel.
     *
     * @param g to add to
     */
    @Override
    public void addToGame(GameLevel g) {
        if (g != null) {
            g.addSprite(this);
        }
    }
}
