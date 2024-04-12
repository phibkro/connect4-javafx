package connect4.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game implements VectorGame {
  public static final int WINNING_LENGTH = 4;
  public static final int HEIGHT = 6;
  public static final int WIDTH = 7;
  public static final int SIZE = Game.HEIGHT * Game.WIDTH;
  private Token currentPlayer = Token.Player;
  private List<Integer> moveHistory;
  private Token[] board = new Token[Game.SIZE];

  public Game() {
    this.moveHistory = new ArrayList<>();

    Arrays.fill(this.board, Token.None);
  }

  /**
   * Returns the player to make the next move in the game.
   */
  public Token getCurrentPlayer() {
    if (this.isGameOver()) {
      return Token.None;
    }
    return this.currentPlayer;
  }

  /**
   * Checks if a move is legal in the game.
   *
   * @param column - The column index to check.
   * @return true if the move is legal, false otherwise.
   */
  @Override
  public boolean isLegalMove(int column) {
    if (column < 0 || column >= Game.WIDTH) {
      return false;
    }

    BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);
    Token[] highestRow = boardHelper.getRow(0);

    return highestRow[column] == Token.None;
  }

  /**
   * Makes a move in the game by dropping the correct token into the specified
   * column.
   * 
   * @param column - The column index where the token should be dropped.
   * @throws IllegalArgumentException if the specified column is invalid (out of
   *                                  bounds).
   * @throws IllegalStateException    if the move is illegal in the current game
   *                                  state (column full).
   */
  @Override
  public void makeMove(int column) throws IllegalArgumentException, IllegalStateException {
    if (column < 0 || column >= Game.WIDTH) {
      throw new IllegalArgumentException("Invalid move to out-of-bounds column " + column);
    }

    if (!this.isLegalMove(column)) {
      throw new IllegalStateException("Illegal move to full column " + column);
    }

    BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);
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

  /**
   * Alternates the player to make the next move in the game between Player and
   * Opponent.
   */
  private void alternatePlayer() {
    this.currentPlayer = this.getCurrentPlayer() == Token.Player ? Token.Opponent : Token.Player;
  }

  /**
   * Checks if the game is over. The game is over if someone has won or if every
   * cell is filled (stalemate).
   * 
   * @return true if the game is over, false otherwise.
   */
  @Override
  public boolean isGameOver() {
    BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);

    if (this.moveHistory.size() == Game.SIZE) {
      return true;
    }

    for (int row = 0; row < Game.HEIGHT; row++) {
      for (int column = 0; column < Game.WIDTH; column++) {
        Token token = boardHelper.getToken(row, column);

        if (token == Token.None) {
          continue;
        }

        if (boardHelper.isNInARow(row, column, Game.WINNING_LENGTH)) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * Gets the winner of the game. If the game is not over, an exception is thrown.
   * 
   * @return the winner of the game, where `None` represents a stalemate.
   * @throws IllegalStateException if the game is not over.
   */
  @Override
  public Token getWinner() {
    if (!this.isGameOver()) {
      throw new IllegalStateException("Game is not over");
    }

    BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);

    for (int row = 0; row < Game.HEIGHT; row++) {
      for (int column = 0; column < Game.WIDTH; column++) {
        Token token = boardHelper.getToken(row, column);

        if (token == Token.None) {
          continue;
        }

        if (boardHelper.isNInARow(row, column, Game.WINNING_LENGTH)) {
          return token;
        }
      }
    }

    return Token.None;
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
}
