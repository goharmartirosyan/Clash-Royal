package clashroyale.core;

/**
 * Represents an exception indicating the selection of a wrong letter in Clash Royale.
 * This exception is thrown when a player chooses a letter that is not within the given range.
 *
 * @author Gohar Martirosyan
 * @version 1.0
 */
public class WrongLetter extends Exception {
    /**
     * Constructs a new clashRoyale.core.WrongLetter exception with a default message.
     */
    public WrongLetter(){
        super("You should choose from the given range");
    }
    /**
     * Constructs a new clashRoyale.core.WrongLetter exception with a custom message.
     *
     * @param e The custom error message.
     */
    public WrongLetter(String e){
        super(e);
    }
}
