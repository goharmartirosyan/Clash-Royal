package clashroyale.core;


public abstract class Card implements Cloneable{
    // Instance variables start from here.

    private final int elixir;

    private double level;
    private ClashRoyale.CardColor color;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLACK = "\u001B[30m";

    public abstract String toString();
    public abstract String name();


    public Card(int level, int elixir, ClashRoyale.CardColor color) {
        this.elixir = elixir;
        this.level = level;
        this.color = color;
    }
    public Card(Card other){
        this.level = other.level;
        this.color = other.color;
        this.elixir = other.elixir;
    }


    // From here starts the getter setter part.

    public int getElixir() {
        return elixir;
    }
    public double getLevel() {
        return level;
    }


    public void setLevel(double level) {
        this.level = level;
    }


    public ClashRoyale.CardColor getColor() {
        return color;
    }
    public boolean isValidPosition(int row, int column) {
        return row >= 0 && row < ClashRoyale.HEIGHT_BOARD && column >= 0 && column < ClashRoyale.LENGTH_BOARD;
    }


    public Card clone() {
        Card copy = null;
        try{
            copy = (Card)super.clone();
        } catch (CloneNotSupportedException e) {

        }
        return copy;
    }


    public void attackCard() {
        this.level = this.level - 0.5;
    }
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return card.elixir == this.elixir && card.level == this.level && card.color == this.color;
    }
    public Position nearCriticalPositions(Position p){
        if(p.getColumn() < ClashRoyale.BRIDGE_COLUMN){
            return Position.generateFromRowAndColumn(p.getRow(), p.getColumn() + 1);
        } else if(p.getColumn() > ClashRoyale.BRIDGE_COLUMN){
            return Position.generateFromRowAndColumn(p.getRow() , p.getColumn() - 1);
        } else{
            int direction = (color == ClashRoyale.CardColor.RED) ? 1 : -1;
            return Position.generateFromRowAndColumn(p.getRow() + direction, p.getColumn());
        }
    }

}
