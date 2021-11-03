// ID 031824915

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elad Sheffer
 * This class represents Arkadroid Brick Breaker Gameplay
 */
public class BrickBreaker {

    /**
     * Main of the program.
     *
     * @param args of the program
     */
    public static void main(String[] args) {
        AnimationRunner animationRunner = new AnimationRunner();
        GameFlow gameFlow = new GameFlow(animationRunner);
        List<LevelInformation> levels = new ArrayList<>();

        if (args.length > 0) {
            label:
            for (String arg : args) {
                LevelInformation level;
                switch (arg) {
                    case "1":
                        level = new Level1();
                        break;
                    case "2":
                        level = new Level2();
                        break;
                    case "3":
                        level = new Level3();
                        break;
                    case "4":
                        level = new Level4();
                        break;
                    default:
                        continue label;
                }
                levels.add(level);

            }

            if (levels.size() == 0) {
                addAllLevels(levels);
            }

        } else {

            addAllLevels(levels);
        }

        gameFlow.runLevels(levels);

    }

    /**
     * Adds all levels to the list of levels.
     *
     * @param levels list of levels
     */
    private static void addAllLevels(List<LevelInformation> levels) {
        LevelInformation level1 = new Level1();
        LevelInformation level2 = new Level2();
        LevelInformation level3 = new Level3();
        LevelInformation level4 = new Level4();

        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
        levels.add(level4);
    }
}
