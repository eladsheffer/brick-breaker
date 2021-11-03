// ID 031824915

/**
 * @author Elad Sheffer
 * This interface represents a hit notifier.
 */
public interface HitNotifier {

    /**
     * Adds hl as a listener to hit events.
     *
     * @param hl hit listener to add.
     */
    void addHitListener(HitListener hl);

    /**
     * Removes hl from the list of listeners to hit events.
     *
     * @param hl hit listener to remove.
     */
    void removeHitListener(HitListener hl);
}
