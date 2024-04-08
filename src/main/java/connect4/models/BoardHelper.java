package connect4.models;

import java.util.ArrayList;
import java.util.List;

public class BoardHelper {
    public int BOARD_WIDTH;
    public int BOARD_HEIGHT;
    public Tile[] board;

    public BoardHelper(int BOARD_HEIGHT, int BOARD_WIDTH, Tile[] board) {
        this.BOARD_HEIGHT = BOARD_HEIGHT;
        this.BOARD_WIDTH = BOARD_WIDTH;
        this.board = board;
    }

    public Tile[] getRow(int row) {
        Tile[] rowTiles = new Tile[this.BOARD_WIDTH];

        for (int index = 0; index < this.BOARD_WIDTH; index++) {
            rowTiles[index] = getTile(row, index);
        }

        return rowTiles;
    }

    public Tile[] getColumn(int column) {
        Tile[] columnTiles = new Tile[this.BOARD_HEIGHT];

        for (int index = 0; index < this.BOARD_HEIGHT; index++) {
            columnTiles[index] = getTile(index, column);
        }

        return columnTiles;
    }

    public Tile[] getHorizontalAndDiagonalTiles(int row, int column, int max) {
        max = Math.min(max, Math.max(this.BOARD_WIDTH, this.BOARD_HEIGHT) - 1);

        List<Tile> tiles = new ArrayList<>();
        int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };

        tiles.add(getTile(row, column));

        if (max <= 0) {
            return tiles.toArray(new Tile[0]);
        }

        for (int[] direction : directions) {
            int rowDirection = direction[0];
            int columnDirection = direction[1];

            for (int index = 1; index <= max; index++) {
                int newRow = row + rowDirection * index;
                int newColumn = column + columnDirection * index;

                if (newRow < 0 || newRow >= this.BOARD_HEIGHT || newColumn < 0 || newColumn >= this.BOARD_WIDTH) {
                    break;
                }

                tiles.add(getTile(newRow, newColumn));
            }
        }

        return tiles.toArray(new Tile[0]);
    }

    public Tile getTile(int row, int column) {
        return this.board[translate(row, column)];
    }

    public int translate(int row, int column) {
        return row * this.BOARD_WIDTH + column;
    }
}
