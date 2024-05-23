package clashroyale.core;
public class Musketeer extends Card implements Walkable{
    public Musketeer(ClashRoyale.CardColor color){
        super(5, 6, color);
    }

    public String toString(){
        if(this.getColor() == ClashRoyale.CardColor.RED){
            return  ANSI_RED + "M" + ANSI_RESET;
        }
        return  ANSI_BLUE + "M" + ANSI_RESET;
    }
    private int step = 1;
    public Position attackCardsWalkable(ClashRoyale game, Position p){
        int[] row = {-1, -1, -1, 0, 0, 1, 1, 1, -2, -2, -2, -2, -2, -1, 0, 1, 2, 2, 2, 2, 2, 1, 0, -1};
        int[] column = {-1, 0, 1, -1, 1, -1, 0, 1, -2, -1, 0, 1, 2, 2, 2, 2, 2, 1, 0, -1, -2, -2, -2, -2, -2};
        for (int i = 0; i < row.length; i++) {
            int newRow = p.getRow() + row[i];
            int newColumn = p.getColumn() + column[i];

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

        // If no opponent's card is adjacent, move forward one block
        if(p.getRow() + 1 == ClashRoyale.BRIDGE_ROW || p.getRow() - 1 == ClashRoyale.BRIDGE_ROW ||
                p.getRow() == ClashRoyale.BLUETOWER_ROW || p.getRow() == ClashRoyale.REDTOWER_ROW) {
            return nearCriticalPositions(p);
        }
        int forwardRow = p.getRow() + (this.getColor() == ClashRoyale.CardColor.RED ? step : -step);
        int forwardColumn = p.getColumn();
        return Position.generateFromRowAndColumn(forwardRow, forwardColumn);
    }
    public String name() {
        if (this.getColor() == ClashRoyale.CardColor.RED) {
            return "Musketeer - red";
        }
        return "Musketeer - blue";
    }}

