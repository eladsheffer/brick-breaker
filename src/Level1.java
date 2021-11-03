// ID 031824915

import java.awt.Color;

/**
 * @author Elad Sheffer
 * This class represents level 1
 */
public class Level1 extends LevelBase {

    //constants
    private static final int HEIGHT_BLOCK = 30;
    private static final int WIDTH_BLOCK = 30;

    private static final int NUMBER_OF_BALLS = 1;

    private static final int WIDTH_PADDLE = 100;
    private static final int SPEED_OF_PADDLE = 5;
    private static final String LEVEL_NAME = "Direct Hit";


    // constructors

    /**
     * Creates level 1 information.
     */
    public Level1() {
        //this.setBackground(new Background(Color.BLACK));
        this.setNumberOfBalls(NUMBER_OF_BALLS);
        setVelocities();
        this.setPaddleSpeed(SPEED_OF_PADDLE);
        this.setPaddleWidth(WIDTH_PADDLE);
        this.setLevelName(LEVEL_NAME);
        setBlocks();
        this.setBackground(new Background1(this));
    }

    /**
     * Sets the velocities of balls.
     */
    private void setVelocities() {
        if (this.initialBallVelocities() == null) {
            return;
        }
        for (int i = 0; i < this.numberOfBalls(); i++) {
            Velocity velocity = Velocity.fromAngleAndSpeed(i, 3);
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

        Block block = new Block(new Point(AnimationRunner.getWidthScreen() / 2.0 - GameLevel.getBorderThickness(),
                AnimationRunner.getHeightScreen() / 4.0),
                WIDTH_BLOCK, HEIGHT_BLOCK,
                Color.RED);
        this.blocks().add(block);
    }

}
