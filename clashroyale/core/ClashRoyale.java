package clashroyale.core;

/**
 *A class that coordinates and gather almost all the game and contains general methods for the game.
 * @author Edgar Kirakosyan.
 * @version 1.2
 */
public class ClashRoyale implements Cloneable{
    public enum CardColor {
        BLUE, RED
    }




    // Instance variables begin here (one enum type included).
    public static final int LENGTH_BOARD = 15;
    public static final int HEIGHT_BOARD = 7;
    public static final int BRIDGE_COLUMN = 4;
    public static final int BRIDGE_ROW = 8;
    public static final int REDTOWER_ROW = 0;
    public static final int REDTOWER_COLOMN = 3;
    public static final int BLUETOWER_ROW = 14;
    public static final int BLUETOWER_COLUMN = 3;
    private Card[][] board = new Card[HEIGHT_BOARD][LENGTH_BOARD];
    private CardColor turn;






    public ClashRoyale(){}

    /**
     * A simple constructor for assigning the given value to enum variable color and a deep copied array to board.
     * @param color
     */
    public ClashRoyale(CardColor color, Card[][] array) {
        this.turn = color;
        for (int i = 0; i < HEIGHT_BOARD; i++) {
            for (int j = 0; j < LENGTH_BOARD; j++) {
                this.board[i][j] = array[i][j].clone();
            }
        }

    }

    /**
     * A copy constructor that deep copies board.
     * @param original and object of clashRoyale.core.ClashRoyale class type.
     */
    public ClashRoyale(ClashRoyale original) {
        this.turn = original.turn;

        for (int i = 0; i < HEIGHT_BOARD; i++) {
            for (int j = 0; j < LENGTH_BOARD; j++) {
                this.board[i][j] = original.getBoard()[i][j].clone();
            }
        }
    }

    /**
     * A deep copy of the object of the class type Clash Royale using the clone method of each board card.
     * @return deep copy of the instance of clashRoyale.core.ClashRoyale.
     */
    public ClashRoyale clone() {
        ClashRoyale copy = null;
        try{
            copy = (ClashRoyale) super.clone();
            for (int i = 0; i < HEIGHT_BOARD; i++) {
                for (int j = 0; j < LENGTH_BOARD; j++) {
                    copy.board[i][j] = this.board[i][j].clone();
                }
            }
        } catch(CloneNotSupportedException e) {

        }
        return copy;
    }

    /**
     * A getter method for board using good copying mechanism.
     * @return a deep copy of the board.
     */
    public Card[][] getBoard() {
        Card[][] copy = new Card[HEIGHT_BOARD][LENGTH_BOARD];

        for (int i = 0; i < HEIGHT_BOARD; i++) {
            for (int j = 0; j < LENGTH_BOARD; j++) {
                copy[i][j] = this.board[i][j].clone();
            }
        }
        return copy;
    }

    /**
     * A method that gives the clone of the card at the specific position.
     * @param p
     * @return a deep copy of the card at position p.
     */
    public Card getCardAt(Position p) {
        return board[p.getHeight()][p.getLength()].clone();
    }

    /**
     * Attacks an enemy in a certain position.
     * @param p of type clashRoyale.core.Position.
     */
    public void attackPosition(Position p) {
        this.getCardAt(p).attackCard(); // has to be checked for its workability.
    }


    /**
     * A method that checks if any of the kings is dead.
     * @return true if the game is over, false if it is not.
     */
    public boolean isGameOver() {
//        if(blueKing.getLevel == 0 || redKing.getLevel == 0) {
//            return true;
//        }
        return false;
    }

    /**
     * Changes the turn.
     */
    public void changeTurn() {
        this.turn = this.turn == CardColor.BLUE ? CardColor.RED : CardColor.BLUE;
    }

    /**
     * A method that provides the turn of the players using String
     * @return BLUE or RED using String.
     */
    public String getTurnString() {
        return this.turn.toString();
    }

    /**
     * A method that returns the turn of the player in enumerated type.
     * @return BLUE or RED in enum type CardColor.
     */
    public CardColor getTurn() {
        return this.turn;
    }
}

