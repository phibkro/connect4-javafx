package connect4.models;

import java.io.File;
import java.util.Arrays;

public class Game implements VectorGame {
  public static final int HEIGHT = 6;
  public static final int WIDTH = 7;
  private Token currentPlayer = Token.Player;
  private Token[] board = new Token[WIDTH * HEIGHT];

  public Game() {
    Arrays.fill(this.board, Token.None);
  }

  public Token getCurrentPlayer() {
    return this.currentPlayer;
  }

  @Override
  public boolean isLegalMove(int column) {
    if (column < 0 || column >= Game.WIDTH) {
      return false;
    }

    BoardHelper boardHelper = new BoardHelper(Game.HEIGHT, Game.WIDTH, this.board);
    Token[] highestRow = boardHelper.getRow(0);

    return highestRow[column] == Token.None;
  }

  @Override
  public void makeMove(int column) throws IllegalArgumentException {
    if (!this.isLegalMove(column)) {
      throw new IllegalArgumentException("Illegal or invalid move to column " + column);
    }

    BoardHelper boardHelper = new BoardHelper(Game.HEIGHT, Game.WIDTH, this.board);
    Token[] selectedColumn = boardHelper.getColumn(column);

    for (int row = Game.HEIGHT - 1; row >= 0; row--) {
      if (selectedColumn[row] == Token.None) {
        this.board[boardHelper.translate(row, column)] = this.getCurrentPlayer();
        break;
      }
    }

    this.alternatePlayer();
  }

  @Override
  public boolean isGameOver() {
    BoardHelper boardHelper = new BoardHelper(Game.HEIGHT, Game.WIDTH, this.board);

    for (int row = 0; row < Game.HEIGHT; row++) {
      for (int column = 0; column < Game.WIDTH; column++) {
        Token token = boardHelper.getToken(row, column);

        if (token == Token.None) {
          continue;
        }

        if (boardHelper.isNInARow(token, row, column, 4)) {
          return true;
        }
      }
    }

    return false;
  }

  @Override
  public Token getWinner() {
    BoardHelper boardHelper = new BoardHelper(Game.HEIGHT, Game.WIDTH, this.board);

    for (int row = 0; row < Game.HEIGHT; row++) {
      for (int column = 0; column < Game.WIDTH; column++) {
        Token token = boardHelper.getToken(row, column);

        if (token == Token.None) {
          continue;
        }

        if (boardHelper.isNInARow(token, row, column, 4)) {
          return token;
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

  private void alternatePlayer() {
    this.currentPlayer = this.getCurrentPlayer() == Token.Player ? Token.Opponent : Token.Player;
  }
}
