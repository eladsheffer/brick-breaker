// ID 031824915

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Elad Sheffer
 * This class represents a game
 */
public class GameLevel implements Animation {

    //constants
    private static final int BORDERS = 4;
    private static final int TOP = 0;
    private static final int BOTTOM = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;

    private static final int BORDER_THICKNESS = 20;

    private static final int HEIGHT_PADDLE = 20;

    private static final int BONUS_FOR_EMPTYING_SCREEN = 100;
    private static final int RADIUS_BALL = 7;

    //attributes
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private KeyboardSensor keyboardSensor;
    private Block[] borders;
    private List<Color> colors;
    private Counter blockCounter;
    private BlockRemover blockRemover;
    private Counter ballCounter;
    private BallRemover ballRemover;
    private Counter score;
    private ScoreTrackingListener scoreTrackingListener;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInformation;
    private Point upperLeftPaddle;

    //constructor

    /**
     * Creates a game level.
     *
     * @param levelInformation level information
     * @param keyboardSensor   keyboard sensor
     * @param animationRunner  animation runner
     * @param score            score
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, Counter score) {
        this.keyboardSensor = keyboardSensor;
        this.runner = animationRunner;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.borders = new Block[BORDERS];
        this.colors = new ArrayList<>();
        this.colors = List.of(Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN);
        this.score = score;
        this.blockCounter = new Counter();
        this.blockRemover = new BlockRemover(this, this.blockCounter);
        this.ballCounter = new Counter();
        this.ballRemover = new BallRemover(this, this.ballCounter);
        this.scoreTrackingListener = new ScoreTrackingListener(this.score);
        this.levelInformation = levelInformation;
    }

    //accessors

    /**
     * Returns game environment.
     *
     * @return environment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Returns sprite collection.
     *
     * @return sprite collection
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * Returns the borders of the game.
     *
     * @return of the program
     */
    public Block[] getBorders() {
        return borders;
    }

    /**
     * Returns block counter.
     *
     * @return block counter
     */
    public Counter getBlockCounter() {
        return this.blockCounter;
    }

    /**
     * Returns ball counter.
     *
     * @return ball counter
     */
    public Counter getBallCounter() {
        return ballCounter;
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
     * Returns border thickness.
     *
     * @return border thickness
     */
    public static int getBorderThickness() {
        return BORDER_THICKNESS;
    }

    /**
     * Returns block remover.
     *
     * @return block remover
     */
    public BlockRemover getBlockRemover() {
        return blockRemover;
    }

    /**
     * Returns score tracking listener.
     *
     * @return score tracking listener
     */
    public ScoreTrackingListener getScoreTrackingListener() {
        return scoreTrackingListener;
    }

    //mutators

    /**
     * Sets game environment.
     *
     * @param e environment to set
     */
    public void setEnvironment(GameEnvironment e) {
        this.environment = e;
    }

    /**
     * Sets sprite collection.
     *
     * @param s sprites to set
     */
    public void setSprites(SpriteCollection s) {
        this.sprites = s;
    }

    //methods

    /**
     * Adds a collidable to the game environment.
     *
     * @param c collidable to add to game environment
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Removes a collidable from the game environment.
     *
     * @param c collidable to add from game environment
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Adds a sprite to the sprite collection.
     *
     * @param s sprite to add to sprite collection
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes a sprite from the sprite collection.
     *
     * @param s sprite to remove from sprite collection
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initializes a new game: creates the Blocks and Ball (and Paddle) and adds them to the game.
     */
    public void initialize() {

        this.levelInformation.getBackground().addToGame(this);

        drawBlocks();
        drawBorders();
        drawPaddle();
        drawBalls();
        drawScoreIndicator();
        drawLevelName();
    }

    /**
     * Draws level name.
     */
    private void drawLevelName() {
        int positionX = AnimationRunner.getWidthScreen() * 3 / 4;
        int positionY = (int) borders[TOP].getCollisionRectangle().getUpperRight().getY() + BORDER_THICKNESS - 3;
        int fontSize = BORDER_THICKNESS - 2;
        Header levelNameDisplay =
                new Header("Level Name: " + this.levelInformation.levelName(), Color.BLACK, positionX, positionY,
                        fontSize);
        levelNameDisplay.addToGame(this);
    }

    /**
     * Draws the counter indicator of the game.
     */
    private void drawScoreIndicator() {
        int positionX = AnimationRunner.getWidthScreen() / 2;
        int positionY = (int) borders[TOP].getCollisionRectangle().getUpperRight().getY() + BORDER_THICKNESS - 3;
        int fontSize = BORDER_THICKNESS - 2;
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score, positionX, positionY, fontSize);
        scoreIndicator.addToGame(this);
    }

    /**
     * Draws the borders of the game.
     */
    private void drawBorders() {
        this.borders[TOP] = new Block(new Point(0, 0), AnimationRunner.getWidthScreen(), BORDER_THICKNESS, Color.GRAY);
        this.borders[BOTTOM] =
                new Block(new Point(0, AnimationRunner.getHeightScreen()), AnimationRunner.getWidthScreen(), 0,
                        Color.GRAY);
        this.borders[LEFT] =
                new Block(new Point(0, 0), BORDER_THICKNESS, AnimationRunner.getHeightScreen(), Color.GRAY);
        this.borders[RIGHT] =
                new Block(new Point(AnimationRunner.getWidthScreen() - BORDER_THICKNESS, 0), BORDER_THICKNESS,
                        AnimationRunner.getHeightScreen(), Color.GRAY);

        this.borders[TOP].addToGameAsBorder(this);
        this.borders[BOTTOM].addToGameAsBorder(this);
        this.borders[LEFT].addToGameAsBorder(this);
        this.borders[RIGHT].addToGameAsBorder(this);
        this.borders[BOTTOM].addHitListener(ballRemover);
    }

    /**
     * Draws the blocks of the game.
     */
    private void drawBlocks() {
        for (int i = 0; i < this.levelInformation.numberOfBlocksToRemove(); i++) {
            this.levelInformation.blocks().get(i).addToGame(this);
            this.levelInformation.blocks().get(i).addHitListener(this.blockRemover);
            this.levelInformation.blocks().get(i).addHitListener(this.scoreTrackingListener);
        }
    }

    /**
     * Draws the paddle of the game.
     */
    private void drawPaddle() {
        this.upperLeftPaddle =
                new Point(AnimationRunner.getWidthScreen() / 2.0 - this.levelInformation.paddleWidth() / 2.0,
                        AnimationRunner.getHeightScreen() - BORDER_THICKNESS - HEIGHT_PADDLE);
        Paddle paddle =
                new Paddle(keyboardSensor, this.upperLeftPaddle, this.levelInformation.paddleWidth(), HEIGHT_PADDLE,
                        Color.ORANGE, this.levelInformation.paddleSpeed(), this);
        paddle.addToGame(this);
    }

    /**
     * Draws the balls of the game.
     */
    private void drawBalls() {

        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball((int) this.upperLeftPaddle.getX()
                    + (i + 1) * this.levelInformation.paddleWidth() / (this.levelInformation.numberOfBalls() + 1),
                    (int) this.upperLeftPaddle.getY() - RADIUS_BALL, RADIUS_BALL,
                    this.colors.get(i % this.colors.size()), this);


            ball.addToGame(this);
            Velocity v = this.levelInformation.initialBallVelocities().get(i);
            ball.setVelocity(v);
        }
    }

    /**
     * Runs the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2.0, 3, this.sprites));
        this.running = true;
        this.runner.run(this);

        if (this.blockCounter.getValue() <= 0) {
            this.score.increase(BONUS_FOR_EMPTYING_SCREEN);
        }

    }

    /**
     * Returns a String that represents this game.
     *
     * @return String that represents this game
     * @overrides toString() in class java.lang.Object
     */
    @Override
    public String toString() {
        return "GameLevel:" + "\nsprites=" + sprites + "\nenvironment=" + environment;
    }

    /**
     * Does one frame.
     *
     * @param d surface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.ballCounter.getValue() <= 0 || this.blockCounter.getValue() <= 0) {
            this.running = false;
        }

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (this.keyboardSensor.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.keyboardSensor)));
        }
    }

    /**
     * Stops the game.
     *
     * @return true if the game should stop, false - otherwise
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}