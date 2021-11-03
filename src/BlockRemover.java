// ID 031824915

/**
 * @author Elad Sheffer
 * This class represents a block remover which is in charge of removing block from the gameLevel.
 * as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {

    //attributes
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    //constructor

    /**
     * Creates a block remover.
     *
     * @param gameLevel          to remove blocks from
     * @param remainingBlocks count of remaining blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;

    }

    //methods

    /**
     * Blocks that are hit should be removed from the gameLevel.
     * Removal of this listener from the block that is being removed from the gameLevel is needed
     *
     * @param beingHit block that's being hit.
     * @param hitter   the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
    }
}