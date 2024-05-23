package clashroyale.core;

import java.util.ArrayList;


 public class Fireball extends Card implements Launchable {

    public Fireball(ClashRoyale.CardColor color){
        super(2, 3, color);
    }
    public String toString(){
        if(this.getColor() == ClashRoyale.CardColor.RED){
            return  ANSI_RED + "F" + ANSI_RESET;
        }
        return  ANSI_BLUE + "F" + ANSI_RESET;
    }

    public void performAction() {
        setLevel(0);
    }

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
    public ArrayList<Position> attackCardsThrowable(ClashRoyale game, Position p){
        ArrayList<Position> attack = new ArrayList<Position>();
        int[] row = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
        int[] column = {-1, 0, 1, -1, 0, 1, -1 , 0, 1};
        for (int i = 0; i < row.length; i++) {
            int newRow = p.getRow() + row[i];
            int newColumn = p.getColumn() + column[i];
            if (isValidPosition(newRow, newColumn)) {
                Position checkedPosition = Position.generateFromRowAndColumn(newRow, newColumn);
                Card checkedCard = game.getCardAt(checkedPosition);
                if (checkedCard != null && checkedCard.getColor() != this.getColor()) {
                    attack.add(checkedPosition);
                }
            }
        }
        return attack;
    }
    public String name() {
        if (this.getColor() == ClashRoyale.CardColor.RED) {
            return "Fireball - red";
        }
        return "Fireball - blue";
    }


}
