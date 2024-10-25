package GameCollections;
import GameInterfaces.Sprite;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.LinkedList;
/**
 * @author Hadar Padael.
 * 212916753
 * a class which contains a list of all Sprites.
 */
public class SpriteCollection {
    //class properties:
    private final LinkedList<Sprite> sprites;
    //constructor:

    /**
     * constructor method.
     */
    public SpriteCollection() {
        this.sprites = new LinkedList<>();
    }

    //accessors

    public LinkedList<Sprite> getSprites() {
        return sprites;
    }

    //other methods:

    /**
     * this method adds the sprite to the surface.
     * @param s sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    // call timePassed() on all sprites.

    /**
     * this method activates all sprites timePassed method.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> spritesList = new ArrayList<>(sprites);
        for (Sprite s : spritesList) {
            s.timePassed();
        }
    }

    // call drawOn(d) on all sprites.

    /**
     * this method draws all sprite to the surface.
     * @param d Surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}