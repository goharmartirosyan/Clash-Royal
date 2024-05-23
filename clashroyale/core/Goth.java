package clashroyale.core;

public class Goth extends Card implements Walkable{
    public Goth(ClashRoyale.CardColor color){
        super(3, 5, color);
    }

    public String toString(){
        if(this.getColor() == ClashRoyale.CardColor.RED){
            return  ANSI_RED + "G" + ANSI_RESET;
        }
        return  ANSI_BLUE + "G" + ANSI_RESET;
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
                    return positionForCheck;
                }
            }
        }
        //no opponent was spotted
        if(p.getRow() + 1 == ClashRoyale.BRIDGE_ROW || p.getRow() - 1 == ClashRoyale.BRIDGE_ROW ||
                p.getRow() == ClashRoyale.BLUETOWER_ROW || p.getRow() == ClashRoyale.REDTOWER_ROW) {
            return nearCriticalPositions(p);
        }


        int row = p.getRow() + (this.getColor() == ClashRoyale.CardColor.RED ? 1 : -1);
        int column = p.getColumn();
        return Position.generateFromRowAndColumn(row, column);
    }
    public String name() {
        if (this.getColor() == ClashRoyale.CardColor.RED) {
            return "Goth - red";
        }
        return "Goth - blue";
    }
}

