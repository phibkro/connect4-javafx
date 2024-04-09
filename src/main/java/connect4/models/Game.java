package connect4.models;

import java.util.Arrays;

public class Game implements VectorGame {
  public static final int COLUMN_COUNT = 7;
  public static final int COLUMN_SIZE = 6;
  public static final int BOARD_SIZE = COLUMN_COUNT * COLUMN_SIZE;
  private Tile[] board = new Tile[COLUMN_COUNT * COLUMN_SIZE];
  private Tile currentPlayer = Tile.Player;

  public Game() {
    Arrays.fill(this.board, Tile.Empty);
  }

  public Tile getCurrentPlayer() {
    return this.currentPlayer;
  }

  public boolean isMoveLegal(int column) {
    BoardHelper boardHelper = new BoardHelper(COLUMN_SIZE, COLUMN_COUNT, this.board);
    Tile[] highestRow = boardHelper.getRow(0);

    return highestRow[column] == Tile.Empty;
  }

  public void makeMove(int column) throws IllegalArgumentException {
    if (!this.isMoveLegal(column)) {
      throw new IllegalArgumentException("Invalid move to column " + column);
    }

    BoardHelper boardHelper = new BoardHelper(COLUMN_SIZE, COLUMN_COUNT, this.board);
    Tile[] selectedColumn = boardHelper.getColumn(column);

    for (int row = COLUMN_SIZE - 1; row >= 0; row--) {
      if (selectedColumn[row] == Tile.Empty) {
        this.board[boardHelper.translate(row, column)] = this.currentPlayer;
        break;
      }
    }
  }

  @Override
  public Tile isWon() {
    // TODO Auto-generated method stub
    return null;
  }
}
