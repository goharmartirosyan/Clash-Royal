package clashroyale.core;

public class Position {
    private int row; // Represents the horizontal position
    private int column; // Represents the vertical position

    public Position() {
        this.row = 0;
        this.column = 0;
    }


    public Position(Position other) {
        this.row = other.row;
        this.column = other.column;
    }


    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Position generateFromRowAndColumn(int row, int column) {
        return new Position(row, column);
    }


    public static Position generateFromString(String inputPosition) {
        inputPosition = inputPosition.toUpperCase(); // Convert to uppercase for consistency
        int column = ClashRoyale.LENGTH_BOARD - (inputPosition.charAt(0) - '1') - 1;
        int row = ClashRoyale.HEIGHT_BOARD - (inputPosition.charAt(1) - '1') - 1;
        return new Position(row, column);
    }


    public int getRow() {
        return this.row;
    }


    public int getColumn() {
        return this.column;
    }


    public void setRow(int row) {
        if (row >= 0 && row < getHeight())
            this.row = row;
    }


    public void setColumn(int column) {
        if (column >= 0 && column < getLength())
            this.column = column;
    }


    public String toString() {
        return "" + (getRow() - this.column) + (getHeight() - this.row);
    }


    public static Position[] appendToArray(Position[] arr, Position... elements) {
        Position[] result = new Position[arr.length + elements.length];
        System.arraycopy(arr, 0, result, 0, arr.length);
        System.arraycopy(elements, 0, result, arr.length, elements.length);
        return result;
    }


    public static Position[] appendPositionsToArray(Position[] result, Position... potential) {
        return appendToArray(result, potential);
    }


    public int getHeight() {
        return ClashRoyale.HEIGHT_BOARD;
    }

    public int getLength() {
        return ClashRoyale.LENGTH_BOARD;
    }
}
