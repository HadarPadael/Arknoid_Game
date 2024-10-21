package Removers;
import GameFlow.GameLevel;
import GameInterfaces.HitListener;
import GameObjects.Ball;
import GameObjects.Block;
import Trackers.Base.Counter;

/**
 * @author Hadar Padael.
 * 212916753
 * a class for removing balls from the game.
 */
// a BallRemover is in charge of removing balls from the game, as well as keeping count
// of the number of balls that remain.
public class BallRemover implements HitListener {
//class properties/
        private final GameLevel game;
        private final Counter remainingBalls;
//constructor/
    /**
     * constructor method.
     * @param game Game.
     * @param remainingBalls Counter.
     */
        public BallRemover(GameLevel game, Counter remainingBalls) {
            this.game = game;
            this.remainingBalls = remainingBalls;
        }
//other methods/
    /**
     * a method for acting upon a given hit information.
     * @param beingHit Block.
     * @param hitter Ball.
     */
        // Balls that are hit should be removed
        // from the game.
        public void hitEvent(Block beingHit, Ball hitter) {
            hitter.removeFromGame(this.game);
            this.remainingBalls.decrease(1);
        }
}
