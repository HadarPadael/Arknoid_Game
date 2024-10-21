package Tests;

import GameInterfaces.HitListener;
import GameObjects.Ball;
import GameObjects.Block;

/**
 * @author Hadar Padael.
 * 212916753
 * a class for testing the hitt listener pattern.
 */

public class PrintingHitListener implements HitListener {
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}