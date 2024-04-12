package connect4.models;

public class BoardHelper {
    /**
     * Represents every direction you can move from any cell on a board.
     * The directions are represented as { row, column } pairs as follows, where
     * each integer is either -1, 0, or 1.
     */
    private static final int[][] DIRECTIONS = {
            { +0, +1 }, { +0, -1 }, // →, ←
            { +1, +0 }, { -1, +0 }, // ↓, ↑
            { +1, +1 }, { -1, -1 }, // ↘, ↖
            { -1, +1 }, { +1, -1 }, // ↗, ↙
    };
    private Token[] board;
    private final int BOARD_WIDTH;
    private final int BOARD_HEIGHT;

    public BoardHelper(int boardHeight, int boardWidth, Token[] board) throws IllegalArgumentException {
        if (boardHeight < 1) {
            throw new IllegalArgumentException("Board height must be at least 1");
        }

        if (boardWidth < 1) {
            throw new IllegalArgumentException("Board width must be at least 1");
        }

        if (boardHeight * boardHeight != board.length) {
            throw new IllegalArgumentException("Board height and width do not match the length of `board`");
        }

        this.BOARD_HEIGHT = boardHeight;
        this.BOARD_WIDTH = boardWidth;
        this.board = board;
    }

    /**
     * Retrieves the tokens in a specific row of the board.
     *
     * @param row - The index of the row to retrieve (0-indexed, where 0 is the top
     *            row).
     * @return an array of tokens representing the chosen row.
     * @throws IllegalArgumentException if the row index is out of bounds.
     */
    public Token[] getRow(int row) throws IllegalArgumentException {
        if (this.rowOutOfBounds(row)) {
            throw new IllegalArgumentException("`row` index out of bounds");
        }

        Token[] chosenRow = new Token[this.BOARD_WIDTH];

        for (int column = 0; column < this.BOARD_WIDTH; column++) {
            chosenRow[column] = this.getToken(row, column);
        }

        return chosenRow;
    }

    /**
     * Retrieves the tokens in a specific column of the board.
     *
     * @param column - The index of the column to retrieve (0-indexed, where 0 is
     *               the leftmost column).
     * @return An array of tokens representing the chosen column.
     * @throws IllegalArgumentException if the column index is out of bounds.
     */
    public Token[] getColumn(int column) {
        if (this.columnOutOfBounds(column)) {
            throw new IllegalArgumentException("`column` index out of bounds");
        }

        Token[] chosenColumn = new Token[this.BOARD_HEIGHT];

        for (int row = 0; row < this.BOARD_HEIGHT; row++) {
            chosenColumn[row] = this.getToken(row, column);
        }

        return chosenColumn;
    }

    /**
     * Checks if there are `n` tokens of the same type in a row, horizontally,
     * vertically, diagonally, or reverse-diagonally,
     * starting from the "origin token" at the specified row and column.
     *
     * @param originRow    - the starting row index (0-indexed, where 0 is the top
     *                     row).
     * @param originColumn - the starting column index (0-indexed, where 0 is the
     *                     leftmost
     *                     column).
     * @param n            - the number of tokens to check for (including the
     *                     origin).
     * @return true if there are 'n' tokens of the same type in a row, false
     *         otherwise.
     * @throws IllegalArgumentException if `originRow` or `originColumn` are out of
     *                                  bounds.
     * @throws IllegalArgumentException if `n` is less than 1.
     */
    public boolean isNInARow(int originRow, int originColumn, int n) {
        if (this.rowOutOfBounds(originRow)) {
            throw new IllegalArgumentException("Index `originRow` is out of bounds");
        }

        if (this.columnOutOfBounds(originColumn)) {
            throw new IllegalArgumentException("Index `originColumn` is out of bounds");
        }

        if (n < 1) {
            throw new IllegalArgumentException("`n` must be between 1 or more");
        }

        // The origin token is always in a row with itself.
        if (n == 1) {
            return true;
        }

        Token originToken = this.getToken(originRow, originColumn);

        for (int[] direction : BoardHelper.DIRECTIONS) {
            int columnDirection = direction[1];
            int rowDirection = direction[0];

            // The origin token is always in a row with itself, so we start at 1.
            int inARow = 1;

            for (int index = 1; index < n; index++) {
                int columnToTest = originColumn + columnDirection * index;
                int rowToTest = originRow + rowDirection * index;

                if (this.rowOutOfBounds(rowToTest) || this.columnOutOfBounds(columnToTest)) {
                    break;
                }

                if (this.getToken(rowToTest, columnToTest) != originToken) {
                    break;
                }

                if (++inARow == n) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Retrieves the token at the specified position on the board.
     *
     * @param row    - the row index of the token (0-indexed, where 0 is the top
     *               row).
     * @param column - the column index of the token (0-indexed, where 0 is the
     *               leftmost column).
     * @return the token at the specified position.
     * @throws IllegalArgumentException if `row` or `column` is out of bounds.
     */
    public Token getToken(int row, int column) {
        if (this.rowOutOfBounds(row)) {
            throw new IllegalArgumentException("index `row` is out of bounds");
        }

        if (this.columnOutOfBounds(column)) {
            throw new IllegalArgumentException("index `column` is out of bounds");
        }

        return this.board[this.translate(row, column)];
    }

    /**
     * Retrieves the token at the specified position on the board.
     *
     * @param index - the raw index of the token.
     * @return the token at the specified position.
     * @throws IllegalArgumentException if `index` is out of bounds.
     */
    public Token getToken(int index) {
        if (index < 0 || index >= this.board.length) {
            throw new IllegalArgumentException("`index` is out of bounds");
        }

        return this.board[index];
    }

    /**
     * Translates the given row and column coordinates to a linear index on the
     * board.
     *
     * @param row    - the row coordinate (0-indexed, where 0 is the top row).
     * @param column - the column coordinate (0-indexed, where 0 is the leftmost
     *               column).
     * @return the linear index on the board.
     */
    public int translate(int row, int column) {
        return row * this.BOARD_WIDTH + column;
    }

    /**
     * Checks if the given column is out of bounds.
     *
     * @param column - the column index to check.
     * @return true if the column is out of bounds, false otherwise.
     */
    private boolean columnOutOfBounds(int column) {
        return column < 0 || column >= this.BOARD_WIDTH;
    }

    /**
     * Checks if the given row is out of bounds.
     *
     * @param row - the row index to check.
     * @return true if the row is out of bounds, false otherwise.
     */
    private boolean rowOutOfBounds(int row) {
        return row < 0 || row >= this.BOARD_HEIGHT;
    }
}
