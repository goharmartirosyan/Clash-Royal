package clashroyale.core;

import java.util.ArrayList;

/**
 * Represents a throwable interface for clashRoyale.core.Fireball and clashRoyale.core.Bow cards in Clash Royale.
 * <p>Author: Gohar Martirosyan</p>
 * <p>Version: 1.0</p>
 */
public interface Launchable {
     /**
      * When a throwable card is thrown its level turns 0.
      */
     void performAction();
     /**
      * Retrieves all possible destinations to throw for the throwable card on the game board.
      *
      * @param chess The clashRoyale.core.ClashRoyale game instance.
      * @param p     The position of the throwable card.
      * @return An array of positions representing all possible destinations for the throwable card.
      */
     public ArrayList<Position> attackCardsThrowable(ClashRoyale game, Position p);
}
