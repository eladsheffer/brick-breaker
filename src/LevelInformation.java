// ID 031824915

import java.util.List;

/**
 * @author Elad Sheffer
 * This interface represents level information.
 */
public interface LevelInformation {

    /**
     * Returns the number of balls.
     *
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the paddle speed.
     *
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * Returns the paddle width.
     *
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * The level name will be displayed at the top of the screen.
     *
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the Blocks that make up this level, each block contains its size, color and location.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return number of blocks that should be removed before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();
}