// ID 031824915

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Elad Sheffer
 * This class represents background
 */
public class Background1 implements Sprite {

    //constants
    private static final int RADIUS_START = 30;
    private static final int NUMBER_OF_CIRCLES = 3;
    private static final int SPREAD_BETWEEN_CIRCLES = 30;
    private static final int END_FAR_FROM_CIRCLE = 150;
    private static final int END_CLOSE_TO_CIRCLE = 20;

    //attributes
    private LevelInformation levelInformation;
    private int centerX;
    private int centerY;

    //constructors

    /**
     * Creates background.
     * @param levelInformation level information
     */
    public Background1(LevelInformation levelInformation) {
        this.levelInformation = levelInformation;
        this.centerX = (int) (this.levelInformation.blocks().get(0).getCollisionRectangle().getUpperLeft().getX()
                + this.levelInformation.blocks().get(0).getCollisionRectangle().getWidth() / 2);
        this.centerY = (int) (this.levelInformation.blocks().get(0).getCollisionRectangle().getUpperLeft().getY()
                + this.levelInformation.blocks().get(0).getCollisionRectangle().getHeight() / 2);
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
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        d.setColor(Color.BLUE);
        for (int i = RADIUS_START; i <= NUMBER_OF_CIRCLES * SPREAD_BETWEEN_CIRCLES; i += SPREAD_BETWEEN_CIRCLES) {
            d.drawCircle(this.centerX, this.centerY, i);
        }

        d.drawLine(this.centerX, this.centerY - END_FAR_FROM_CIRCLE, this.centerX, this.centerY - END_CLOSE_TO_CIRCLE);
        d.drawLine(this.centerX, this.centerY + END_CLOSE_TO_CIRCLE, this.centerX, this.centerY + END_FAR_FROM_CIRCLE);
        d.drawLine(this.centerX - END_FAR_FROM_CIRCLE, this.centerY, this.centerX - END_CLOSE_TO_CIRCLE, this.centerY);
        d.drawLine(this.centerX + END_CLOSE_TO_CIRCLE, this.centerY, this.centerX + END_FAR_FROM_CIRCLE, this.centerY);
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
