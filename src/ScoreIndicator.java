// ID 031824915

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Elad Sheffer
 * This class represents a score indicator
 */
public class ScoreIndicator implements Sprite {

    //attributes
    private Counter score;
    private int positionX;
    private int positionY;
    private int fontSize;

    //constructor

    /**
     * Creates a score indicator.
     *
     * @param score to display
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Creates a score indicator.
     *
     * @param score     to display
     * @param positionX of the score
     * @param positionY of the score
     * @param fontSize  of the score
     */
    public ScoreIndicator(Counter score, int positionX, int positionY, int fontSize) {
        this.score = score;
        this.positionX = positionX;
        this.positionY = positionY;
        this.fontSize = fontSize;
    }

    //methods

    /**
     * Draw the sprite to the screen.
     *
     * @param d surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(this.positionX, this.positionY, "Score: " + this.score.toString(), this.fontSize);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Add sprite to game.
     *
     * @param g game to add to
     */
    @Override
    public void addToGame(GameLevel g) {
        if (g != null) {
            g.addSprite(this);
        }
    }

    @Override
    /**
     * returns a String that represents this ball.
     * @return String that represents this ball
     * @overrides toString() in class java.lang.Object
     */
    public String toString() {
        return "Score: " + this.score.toString();
    }
}
