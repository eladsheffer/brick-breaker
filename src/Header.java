// ID 031824915

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Elad Sheffer
 * This class represents a level name display
 */
public class Header implements Sprite {

    //attributes
    private String textToDisplay;
    private Color color;
    private int positionX;
    private int positionY;
    private int fontSize;

    //constructors

    /**
     * Creates level name Display.
     *
     * @param textToDisplay of the display
     * @param color         of the display
     * @param positionX     of the display
     * @param positionY     of the display
     * @param fontSize      of the display
     */
    public Header(String textToDisplay, Color color, int positionX, int positionY, int fontSize) {
        this.textToDisplay = textToDisplay;
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
        this.fontSize = fontSize;
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param d surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(this.positionX, this.positionY, this.textToDisplay, this.fontSize);
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
