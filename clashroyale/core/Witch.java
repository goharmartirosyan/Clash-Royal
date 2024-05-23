package clashroyale.core;

public class Witch extends Card implements Walkable{
    private boolean hasChildren = false;
    public Witch(ClashRoyale.CardColor color){
        super(5, 6, color);
    }

    public String toString(){
        if(this.getColor() == ClashRoyale.CardColor.RED){
            return  ANSI_RED + "W" + ANSI_RESET;
        }
        return  ANSI_BLUE + "W" + ANSI_RESET;
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
                {p.getRow() + 1, p.getColumn() + 1},

                {p.getRow() - 1, p.getColumn() - 2},
                {p.getRow() - 2, p.getColumn() - 2},
                {p.getRow(),     p.getColumn() - 2},
                {p.getRow() + 1, p.getColumn() - 2},
                {p.getRow() + 2, p.getColumn() - 2},

                {p.getRow() - 2, p.getColumn()},
                {p.getRow() - 2, p.getColumn() + 1},
                {p.getRow() - 2, p.getColumn() - 1},

                {p.getRow() + 2, p.getColumn()},
                {p.getRow() + 2, p.getColumn() + 1},
                {p.getRow() + 2, p.getColumn() - 1},

                {p.getRow() - 1, p.getColumn() + 2},
                {p.getRow() - 2, p.getColumn() + 2},
                {p.getRow(),     p.getColumn() + 2},
                {p.getRow() + 1, p.getColumn() + 2},
                {p.getRow() + 2, p.getColumn() + 2},
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
                    int count = 1;
                    if(!hasChildren) {
                        for (int j = 0; j < locations.length; j++) {
                            Position check = new Position(locations[j][0], locations[j][1]);
                            if (count <= 2
                                    && locations[j][0] >= 0
                                    && locations[j][0] < ClashRoyale.HEIGHT_BOARD
                                    && locations[j][1] >= 0
                                    && locations[j][1] < ClashRoyale.LENGTH_BOARD
                                    && game.getCardAt(check) == null) {
                                count++;
                                game.performMove(check, new WitchBaby(this.getColor()));
                            }
                        }
                        hasChildren = !hasChildren;
                    }
                    return positionForCheck;
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
            return "Witch - red";
        }
        return "Witch - blue";
    }
}
