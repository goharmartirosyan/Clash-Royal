package clashroyale.core;

/**
 * Abstract method that gathers all the coinciding code of the children classes.
 * @author Edgar Kirakosyan.
 * @version 1.1
 */
public abstract class Card implements Cloneable{
    // Instance variables start from here.

    /**
     * Elixir is a way of restricting the player's ability to call cards.
     */
    private final int elixir;

    /**
     * A level of a card shows its strength, the higher the level the longer the card can fight.
     * However, this is not about the killing power of a card.
     */
    private double level;


    /**
     * Indicates whether a card is from the BLUE king or the RED king.
     */
    private ClashRoyale.CardColor color;



    /**
     * A constructor with argument to initialize the instance variables.
     * @param elixir of type int -> a final variable
     * @param level of type double
     * @param color of type clashRoyale.core.ClashRoyale.CardColor
     */
    public Card(int level, int elixir, ClashRoyale.CardColor color) {
        this.elixir = elixir;
        this.level = level;
        this.color = color;
    }

    // From here starts the getter setter part.

    /**
     * A primitive getter method for the elixir variable.
     * @return elixir.
     */
    public int getElixir() {
        return elixir;
    }

    /**
     * A primitive getter method for the level variable.
     * @return level.
     */
    public double getLevel() {
        return level;
    }

    /**
     * A simple setter that reduces the level by 1.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * A primitive getter method for the color variable of type clashRoyale.core.ClashRoyale.Turn.
     * @return color.
     */
    public ClashRoyale.CardColor getColor() {
        return color;
    }

    /**
     * Overridden method clone for cloning the clashRoyale.core.Card class objects.
     * @return a deep copy of the
     */
    public Card clone() {
        Card copy = null;
        try{
            copy = (Card)super.clone();
        } catch (CloneNotSupportedException e) {

        }
        return copy;
    }

    /**
     * A method that subtracts 0.5 level from each usage of it.
     */
    public void attackCard() {
        this.level = this.level - 0.5;
    }
}
