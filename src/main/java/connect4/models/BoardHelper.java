package connect4.models;

public class BoardHelper {
    public static final int[][] DIRECTIONS = {
            { 0, 1 }, { 0, -1 }, // →, ←
            { 1, 0 }, { -1, 0 }, // ↓, ↑
            { 1, 1 }, { -1, -1 }, // ↘, ↖
            { -1, 1 }, { 1, -1 } // ↗, ↙
    };
    public int BOARD_WIDTH;
    public int BOARD_HEIGHT;
    public Token[] board;

    public BoardHelper(int BOARD_HEIGHT, int BOARD_WIDTH, Token[] board) {
        this.BOARD_HEIGHT = BOARD_HEIGHT;
        this.BOARD_WIDTH = BOARD_WIDTH;
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
            chosenRow[column] = getToken(row, column);
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
            chosenColumn[row] = getToken(row, column);
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
        if (this.rowOutOfBounds(originRow) || this.columnOutOfBounds(originColumn)) {
            throw new IllegalArgumentException("`originRow` or `originColumn` is out of bounds");
        }

        if (n < 1) {
            throw new IllegalArgumentException("`n` must be between 1 or more");
        }

        Token originToken = getToken(originRow, originColumn);

        for (int[] direction : BoardHelper.DIRECTIONS) {
            int columnDirection = direction[1];
            int rowDirection = direction[0];
            int inARow = 1;

            for (int index = 1; index < n; index++) {
                int columnToTest = originColumn + columnDirection * index;
                int rowToTest = originRow + rowDirection * index;

                if (this.rowOutOfBounds(rowToTest) || this.columnOutOfBounds(columnToTest)) {
                    break;
                }

                if (getToken(rowToTest, columnToTest) == originToken) {
                    if (++inARow == n) {
                        return true;
                    }
                } else {
                    break;
                }

            }
        }

        return false;
    }

    /**
     * Unsafely retrieves the token at the specified position on the board.
     *
     * @param row    - the row index of the token (0-indexed, where 0 is the top
     *               row).
     * @param column - the column index of the token (0-indexed, where 0 is the
     *               leftmost column).
     * @return the token at the specified position.
     * @throws ArrayIndexOutOfBoundsException if the `row` or `column` is out of
     *                                        bounds.
     */
    public Token getToken(int row, int column) {
        return this.board[translate(row, column)];
    }

    /**
     * Unsafely retrieves the token at the specified position on the board.
     *
     * @param index - the raw index of the token.
     * @return the token at the specified position.
     * @throws ArrayIndexOutOfBoundsException if the `row` or `column` is out of
     *                                        bounds.
     */
    public Token getToken(int index) {
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
