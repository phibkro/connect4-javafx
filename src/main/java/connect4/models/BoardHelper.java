package connect4.models;

public class BoardHelper {
    public static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 },
            { -1, -1 } };
    public int BOARD_WIDTH;
    public int BOARD_HEIGHT;
    public Tile[] board;

    public BoardHelper(int BOARD_HEIGHT, int BOARD_WIDTH, Tile[] board) {
        this.BOARD_HEIGHT = BOARD_HEIGHT;
        this.BOARD_WIDTH = BOARD_WIDTH;
        this.board = board;
    }

    public Tile[] getRow(int row) throws IllegalArgumentException {
        if (row < 0 || row >= this.BOARD_HEIGHT) {
            throw new IllegalArgumentException("`row` index out of bounds");
        }

        Tile[] rowTiles = new Tile[this.BOARD_WIDTH];

        for (int index = 0; index < this.BOARD_WIDTH; index++) {
            rowTiles[index] = getTile(row, index);
        }

        return rowTiles;
    }

    public Tile[] getColumn(int column) {
        if (column < 0 || column >= this.BOARD_WIDTH) {
            throw new IllegalArgumentException("`column` index out of bounds");
        }

        Tile[] columnTiles = new Tile[this.BOARD_HEIGHT];

        for (int index = 0; index < this.BOARD_HEIGHT; index++) {
            columnTiles[index] = getTile(index, column);
        }

        return columnTiles;
    }

    public boolean isXSelfSameTilesInARowHorizontalOrDiagonal(Tile tile, int row, int column, int max) {
        int maxMax = Math.max(this.BOARD_WIDTH, this.BOARD_HEIGHT) - 1;

        if (max <= 0 || max > maxMax) {
            throw new IllegalArgumentException("`max` must be between 0 and " + maxMax);
        }

        int selfSameTilesInARow = 0;

        for (int[] direction : BoardHelper.DIRECTIONS) {
            int rowDirection = direction[0];
            int columnDirection = direction[1];

            for (int index = 1; index <= max; index++) {
                int newRow = row + rowDirection * index;
                int newColumn = column + columnDirection * index;

                if (newRow < 0 || newRow >= this.BOARD_HEIGHT || newColumn < 0 || newColumn >= this.BOARD_WIDTH) {
                    break;
                }

                if (getTile(newRow, newColumn) == tile) {
                    selfSameTilesInARow++;
                } else {
                    selfSameTilesInARow = 0;
                }

                if (selfSameTilesInARow == max) {
                    return true;
                }
            }
        }

        return false;
    }

    public Tile getTile(int row, int column) {
        return this.board[translate(row, column)];
    }

    public Tile getTile(int index) {
        return this.board[index];
    }

    public int translate(int row, int column) {
        return row * this.BOARD_WIDTH + column;
    }
}
