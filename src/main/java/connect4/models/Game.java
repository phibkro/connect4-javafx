package connect4.models;

import java.util.Arrays;

public class Game {
  private final int BOARD_WIDTH = 7;
  private final int BOARD_HEIGHT = 6;
  private double tileSize = 50;
  private Tile[][] board = new Tile[BOARD_WIDTH][BOARD_HEIGHT];
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
    return currentPlayer;
  }

  public boolean isValidMove(int columnIndex) {
    // TODO
    return true;
  }

  public void makeMove(int columnIndex) {
    // TODO
  }
}
