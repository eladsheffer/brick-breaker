// ID 031824915

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Elad Sheffer
 * This class represents base level
 */
public abstract class LevelBase implements LevelInformation {

    //attributes
    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private List<Color> colors;

    /**
     * Creates level base.
     */
    public LevelBase() {

        this.colors = new ArrayList<>();
        this.colors = List.of(Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN);

        this.initialBallVelocities = new ArrayList<>();

        this.blocks = new ArrayList<>();
    }

    //accessors

    /**
     * Returns the number of balls.
     *
     * @return the number of balls.
     */
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the initial velocity of each ball
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    /**
     * Returns the paddle speed.
     *
     * @return the paddle speed.
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * Returns the paddle width.
     *
     * @return the paddle width.
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * The level name will be displayed at the top of the screen.
     *
     * @return the level name will be displayed at the top of the screen.
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return a sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the Blocks that make up this level, each block contains its size, color and location.
     */
    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return number of blocks that should be removed before the level is considered to be "cleared".
     */
    @Override
    public int numberOfBlocksToRemove() {
        if (this.blocks == null) {
            return 0;
        }

        return this.blocks.size();
    }

    /**
     * Returns color list.
     *
     * @return color list
     */
    public List<Color> getColors() {
        return colors;
    }

    /**
     * Sets number of balls.
     *
     * @param num numberOfBalls to set
     */
    protected void setNumberOfBalls(int num) {
        this.numberOfBalls = num;
    }

    /**
     * Sets paddle speed.
     *
     * @param speed paddleSpeed to set
     */
    protected void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }

    /**
     * Sets paddle width.
     *
     * @param width paddleWidth to set
     */
    protected void setPaddleWidth(int width) {
        this.paddleWidth = width;
    }

    /**
     * Sets level name.
     *
     * @param name levelName to set
     */
    protected void setLevelName(String name) {
        this.levelName = name;
    }

    /**
     * Sets background.
     *
     * @param bg background to set
     */
    protected void setBackground(Sprite bg) {
        this.background = bg;
    }
}
