package clashroyale.core;

/**
 * Represents an exception indicating an attempt to fire one's own tower in Clash Royale.
 * This exception is thrown when a player tries to fire their own tower, which is not allowed.
 *
 * <p>Author: Gohar Martirosyan</p>
 * <p>Version: 1.0</p>
 */
public class NotFireOwnTower extends Exception{
    /**
     * Constructs a new clashRoyale.core.NotFireOwnTower exception with a default message.
     */
    public NotFireOwnTower(){
        super("You are not allowed to fire your own tower.");
    }
    /**
     * Constructs a new clashRoyale.core.NotFireOwnTower exception with a custom message.
     *
     * @param e The custom error message.
     */
    public NotFireOwnTower(String e){
        super(e);
    }
}
