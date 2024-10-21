package Trackers;

import GameInterfaces.HitListener;
import GameObjects.Ball;
import GameObjects.Block;
import Trackers.Base.Counter;

/**
 * @author Hadar Padael.
 * 212916753
 * a class for tracking score during the game.
 */
public class ScoreTrackingListener implements HitListener {
    //class property/
    private final Counter currentScore;
//constructor/

    /**
     * constructor method.
     * @param scoreCounter Counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * a method which acts upon a given hit information.
     * @param beingHit Block
     * @param hitter Ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if(beingHit.getRowOfBlock().size() == 1) {
            this.currentScore.increase(100);
        } else {
            this.currentScore.increase(5);
        }
        beingHit.getRowOfBlock().remove(beingHit);
        beingHit.removeHitListener(this);
    }
}