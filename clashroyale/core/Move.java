package clashroyale.core;

/**
 * Represents a utility class for moving cards in a Clash Royale game.
 * This class contains methods to move cards based on specific conditions.
 *
 * <p>This class is designed to gather all the coinciding code of the child classes.
 * It provides static methods for moving cards within the game board.
 * </p>
 *
 * @author Gohar Martirosyan
 * @version: 1.0
 */
public class Move {
    private ClashRoyale game;
    /**
     * Moves all cards on the game board based on certain conditions.
     *
     * <p>This method iterates over all positions on the game board and
     * moves cards based on specific rules.</p>
     *
     * @param game The clashRoyale.core.ClashRoyale game instance.
     */
    public static void moveAllCards(ClashRoyale game){
        for (int i = 0; i < ClashRoyale.LENGTH_BOARD; i++) {
            for (int j = 0; j < ClashRoyale.LENGTH_BOARD; j++) {
                if(i == ClashRoyale.BRIDGE_ROW && (j < ClashRoyale.BRIDGE_COLUMN || j > ClashRoyale.BRIDGE_COLUMN)){
                    continue;
                }
                Position p = Position.generateFromRowAndColumn(i, j);
                Card currentCard =game.getCardAt(p);
                Position movePosition = null;
                Position bridge = new Position(ClashRoyale.BRIDGE_ROW, ClashRoyale.BRIDGE_COLUMN);
                if (currentCard == null){
                    continue;
                }
                movePosition = checkNearCards(game, p, currentCard);
                if(movePosition != null && checkWhichPositionIsNearer(p, movePosition, bridge)){
                    Card comparedCard = game.getCardAt(movePosition);
                    if(Math.pow(p.getColumn() - movePosition.getColumn(), 2) + Math.pow(p.getRow() - movePosition.getRow(), 2) < 2) {
                        if (currentCard.getLevel() > 0) {
                            currentCard.attackCard();
                            comparedCard.attackCard();
                        } else {
                            currentCard = null;
                        }
                    } else {
                        if(p.getRow() - movePosition.getRow() > 0){
                            p.setRow(p.getRow() - 1);
                        } else if(p.getRow() - movePosition.getRow() < 0){
                            p.setRow(p.getRow() + 1);
                        }
                        if(p.getColumn() - movePosition.getColumn() > 0){
                            p.setColumn(p.getColumn() - 1);
                        } else if(p.getColumn() - movePosition.getColumn() < 0){
                            p.setColumn(p.getColumn() + 1);
                        }
                    }
                }
            }

        }
    }
    /**
     * Checks for nearby cards around a given position and returns the nearest position.
     *
     * <p>This method checks for nearby cards around a given position and returns
     * the nearest position to move the card to.</p>
     *
     * @param game The clashRoyale.core.ClashRoyale game instance.
     * @param p The position to check for nearby cards.
     * @param currentCard The current card at the position.
     * @return The nearest position to move the card to, or null if no suitable position is found.
     */
    private static Position checkNearCards(ClashRoyale game, Position p, Card currentCard){
        Position nearPosition = new Position(0,0);
        boolean down = p.getRow() > ClashRoyale.BRIDGE_ROW && p.getColumn() > ClashRoyale.BRIDGE_COLUMN;
        if(down){
            for (int i = 0; i < ClashRoyale.BRIDGE_ROW; i++) {
                for (int j = 1; j < ClashRoyale.BRIDGE_COLUMN; j++) {
                    if(p.getRow() == i && p.getColumn() == j ){
                        continue;
                    }
                    Position checkPosition = Position.generateFromRowAndColumn(i, j);
                    Card card = game.getCardAt(p);
                    if(card != null &&  card.getColor() != currentCard.getColor()){
                        if(checkWhichPositionIsNearer(p, checkPosition, nearPosition)) {
                            nearPosition = new Position(checkPosition);
                        }
                    }
                }
            }
        }
        return nearPosition;
    }
    /**
     * Checks which of two positions is nearer to a reference position.
     *
     * <p>This method compares the distances between two positions and a reference position
     * and determines which one is nearer.</p>
     *
     * @param current The reference position.
     * @param other The first position to compare.
     * @param near The second position to compare.
     * @return true if the first position is nearer, false otherwise.
     */
    private static boolean checkWhichPositionIsNearer(Position current, Position other, Position near){
        double distanceOther = Math.pow(other.getColumn() - current.getColumn(), 2) + Math.pow(other.getRow() - current.getRow(), 2);
        double distanceNear = Math.pow(near.getColumn() - current.getColumn(), 2) + Math.pow(near.getRow() - current.getRow(), 2);
        return distanceOther > distanceNear;
    }
}
