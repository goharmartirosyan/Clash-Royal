package clashroyale.core;

/**
 * Represents the "Dragon" card in Clash Royale.
 * The Dragon card is a group of flying troops with moderate hitpoints and damage.
 * @author Ani Nazaryan
 * @version 1.0
 */
public class Dragon extends Card implements Walkable {
    /**
     * Constructs a new Dragon card with the specified color.
     *
     * @param color The color of the Dragon card.
     */
    public Dragon(ClashRoyale.CardColor color) {
        super(3, 2, color); // Adjust hitpoints and damage as needed
    }

    /**
     * Generates a string representation of the Dragon card.
     * The representation includes color codes for visual distinction.
     *
     * @return A string representation of the Dragon card.
     */
    public String toString() {
        if (this.getColor() == ClashRoyale.CardColor.RED) {
            return ANSI_RED + "D" + ANSI_RESET; // Red color for RED team
        }
        return ANSI_BLUE + "D" + ANSI_RESET; // Blue color for BLUE team
    }

    int step = 2;
    /**
     * Attacks cards within the game board. Implements the attackCardsWalkable method from the Walkable interface.
     *
     * @param game The Clash Royale game instance.
     * @param p    The position of the Dragon card.
     * @return The position to move the Dragon card to after attacking.
     */
    public Position attackCardsWalkable(ClashRoyale game, Position p) {
        int oldRow = p.getRow();
        int oldColumn = p.getColumn();
        int[] rowAttack = {-1, -1, -1, 0, 0, 1, 1, 1, -2, -2, -2, -2, -2, -1, 0, 1, 2, 2, 2, 2, 2, 1, 0, -1};
        int[] columnAttack = {-1, 0, 1, -1, 1, -1, 0, 1, -2, -1, 0, 1, 2, 2, 2, 2, 2, 1, 0, -1, -2, -2, -2, -2, -2};
        for (int i = 0; i < rowAttack.length; i++) {
            int newRow = oldRow + rowAttack[i];
            int newColumn = oldColumn + columnAttack[i];

            // Check if the new position is within the bounds of the game board
            if (newRow >= 0 && newRow < ClashRoyale.HEIGHT_BOARD &&
                    newColumn >= 0 && newColumn < ClashRoyale.LENGTH_BOARD) {
                Position newPosition = Position.generateFromRowAndColumn(newRow, newColumn);
                Card adjacentCard = game.getCardAt(newPosition);

                // Check if there is an opponent's card adjacent to the current position
                if (adjacentCard != null && adjacentCard.getColor() != this.getColor()) {
                    return newPosition; // Return the position of the opponent's card
                }
            }
        }
        if(oldRow == ClashRoyale.BRIDGE_ROW && oldColumn == ClashRoyale.BRIDGE_COLUMN){
            int row =oldRow + (this.getColor() == ClashRoyale.CardColor.RED ? step : -step);
            int column = oldColumn + (oldColumn < ClashRoyale.BRIDGE_COLUMN ? step : -step);
            return Position.generateFromRowAndColumn(row, column);
        }
        if (oldRow + (this.getColor() == ClashRoyale.CardColor.RED ? 2 : -2) == ClashRoyale.BRIDGE_ROW && oldColumn != ClashRoyale.BRIDGE_COLUMN) {
            int row =oldRow + (this.getColor() == ClashRoyale.CardColor.RED ? 1 : -1);
            int column = oldColumn + (oldColumn < ClashRoyale.BRIDGE_COLUMN ? 1 : -1);
            return Position.generateFromRowAndColumn(row, column);
        }
        if (oldRow + (this.getColor() == ClashRoyale.CardColor.RED ? 1 : -1) == ClashRoyale.BRIDGE_ROW && oldColumn != ClashRoyale.BRIDGE_COLUMN) {
            if (oldColumn + 1 == ClashRoyale.BRIDGE_COLUMN || oldColumn - 1 == ClashRoyale.BRIDGE_COLUMN) {
                int column = oldColumn + (oldColumn < ClashRoyale.BRIDGE_COLUMN ? 1 : -1);
                return Position.generateFromRowAndColumn(ClashRoyale.BLUETOWER_ROW, column);
            } else {
                int column = oldColumn + (oldColumn < ClashRoyale.BRIDGE_COLUMN ? step : -step);
                return Position.generateFromRowAndColumn(oldRow, column);
            }
        }

        if( ClashRoyale.CardColor.RED == this.getColor() && oldRow + 3 >= ClashRoyale.BLUETOWER_ROW){
            int column = oldColumn + (oldColumn < ClashRoyale.BLUETOWER_COLUMN ? step : -step);
            return Position.generateFromRowAndColumn(oldRow, column);
        }

        if( ClashRoyale.CardColor.BLUE == this.getColor() && oldRow - 2 <=  ClashRoyale.REDTOWER_ROW){
            int column = oldColumn + (oldColumn < ClashRoyale.REDTOWER_COLOMN ? step : -step);
            return Position.generateFromRowAndColumn(oldRow, column);
        }

        int forwardRow = oldRow + (this.getColor() == ClashRoyale.CardColor.RED ? step : -step);
        int forwardColumn = oldColumn;
        return Position.generateFromRowAndColumn(forwardRow,forwardColumn);
    }
    public String name() {
        if (this.getColor() == ClashRoyale.CardColor.RED) {
            return "Dragon - red";
        }
        return "Dragon - blue";
    }
}

