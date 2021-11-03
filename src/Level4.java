// ID 031824915

import java.awt.Color;

/**
 * @author Elad Sheffer
 * This class represents level 4
 */
public class Level4 extends LevelBase {

    //constants
    private static final int HEIGHT_BLOCK = 20;
    private static final int WIDTH_BLOCK = 50;
    private static final int NUM_OF_ROWS_OF_BLOCKS = 7;
    private static final int INITIAL_HEIGHT_OF_BLOCKS = 100;

    private static final int NUMBER_OF_BALLS = 3;

    private static final int WIDTH_PADDLE = 100;
    private static final int SPEED_OF_PADDLE = 5;
    private static final String LEVEL_NAME = "Final Four";

    // constructors

    /**
     * Creates level 4 information.
     */
    public Level4() {
        this.setBackground(new Background(Color.cyan.darker()));
        this.setNumberOfBalls(NUMBER_OF_BALLS);
        setVelocities();

        this.setPaddleSpeed(SPEED_OF_PADDLE);
        this.setPaddleWidth(WIDTH_PADDLE);
        this.setLevelName(LEVEL_NAME);

        setBlocks();
    }

    /**
     * Sets the velocities of balls.
     */
    private void setVelocities() {
        if (this.initialBallVelocities() == null) {
            return;
        }
        for (int i = 0; i < this.numberOfBalls(); i++) {
            Velocity velocity = Velocity.fromAngleAndSpeed(60 + 100 * i, 3);
            this.initialBallVelocities().add(velocity);
        }
    }

    /**
     * Sets blocks.
     */
    private void setBlocks() {
        if (this.blocks() == null) {
            return;
        }

        int startWidth = AnimationRunner.getWidthScreen() - GameLevel.getBorderThickness() - WIDTH_BLOCK;
        int startHeight = INITIAL_HEIGHT_OF_BLOCKS;

        int rowCount = 0;
        for (int rows = startHeight; rows < startHeight + NUM_OF_ROWS_OF_BLOCKS * HEIGHT_BLOCK;
             rows += HEIGHT_BLOCK) {

            for (int cols = GameLevel.getBorderThickness();
                 cols < AnimationRunner.getWidthScreen() - GameLevel.getBorderThickness() - WIDTH_BLOCK;
                 cols += WIDTH_BLOCK) {
                Block block = new Block(new Point(cols, rows), WIDTH_BLOCK, HEIGHT_BLOCK,
                        this.getColors().get(rowCount % this.getColors().size()));
                this.blocks().add(block);
            }
            rowCount++;

        }

    }


}
