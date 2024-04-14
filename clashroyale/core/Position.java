package clashroyale.core;

/**
 * Represents a position on the game board using row and column indices.
 * @author Ani Nazaryan
 * @version 1.0
 */
public class Position {
    private int row; // Represents the horizontal position
    private int column; // Represents the vertical position

    /**
     * Default constructor. Initializes position to (0, 0).
     */
    public Position() {
        this.row = 0;
        this.column = 0;
    }

    /**
     * Copy constructor. Creates a new position with the same row and column as the given position.
     * @param other The position to copy.
     */
    public Position(Position other) {
        this.row = other.row;
        this.column = other.column;
    }

    /**
     * Constructs a new clashRoyale.core.Position object with the specified row and column indices.
     *
     * @param row The horizontal position index.
     * @param column The vertical position index.
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
    /**
     * Generates a clashRoyale.core.Position object from given row and column indices.
     * @param row The row index.
     * @param column The column index.
     * @return A new clashRoyale.core.Position object with the specified row and column indices.
     */
    public static Position generateFromRowAndColumn(int row, int column) {
        return new Position(row, column);
    }

    /**
     * Generates a clashRoyale.core.Position object from a string representation.
     * The string should be in the format "ColumnRow".
     * @param inputPosition The string representation of the position.
     * @return A new clashRoyale.core.Position object parsed from the input string.
     */
    public static Position generateFromString(String inputPosition) {
        inputPosition = inputPosition.toUpperCase(); // Convert to uppercase for consistency
        int column = ClashRoyale.LENGTH_BOARD - (inputPosition.charAt(0) - '1') - 1;
        int row = ClashRoyale.HEIGHT_BOARD - (inputPosition.charAt(1) - '1') - 1;
        return new Position(row, column);
    }

    /**
     * Gets the row (horizontal position) of the position.
     * @return The row.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Gets the column (vertical position) of the position.
     * @return The column.
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Sets the row (horizontal position) of the position.
     * @param row The row to set.
     */
    public void setRow(int row) {
        if (row >= 0 && row < getHeight())
            this.row = row;
    }

    /**
     * Sets the column (vertical position) of the position.
     * @param column The column to set.
     */
    public void setColumn(int column) {
        if (column >= 0 && column < getLength())
            this.column = column;
    }

    /**
     * Generates a string representation of the position in the format "ColumnRow".
     * @return The string representation of the position.
     */
    public String toString() {
        return "" + (getRow() - this.column) + (getHeight() - this.row);
    }

    /**
     * Appends an array of positions to another array of positions.
     * @param arr The array to which elements will be appended.
     * @param elements The elements to be appended.
     * @return A new array containing all elements from arr followed by elements.
     */
    public static Position[] appendToArray(Position[] arr, Position... elements) {
        Position[] result = new Position[arr.length + elements.length];
        System.arraycopy(arr, 0, result, 0, arr.length);
        System.arraycopy(elements, 0, result, arr.length, elements.length);
        return result;
    }

    /**
     * Appends positions from one array to another array of positions.
     * @param result The array to which positions will be appended.
     * @param potential The positions to be appended.
     * @return A new array containing all positions from result followed by potential.
     */
    public static Position[] appendPositionsToArray(Position[] result, Position... potential) {
        return appendToArray(result, potential);
    }

    /**
     * Gets the height of the game board.
     * @return The height.
     */
    public int getHeight() {
        return ClashRoyale.HEIGHT_BOARD;
    }

    /**
     * Gets the length of the game board.
     * @return The length.
     */
    public int getLength() {
        return ClashRoyale.LENGTH_BOARD;
    }
}
