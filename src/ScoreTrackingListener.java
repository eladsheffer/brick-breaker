// ID 031824915

/**
 * @author Elad Sheffer
 * This class represents a score tracking listener
 */
public class ScoreTrackingListener implements HitListener {

    //constants
    private static final int SCORE_PER_BLOCK = 5;

    //attributes
    private Counter currentScore;

    //constructor

    /**
     * Creates a score tracking listener.
     *
     * @param scoreCounter to count the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    //methods

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit block that's being hit.
     * @param hitter   the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(SCORE_PER_BLOCK);
    }
}