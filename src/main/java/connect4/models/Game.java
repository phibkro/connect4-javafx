package connect4.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game implements VectorGame {
  public static final int HEIGHT = 6;
  public static final int WIDTH = 7;
  private Token currentPlayer = Token.Player;
  private List<Integer> moveHistory;
  private Token[] board = new Token[WIDTH * HEIGHT];

  public Game() {
    this.moveHistory = new ArrayList<>();

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

    this.moveHistory.add(column);
    this.alternatePlayer();
  }

  @Override
  public boolean isGameOver() {
    BoardHelper boardHelper = new BoardHelper(Game.HEIGHT, Game.WIDTH, this.board);

    if (this.moveHistory.size() == Game.HEIGHT * Game.WIDTH) {
      return true;
    }

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
  public void loadGame(String moveHistory) {
    this.moveHistory = new ArrayList<>();
    Arrays.fill(this.board, Token.None);

    for (char rawColumnIndex : moveHistory.toCharArray()) {
      int columnIndex = Character.getNumericValue(rawColumnIndex);

      this.moveHistory.add(columnIndex);
      this.makeMove(columnIndex);
    }
  }

  @Override
  public String extractMoveHistory() {
    if (this.moveHistory.size() == 0) {
      return "";
    }

    StringBuilder moveHistory = new StringBuilder();

    for (int columnIndex : this.moveHistory) {
      moveHistory.append(columnIndex);
    }

    return moveHistory.toString();
  }

  private void alternatePlayer() {
    this.currentPlayer = this.getCurrentPlayer() == Token.Player ? Token.Opponent : Token.Player;
  }
}
