package clashroyale.core;

import clashroyale.cli.ClashRoyaleConsole;

public class Tower extends Card {
    public Tower(ClashRoyale.CardColor color){
        super(60, 0, color);
    }
    public Tower(Tower other){
        this(other.getColor());
    }
    public String toString(){
        if(this.getColor() == ClashRoyale.CardColor.RED){
            return  ANSI_RED + "T" + ANSI_RESET;
        }
        return  ANSI_BLUE + "T" + ANSI_RESET;
    }
    int[][] redTowerAttack = {
            {0, 1}, {1, 5},
            {1, 1}, {0,5},
            {2, 1}, {2, 2}, {2, 3}, {2, 4}, {2, 5}
    };
    int[][] blueTowerAttack = {
            {14, 1}, {14, 5},
            {13, 1}, {13, 5},
            {12, 1}, {12, 2}, {12, 3}, {12, 4}, {12, 5}
    };
    public void attackCard(ClashRoyale game) {
        if(this.getColor() == ClashRoyale.CardColor.RED){
            for (int i = 0; i < redTowerAttack.length; i++) {
                Card nearCard = game.getCardAt(Position.generateFromRowAndColumn(redTowerAttack[i][0], redTowerAttack[i][1]));
                if(nearCard != null && nearCard.getColor() == ClashRoyale.CardColor.BLUE){
                    nearCard.getLevel();
                    nearCard.getLevel();
                }
            }
        } else{
            for (int i = 0; i < blueTowerAttack.length; i++) {
                Card nearCard = game.getCardAt(Position.generateFromRowAndColumn(redTowerAttack[i][0], redTowerAttack[i][1]));
                if(nearCard != null && nearCard.getColor() == ClashRoyale.CardColor.RED){
                    nearCard.getLevel();
                    nearCard.getLevel();
                }
            }
        }
    }
    public String name() {
        if (this.getColor() == ClashRoyale.CardColor.RED) {
            return "Tower - red";
        }
        return "Tower - blue";
    }
}
