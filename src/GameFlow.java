// ID 031824915

import biuoop.KeyboardSensor;

import java.util.List;

/**
 * @author Elad Sheffer
 * This class represents a game
 */
public class GameFlow {

    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score;

    //constructor

    /**
     * Creates game flow.
     *
     * @param ar animation runner
     */
    public GameFlow(AnimationRunner ar) {
        this.animationRunner = ar;
        this.keyboardSensor = this.animationRunner.getGui().getKeyboardSensor();
        this.score = new Counter();
    }

    /**
     * Runs all the levels.
     *
     * @param levels to run
     */
    public void runLevels(List<LevelInformation> levels) {

        if (levels == null || levels.size() == 0) {
            return;
        }

        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score);

            level.initialize();

            while (level.getBlockCounter().getValue() > 0 && level.getBallCounter().getValue() > 0) {
                level.run();
            }

            if (level.getBallCounter().getValue() <= 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new GameOverScreen(this.keyboardSensor, this.score)));
                this.animationRunner.getGui().close();
                break;
            }

        }

        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new YouWinScreen(this.keyboardSensor, this.score)));
        this.animationRunner.getGui().close();
    }
}