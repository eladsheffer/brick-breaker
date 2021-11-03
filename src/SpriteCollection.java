// ID 031824915

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elad Sheffer
 * This class represents a Sprite collection
 */
public class SpriteCollection {

    //attributes
    private ArrayList<Sprite> sprites;

    /**
     * Creates a sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * Adds a sprite to the sprite collection.
     *
     * @param s sprite
     */
    public void addSprite(Sprite s) {
        if (this.sprites != null) {
            sprites.add(s);
        }
    }

    /**
     * Remove a sprite from the sprite collection.
     *
     * @param s sprite
     */
    public void removeSprite(Sprite s) {
        if (this.sprites != null) {
            sprites.remove(s);
        }
    }

    /**
     * Calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        if (this.sprites == null) {
            return;
        }

        List<Sprite> spritesCopy = new ArrayList<Sprite>(this.sprites);
        for (Sprite sprite : spritesCopy) {
            sprite.timePassed();
        }
    }

    /**
     * Calls drawOn(d) on all sprites.
     *
     * @param d surface to draw sprites on
     */
    public void drawAllOn(DrawSurface d) {
        if (this.sprites == null) {
            return;
        }

        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }
    }

    /**
     * Returns a String that represents this sprite collection.
     *
     * @return String that represents this sprite collection
     * @overrides toString() in class java.lang.Object
     */
    @Override
    public String toString() {

        StringBuilder spriteString = new StringBuilder();
        for (int i = 0; i < this.sprites.size(); i++) {
            spriteString.append(i + 1).append(". ").append(this.sprites.get(i)).append("\n");
        }
        return "\nSprite Collection:\n" + "sprites:\n" + spriteString;
    }
}