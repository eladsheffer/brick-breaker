// ID 031824915

/**
 * @author Elad Sheffer
 * This class represents a ball remover which is in charge of removing ball from the gameLevel.
 * as well as keeping count of the number of balls that remain.
 */
public class BallRemover implements HitListener {

    //attributes
    private GameLevel gameLevel;
    private Counter remainingBalls;

    //constructor

    /**
     * Creates a ball remover.
     *
     * @param gameLevel           to remove blocks from
     * @param remainingBalls count of remaining balls
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;

    }

    //methods

    /**
     * Balls that hit the bottom should be removed from the gameLevel.
     * Removal of this listener from the ball that is being removed from the gameLevel is needed
     *
     * @param beingHit ball that's being hit.
     * @param hitter   the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
    }
}