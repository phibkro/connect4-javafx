package connect4.models;

import java.util.Arrays;

public class Game implements VectorGame {
  private final int BOARD_WIDTH = 7;
  private final int BOARD_HEIGHT = 6;
  private double tileSize = 50;
  private Tile[] board = new Tile[BOARD_WIDTH * BOARD_HEIGHT];
  private Tile currentPlayer;

  public Game() {
    Tile[] column = new Tile[BOARD_WIDTH];
    Arrays.fill(column, Tile.Empty);
    Arrays.fill(board, column);
    this.currentPlayer = Tile.Player;
  }

  public int getBoardWidth() {
    return this.BOARD_WIDTH;
  }

  public int getBoardHeight() {
    return this.BOARD_HEIGHT;
  }

  public int getBoardSize() {
    return this.BOARD_WIDTH * this.BOARD_HEIGHT;
  }

  public double getTileSize() {
    return this.tileSize;
  }

  public Tile getCurrentPlayer() {
    return this.currentPlayer;
  }

  public boolean isValidMove(int column) {
    BoardHelper boardHelper = new BoardHelper(this.BOARD_WIDTH, this.BOARD_HEIGHT, this.board);
    Tile[] highestRow = boardHelper.getRow(0);

    return highestRow[column] == Tile.Empty;
  }

  public void makeMove(int column) throws IllegalArgumentException {
    if (!this.isValidMove(column)) {
      throw new IllegalArgumentException("Invalid move to column " + column);
    }

    BoardHelper boardHelper = new BoardHelper(this.BOARD_WIDTH, this.BOARD_HEIGHT, this.board);
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
