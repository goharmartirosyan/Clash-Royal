package clashroyale.core;

/**
 * Represents a throwable interface for clashRoyale.core.Fireball and clashRoyale.core.Bow cards in Clash Royale.
 * @author Gohar Martirosyan
 * @version 1.0
 */
public interface Throwable {
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
     Position[] allDestinations(ClashRoyale chess, Position p);
}
