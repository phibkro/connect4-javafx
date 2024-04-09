package connect4.models;

import java.io.File;
import java.util.Arrays;

public class Game implements VectorGame {
  public static final int HEIGHT = 6;
  public static final int WIDTH = 7;
  private Tile[] board = new Tile[WIDTH * HEIGHT];
  private Tile currentPlayer = Tile.Player;

  public Game() {
    Arrays.fill(this.board, Tile.Empty);
  }

  public Tile getCurrentPlayer() {
    return this.currentPlayer;
  }

  @Override
  public boolean isLegalMove(int column) {
    if (column < 0 || column >= Game.WIDTH) {
      return false;
    }

    BoardHelper boardHelper = new BoardHelper(Game.HEIGHT, Game.WIDTH, this.board);
    Tile[] highestRow = boardHelper.getRow(0);

    return highestRow[column] == Tile.Empty;
  }

  @Override
  public void makeMove(int column) throws IllegalArgumentException {
    if (!this.isLegalMove(column)) {
      throw new IllegalArgumentException("Illegal or invalid move to column " + column);
    }

    BoardHelper boardHelper = new BoardHelper(Game.HEIGHT, Game.WIDTH, this.board);
    Tile[] selectedColumn = boardHelper.getColumn(column);

    for (int row = Game.HEIGHT - 1; row >= 0; row--) {
      if (selectedColumn[row] == Tile.Empty) {
        this.board[boardHelper.translate(row, column)] = this.currentPlayer;
        break;
      }
    }

    this.currentPlayer = this.currentPlayer == Tile.Player ? Tile.Opponent : Tile.Player;
  }

  @Override
  public boolean isGameOver() {
    BoardHelper boardHelper = new BoardHelper(Game.HEIGHT, Game.WIDTH, this.board);

    for (int row = 0; row < Game.HEIGHT; row++) {
      for (int column = 0; column < Game.WIDTH; column++) {
        Tile tile = boardHelper.getTile(row, column);

        if (tile == Tile.Empty) {
          continue;
        }

        if (boardHelper.isNInARow(tile, row, column, 4)) {
          return true;
        }
      }
    }

    return false;
  }

  @Override
  public Tile getWinner() {
    BoardHelper boardHelper = new BoardHelper(Game.HEIGHT, Game.WIDTH, this.board);

    for (int row = 0; row < Game.HEIGHT; row++) {
      for (int column = 0; column < Game.WIDTH; column++) {
        Tile tile = boardHelper.getTile(row, column);

        if (tile == Tile.Empty) {
          continue;
        }

        if (boardHelper.isNInARow(tile, row, column, 4)) {
          return tile;
        }
      }
    }

    throw new IllegalStateException("No winner found");
  }

  @Override
  public void loadGame(File file) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'loadGame'");
  }

  @Override
  public File saveGame() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'saveGame'");
  }
}
