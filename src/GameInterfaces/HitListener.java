package GameInterfaces;

import GameObjects.Ball;
import GameObjects.Block;

/**
 * @author Hadar Padael.
 * 212916753
 * an interface for listener pattern.
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.
    void hitEvent(Block beingHit, Ball hitter);
}