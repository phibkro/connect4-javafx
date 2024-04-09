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

    public Token[] getRow(int row) throws IllegalArgumentException {
        if (row < 0 || row >= this.BOARD_HEIGHT) {
            throw new IllegalArgumentException("`row` index out of bounds");
        }

        Token[] chosenRow = new Token[this.BOARD_WIDTH];

        for (int column = 0; column < this.BOARD_WIDTH; column++) {
            chosenRow[column] = getToken(row, column);
        }

        return chosenRow;
    }

    public Token[] getColumn(int column) {
        if (column < 0 || column >= this.BOARD_WIDTH) {
            throw new IllegalArgumentException("`column` index out of bounds");
        }

        Token[] chosenColumn = new Token[this.BOARD_HEIGHT];

        for (int row = 0; row < this.BOARD_HEIGHT; row++) {
            chosenColumn[row] = getToken(row, column);
        }

        return chosenColumn;
    }

    public boolean isNInARow(Token token, int row, int column, int n) {
        if (n < 1) {
            throw new IllegalArgumentException("`n` must be between 1 or more");
        }

        for (int[] direction : BoardHelper.DIRECTIONS) {
            int columnDirection = direction[1];
            int rowDirection = direction[0];
            int inARow = 1;

            for (int index = 1; index < n; index++) {
                int columnToTest = column + columnDirection * index;
                int rowToTest = row + rowDirection * index;

                if (rowToTest < 0 || rowToTest >= this.BOARD_HEIGHT || columnToTest < 0
                        || columnToTest >= this.BOARD_WIDTH) {
                    break;
                }

                if (getToken(rowToTest, columnToTest) == token) {
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

    public Token getToken(int row, int column) {
        return this.board[translate(row, column)];
    }

    public Token getToken(int index) {
        return this.board[index];
    }

    public int translate(int row, int column) {
        return row * this.BOARD_WIDTH + column;
    }
}
