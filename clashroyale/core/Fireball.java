package clashroyale.core;

/**
 * Represents a clashRoyale.core.Fireball class for moving cards in a Clash Royale game.
 * @author Gohar Martirosyan
 * @version 1.0
 */public class Fireball extends Card implements Throwable {
    /**
     * Constructs a new clashRoyale.core.Bow card with the specified color.
     *
     * @param color The color of the clashRoyale.core.Bow card.
     */
    Fireball(ClashRoyale.CardColor color){
        super(2, 3, color);
    }
    /**
     * When a fireball is thrown its level turns 0.
     */
    public void performAction() {
        setLevel(0);
    }
    /**
     * Retrieves all possible destinations to throw for the clashRoyale.core.Fireball card on the game board.
     *
     * @param chess The clashRoyale.core.ClashRoyale game instance.
     * @param p     The position of the clashRoyale.core.Fireball card.
     * @return An array of positions representing all possible destinations for the clashRoyale.core.Fireball card.
     */
    public Position[] allDestinations(ClashRoyale chess, Position p){
        Position[] result = new Position[0];
        for (int i = 0; i < ClashRoyale.HEIGHT_BOARD; i++) {
            for (int j = 0; j < ClashRoyale.LENGTH_BOARD; j++) {
                Position potential = new Position(i, j);
                if((this.getColor() == ClashRoyale.CardColor.BLUE && i == ClashRoyale.BLUETOWER_ROW && j == ClashRoyale.BLUETOWER_COLUMN)
                        || (this.getColor() == ClashRoyale.CardColor.RED && i == ClashRoyale.REDTOWER_ROW && j == ClashRoyale.REDTOWER_COLOMN)){
                    continue;
                }
                result = Position.appendPositionsToArray(result, potential);
            }
        }
        return result;
    }
}
