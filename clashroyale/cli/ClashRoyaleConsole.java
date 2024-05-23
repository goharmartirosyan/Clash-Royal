package clashroyale.cli;

import clashroyale.core.*;
import clashroyale.core.ClashRoyale;

import java.sql.SQLOutput;
import java.util.Scanner;

public class ClashRoyaleConsole {
    private ClashRoyale game;

    public ClashRoyale getGame() {
        return game.clone();
    }


    public void play() {
        game = new ClashRoyale();
        Scanner sc = new Scanner(System.in);
        int inputCard = -1;
        // String inputPosition;
        Position p1 = null;

        game.moveAllCards();
        print();
        printElixirLevel();
        Card[] cards = ClashRoyale.randomCards(game.getTurn());
        printCard(cards);
        boolean firstIteration = true;
        while (!game.gameIsOver()) {
            if(!firstIteration) {
                sc.nextLine();
            }
            firstIteration = false;
            if (game.getTurn() == ClashRoyale.CardColor.BLUE)
                System.out.println("Blue's move: ");
            else
                System.out.println("Red's move: ");

            System.out.println("Enter a position i.e. (0, 0)");

            String inputPosition = sc.nextLine();
            inputPosition = inputPosition.replaceAll("\\s+","");
            if (inputPosition.length() < 5 || inputPosition.charAt(0) != '(' || inputPosition.charAt(inputPosition.length() - 1) != ')') {
                System.out.println(inputPosition);
                System.out.println("Invalid input position format. Please enter position in the format (row, column).");
                continue; // Skip this iteration and restart the loop
            }
            String[] parts = inputPosition.substring(1, inputPosition.length() - 1).split(",");
            parts = inputPosition.substring(1, inputPosition.length() - 1).split(",");
            int row = Integer.parseInt(parts[0].trim());
            int column = Integer.parseInt(parts[1].trim());
            System.out.println(row);
            p1 = Position.generateFromRowAndColumn(Integer.parseInt(parts[0]),  Integer.parseInt(parts[1]));
            System.out.println("Enter one of the following cards.");
            inputCard = sc.nextInt();
            if(inputCard < 1 && inputCard > 6){
                System.out.println("Invalid card. Please try again.");
            } else {

                if (!game.performMove(p1, cards[inputCard - 1])) {
                    System.out.println("Invalid position. Please try again.");
                }

            }
            print();
            cards = ClashRoyale.randomCards(game.getTurn());
            game.moveAllCards();
            printCard(cards);
            printElixirLevel();
        }
    }

    public void print() {
        System.out.print(" ");
        for(int i = 0; i < ClashRoyale.LENGTH_BOARD; i++ ){
            System.out.print("  " + i);
        }
        System.out.println();
        for (int i = 0; i < ClashRoyale.HEIGHT_BOARD; i++) {
            System.out.print(i);
            if(i < 10 ) {
                System.out.print(" ");
            }
            for (int j = 0; j < ClashRoyale.LENGTH_BOARD; j++) {
                Card current = game.getCardAt(Position.generateFromRowAndColumn(i, j));
                if (i == ClashRoyale.BRIDGE_ROW) {
                    if (j == ClashRoyale.BRIDGE_COLUMN) {
                        System.out.print(Card.ANSI_GREEN + "[" + Card.ANSI_RESET);
                        if (current != null) {
                            System.out.print(current);
                        } else {
                            System.out.print(" ");
                        }
                        System.out.print(Card.ANSI_GREEN + "]" + Card.ANSI_RESET);
                    } else {
                        System.out.print(Card.ANSI_BLACK + "[" + Card.ANSI_RESET);
                        if (current != null) {
                            System.out.print(current);
                        } else {
                            System.out.print(" ");
                        }
                        System.out.print(Card.ANSI_BLACK + "]" + Card.ANSI_RESET);
                    }
                } else {
                    System.out.print("[");
                    if (current != null) {
                        System.out.print(current);
                    } else {
                        System.out.print(" ");
                    }
                    System.out.print("]");
                }
            }
            System.out.println();
        }
    }


    public void printCard(Card[] cards){
        System.out.println("-------------------------");
        for (int i = 0; i < cards.length; i++) {
            System.out.print(cards[i].toString());
            System.out.print("  ");
        }

        System.out.println();
        for (int i = 0; i < cards.length; i++) {
            System.out.print(cards[i].getElixir());
            System.out.print("  ");
        }
        System.out.println();
        System.out.println("-------------------------");
    }
    public void printElixirLevel(){
        for (int i = 0; i < ClashRoyale.HEIGHT_BOARD; i++) {
            for (int j = 0; j < ClashRoyale.LENGTH_BOARD; j++) {
                Card currentCard = game.getCardAt( Position.generateFromRowAndColumn(i, j));
                if(currentCard != null){
                    System.out.println(currentCard.toString() + " level is " + currentCard.getLevel());
                }
            }
        }
        System.out.println("Elixir of red's " +game.getGameElixir(ClashRoyale.CardColor.RED));
        System.out.println("Elixir of blue's " +game.getGameElixir(ClashRoyale.CardColor.BLUE));
    }

}
