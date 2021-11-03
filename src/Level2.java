// ID 031824915

import java.awt.Color;

/**
 * @author Elad Sheffer
 * This class represents level 2
 */
public class Level2 extends LevelBase {

    //constants
    private static final int HEIGHT_BLOCK = 20;
    private static final int WIDTH_BLOCK = 42;

    private static final int NUMBER_OF_BALLS = 10;

    private static final int WIDTH_PADDLE = 600;
    private static final int SPEED_OF_PADDLE = 5;
    private static final String LEVEL_NAME = "Wide Easy";

    // constructors

    /**
     * Creates level 2 information.
     */
    public Level2() {
        this.setBackground(new Background(Color.WHITE));
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
            Velocity velocity = Velocity.fromAngleAndSpeed(-100 + i * 40, 3);
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

        double colorNumber = 0;

        for (int i = GameLevel.getBorderThickness();
             i < AnimationRunner.getWidthScreen() - GameLevel.getBorderThickness() - WIDTH_BLOCK;
             i += WIDTH_BLOCK) {
            Block block = new Block(new Point(i, AnimationRunner.getHeightScreen() / 5.0),
                    WIDTH_BLOCK, HEIGHT_BLOCK,
                    this.getColors().get((int) (colorNumber) % this.getColors().size()));
            this.blocks().add(block);
            colorNumber += 0.5;
        }

    }
}
