package clashroyale.core;

/**
 * Not a full card because this can be used only in case of having a Witch and only called from the Witch.
 * But after being called behaves exactly as the other cards.
 */
public class WitchBaby extends Card implements Walkable{
    public WitchBaby(ClashRoyale.CardColor color){
        super(2, 0, color);
    }

    public String toString(){
        if(this.getColor() == ClashRoyale.CardColor.RED){
            return  ANSI_RED + "WB" + ANSI_RESET;
        }
        return  ANSI_BLUE + "WB" + ANSI_RESET;
    }

    public Position attackCardsWalkable(ClashRoyale game, Position p) {
        int[][] locations = {
                {p.getRow() - 1, p.getColumn() - 1},
                {p.getRow(),     p.getColumn() - 1},
                {p.getRow() + 1, p.getColumn() - 1},

                {p.getRow() - 1, p.getColumn()},
                {p.getRow() + 1, p.getColumn()},

                {p.getRow() - 1, p.getColumn() + 1},
                {p.getRow(),     p.getColumn() + 1},
                {p.getRow() + 1, p.getColumn() + 1}
        };

        for (int i = 0; i < locations.length; i++) {
            int row = locations[i][0];
            int column = locations[i][1];
            boolean checkForEligibility = row < ClashRoyale.HEIGHT_BOARD
                    && row >= 0
                    && column < ClashRoyale.LENGTH_BOARD
                    && column >= 0;

            if(checkForEligibility) {
                Position positionForCheck = Position.generateFromRowAndColumn(row, column);
                Card nextCard = game.getCardAt(positionForCheck);

                if(nextCard != null && nextCard.getColor() != this.getColor()) {
                    if (row >= 0 && row < ClashRoyale.HEIGHT_BOARD &&
                            column >= 0 && column < ClashRoyale.LENGTH_BOARD) {
                        return positionForCheck;
                    }
                }
            }
        }

        //no opponent was spotted
        if((p.getRow() + (this.getColor() == ClashRoyale.CardColor.RED ? 1 : -1) != 7) || p.getColumn() == 3){
            int row = p.getRow() + (this.getColor() == ClashRoyale.CardColor.RED ? 1 : -1);
            int column = p.getColumn();
            return Position.generateFromRowAndColumn(row, column);
        } else {
            int column = p.getColumn() < 3 ? p.getColumn() + 1 : p.getColumn() - 1;
            int row = p.getRow();
                return Position.generateFromRowAndColumn(row, column);
        }
    }
    public String name() {
        if (this.getColor() == ClashRoyale.CardColor.RED) {
            return "Witchbaby - red";
        }
        return "Witchbaby - blue";
    }
}
