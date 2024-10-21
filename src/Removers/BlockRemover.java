package Removers;

import GameFlow.GameLevel;
import GameInterfaces.HitListener;
import GameObjects.Ball;
import GameObjects.Block;
import Trackers.Base.Counter;

/**
 * @author Hadar Padael.
 * 212916753
 * a class for removing blocks from the game.
 */
// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
    //class property/
    private final GameLevel game;
    private final Counter remainingBlocks;
//constructor/
    /**
     * constructor method.
     * @param game Game
     * @param remainingBlocks Counter.
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.

    /**
     * a method which acts upon a given hit information.
     * @param beingHit Block.
     * @param hitter Ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }
}