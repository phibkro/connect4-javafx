package connect4.models;

import java.util.Arrays;

public class Game implements VectorGame {
  public static final int BOARD_WIDTH = 7;
  public static final int BOARD_HEIGHT = 6;
  public static final int BOARD_SIZE = BOARD_WIDTH * BOARD_HEIGHT;
  private Tile[] board = new Tile[BOARD_WIDTH * BOARD_HEIGHT];
  private Tile currentPlayer;

  public Game() {
    Arrays.fill(this.board, Tile.Empty);
    this.currentPlayer = Tile.Player;
  }

  public Tile getCurrentPlayer() {
    return this.currentPlayer;
  }

  public boolean isValidMove(int column) {
    BoardHelper boardHelper = new BoardHelper(BOARD_WIDTH, BOARD_HEIGHT, this.board);
    Tile[] highestRow = boardHelper.getRow(0);

    return highestRow[column] == Tile.Empty;
  }

  public void makeMove(int column) throws IllegalArgumentException {
    if (!this.isValidMove(column)) {
      throw new IllegalArgumentException("Invalid move to column " + column);
    }

    BoardHelper boardHelper = new BoardHelper(BOARD_WIDTH, BOARD_HEIGHT, this.board);
    Tile[] selectedColumn = boardHelper.getColumn(column);

    for (int row = BOARD_HEIGHT - 1; row >= 0; row--) {
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
