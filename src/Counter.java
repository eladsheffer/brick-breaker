// ID 031824915

/**
 * @author Elad Sheffer
 * This interface represents a counter
 */
public class Counter {

    //attributes
    private int count;

    /**
     * Creates a counter.
     */
    public Counter() {
        this.count = 0;
    }

    //accessors

    /**
     * Get current count.
     *
     * @return current count.
     */
    int getValue() {
        return this.count;
    }

    //methods

    /**
     * Add number to current count.
     *
     * @param number to add to current count.
     */
    void increase(int number) {
        this.count += number;

    }

    /**
     * Subtract number from current count.
     *
     * @param number to subtract from current count.
     */
    void decrease(int number) {
        if (this.count > 0) {
            this.count -= number;
        }

    }

    @Override
    /**
     * returns a String that represents this velocity.
     * @return String that represents this velocity
     * @overrides toString() in class java.lang.Object
     */
    public String toString() {
        return Integer.toString(this.count);
    }
}