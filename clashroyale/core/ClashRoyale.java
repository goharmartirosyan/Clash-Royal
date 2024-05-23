package clashroyale.core;

import java.util.ArrayList;


public class ClashRoyale implements Cloneable{
    // Instance variables begin here (one enum type included).
    public static final int LENGTH_BOARD = 7;
    public static final int HEIGHT_BOARD = 15;
    public static final int BRIDGE_COLUMN = 3;
    public static final int BRIDGE_ROW = 7;
    public static final int REDTOWER_ROW = 0;
    public static final int REDTOWER_COLOMN = 3;
    public static final int BLUETOWER_ROW = 14;
    public static final int BLUETOWER_COLUMN = 3;
    private Card[][] board = new Card[HEIGHT_BOARD][LENGTH_BOARD];
    private CardColor turn;

    public void moveAllCards() {
        ArrayList<Position> allMoved = new ArrayList<Position>();
        ArrayList<Card> allMovedCards = new ArrayList<Card>();
        for (int i = 0; i < HEIGHT_BOARD; i++) {
            for (int j = 0; j < LENGTH_BOARD; j++) {
                Position p = Position.generateFromRowAndColumn(i, j);
                Card currentCard = board[p.getRow()][p.getColumn()];
                if (currentCard == null) {
                    continue;
                } else if (currentCard instanceof Launchable) {
                    ArrayList<Position> attackedPosition = ((Launchable) currentCard).attackCardsThrowable(this, p);
                    for (Position position : attackedPosition) {
                        Card attackedCard = board[position.getRow()][position.getColumn()];
                        attackedCard.setLevel(attackedCard.getLevel() - currentCard.getLevel());
                        ((Launchable) currentCard).performAction();
                        board[p.getRow()][p.getColumn()] = null;
                    }
                    board[p.getRow()][p.getColumn()] = null;
                } else if( (i == REDTOWER_ROW && j == REDTOWER_COLOMN)
                        || (i == BLUETOWER_ROW && j == BLUETOWER_COLUMN)){
                    continue;
                } else {
                    Position nearest = ((Walkable) currentCard).attackCardsWalkable(this, p);
                    if(nearest.getRow() >= 0 && nearest.getRow() < ClashRoyale.HEIGHT_BOARD &&
                            nearest.getColumn()>= 0 && nearest.getColumn() < ClashRoyale.LENGTH_BOARD) {
                        Card opponent = board[nearest.getRow()][nearest.getColumn()];

                    if(opponent == null && !allMovedCards.contains(currentCard)){
                        board[nearest.getRow()][nearest.getColumn()] = currentCard;
                        board[p.getRow()][p.getColumn()] = null;
                        getBoard()[nearest.getRow()][nearest.getColumn()] = currentCard;
                        allMovedCards.add(currentCard);
                    } else if(allMovedCards.contains(currentCard)){
                        continue;
                    }
                    else {
                        boolean movedCard = false;
                        for (Position position : allMoved) {
                            if (nearest.equals(position)) {
                                movedCard = true;
                                break;
                            }
                        }
                        if (!movedCard) {
                            currentCard.attackCard();
                            opponent.attackCard();
                        }
                    }}
                }
                if (currentCard != null && currentCard.getLevel() <= 0) {
                    board[p.getRow()][p.getColumn()] = null;
                }
            }
        }

    }


    public boolean performMove(Position origin, Card card) {
        Card previousCard = board[origin.getRow()][origin.getColumn()];
        if(origin.getRow() == ClashRoyale.BRIDGE_ROW && origin.getColumn() != ClashRoyale.BRIDGE_COLUMN){
            return false;
        }
        if(previousCard == null){
            setCard(origin, card);
            setGameElixir(card);
             changeTurn();
            return true;
        }
        System.out.println("There is already a card");
        return false;
    }
    public enum CardColor {
        BLUE, RED
    }
    private int gameElixirBlue = 90;
    private int gameElixirRed = 90;
    public int getGameElixir(CardColor color) {
        if(color.equals(CardColor.RED)){
            return this.gameElixirRed;
        } else{
            return this.gameElixirBlue;
       }
    }
    public void setGameElixir(Card card){
        if(card.getColor() == CardColor.RED){
            this.gameElixirRed = this.gameElixirRed - card.getElixir();
        } else {
           this.gameElixirBlue = this.gameElixirBlue - card.getElixir();
       }
    }
    public  boolean gameIsOver(){
        if(this.gameElixirBlue <= 0 || this.gameElixirRed <= 0){
            if(this.gameElixirRed > 0){
                System.out.println("Red team won!!");
            } else if(this.gameElixirBlue > 0){
                System.out.println("Red team won!!");
            } else{
                System.out.println("Friendship won!!");
            }
            return true;
        }
        return false;
    }

    public ClashRoyale(){
        Tower redTower = new Tower(CardColor.RED);
        board[REDTOWER_ROW][REDTOWER_COLOMN] = redTower;
        Tower blueTower = new Tower(CardColor.BLUE);
        board[BLUETOWER_ROW][BLUETOWER_COLUMN] = blueTower;
        this.turn = CardColor.RED;
    }

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

    public ClashRoyale(ClashRoyale original) {
        this.turn = original.turn;

        for (int i = 0; i < HEIGHT_BOARD; i++) {
            for (int j = 0; j < LENGTH_BOARD; j++) {
                this.board[i][j] = original.getBoard()[i][j].clone();
                this.board[REDTOWER_ROW][REDTOWER_COLOMN] = new Tower(CardColor.RED);
                this.board[BLUETOWER_ROW][BLUETOWER_COLUMN] = new Tower(CardColor.BLUE);
            }
        }
    }


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
    public void setCard(Position newp, Card card){
        this.board[newp.getRow()][newp.getColumn()] = card;

    }

    public Card[][] getBoard() {
        Card[][] copy = new Card[HEIGHT_BOARD][LENGTH_BOARD];

        for (int i = 0; i < HEIGHT_BOARD; i++) {
            for (int j = 0; j < LENGTH_BOARD; j++) {
                if(this.board[i][j] == null){
                    continue;
                }
                copy[i][j] = this.board[i][j].clone();
            }
        }
        return copy;
    }


    public Card getCardAt(Position p) {
        if(board[p.getRow()][p.getColumn()] == null){
            return null;
        }
        return board[p.getRow()][p.getColumn()].clone();
    }


    public void changeTurn() {
        // Check if the current turn is blue
        if (this.turn == CardColor.BLUE) {
            // If it is, change the turn to red
            this.turn = CardColor.RED;
        } else {
            // If it's not blue, assume it's red and change it to blue
            this.turn = CardColor.BLUE;
        }
    }


    public CardColor getTurn() {
        return this.turn;
    }
    public boolean isEmpty(Position p) {
        return this.board[p.getRow()][p.getColumn()] == null;
    }
    Card[] Card= {  new Knight(getTurn()), new Archer(getTurn()), new Musketeer(getTurn()),  new Fireball(getTurn()), new Bow(getTurn()), new Valkyrie(getTurn()), new Witch(getTurn()), new Dragon(getTurn()), new Goth(getTurn())};


    public static Card[] randomCards(CardColor color){
       Card[] allCards= {  new Knight(color), new Archer(color), new Musketeer(color),  new Fireball(color), new Bow(color), new Valkyrie(color), new Witch(color), new Dragon(color), new Goth(color)};
       Card[] cards = new Card[5];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = allCards[(int) (Math.random() * allCards.length)];
        }
        return cards;
    }
    public String getElixirLevelText() {
        StringBuilder elixirText = new StringBuilder();
        elixirText.append("Turn ");
        elixirText.append(getTurn());
        elixirText.append("\n");
        Card[] chooseCards = Card;
        for (int i = 0; i < chooseCards.length; i++) {
            elixirText.append(chooseCards[i].name()).append(" elixir is ").append(chooseCards[i].getElixir()).append("\n");
        }
        elixirText.append("\n");
        for (int i = 0; i < ClashRoyale.HEIGHT_BOARD; i++) {
            for (int j = 0; j < ClashRoyale.LENGTH_BOARD; j++) {
                Card currentCard = this.getCardAt(Position.generateFromRowAndColumn(i, j));
                if (currentCard != null) {
                    elixirText.append(currentCard.name()).append(" level is ").append(currentCard.getLevel()).append("\n");
                }
            }
        }
        elixirText.append("\n");
        elixirText.append("Elixir of red's ").append(this.getGameElixir(ClashRoyale.CardColor.RED)).append("\n");
        elixirText.append("Elixir of blue's ").append(this.getGameElixir(ClashRoyale.CardColor.BLUE)).append("\n");
        return elixirText.toString();
    }

}

