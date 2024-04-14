package clashroyale.cli;

import clashroyale.core.ClashRoyale;
import clashroyale.core.Position;

import java.util.Scanner;
/**
 * Represents a console interface for playing Clash Royale game.
 * This class allows players to interact with the game through the console.
 *
 * <p>Author: Gohar Martirosyan</p>
 * <p>Version: 1.0</p>
 */

public class ClashRoyaleConsole {
    private ClashRoyale game;

    /**
     * Initiates the game console and starts the game loop.
     */
    public void play() {
        Scanner sc = new Scanner(System.in);
        String inputCard;
        String inputPosition;
        Position p1 = null;
        print(p1);

        while (!game.isGameOver()) {
            if (game.getTurn() == ClashRoyale.CardColor.BLUE)
                System.out.println("Blue's move: ");
            else
                System.out.println("Red's move: ");

            inputCard = sc.next();
            //here goes clashRoyale.core.WrongLetter exception
            System.out.println("Enter a position i.e. (0, 0)");
            inputPosition = sc.nextLine();
            p1 = Position.generateFromString(inputPosition);

            if(!(game.performMove(p1, inputCard) || p1 == null)){
                System.out.println("Invalid position. Please try again.");
            }
            print(p1);
        }
    }
    /**
     * Prints the current state of the game.
     *
     * @param origin The origin position of the last move.
     */
    public void print(Position origin) {}

}

