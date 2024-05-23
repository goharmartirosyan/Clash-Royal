package clashroyale.core;

import java.util.ArrayList;


 public class Bow extends Card implements Launchable {

    public Bow(ClashRoyale.CardColor color){
        super(3, 4, color);
    }
    public String toString(){
        if(this.getColor() == ClashRoyale.CardColor.RED){
            return  ANSI_RED + "B" + ANSI_RESET;
        }
        return  ANSI_BLUE + "B" + ANSI_RESET;
    }

    public void performAction() {
        setLevel(0);
    }

   public ArrayList<Position> attackCardsThrowable(ClashRoyale game, Position p){
       ArrayList<Position> attack= new ArrayList<Position>();
       int[] row = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
       int[] column = {-1, 0, 1, -1, 0, 1, -1 , 0, 1};
       for (int i = 0; i < row.length; i++) {
           int newRow = p.getRow() + row[i];
           int newColumn = p.getColumn() + column[i];

           // Check if the new position is within the bounds of the game board
           if (isValidPosition(newRow, newColumn)) {
               Position checkedPosition = Position.generateFromRowAndColumn(newRow, newColumn);
               Card checkedCard  = game.getCardAt(checkedPosition);
               if(checkedCard != null && checkedCard.getColor() != this.getColor()){
                   attack.add(checkedPosition);
               }
           }
       }
       return attack;
   }
    public String name() {
        if (this.getColor() == ClashRoyale.CardColor.RED) {
            return "Bow - red";
        }
        return "Bow - blue";
    }

}

