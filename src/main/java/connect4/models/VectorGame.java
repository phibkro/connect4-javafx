package connect4.models;

import java.io.File;

public interface VectorGame {
  /**
   * Makes a move in the specified column.
   *
   * @param column - the column index to make a move in
   * @throws IllegalArgumentException if the column is out of bounds
   * @throws IllegalStateException    if the column is full
   */
  void makeMove(int column) throws IllegalArgumentException, IllegalStateException;

  /**
   * Checks if a move is legal in the game.
   *
   * @param column - the column index to check for a legal move
   * @return true if the move is legal, false otherwise
   */
  boolean isLegalMove(int column);

  /**
   * Checks if the game is over. The game is over if there is a winner or if the
   * board is full.
   *
   * @return true if the game is over, false otherwise.
   */
  boolean isGameOver();

  /**
   * Returns the winner of the game.
   * 
   * @return the winner of the game. `None` represents a stalemate.
   * @throws IllegalStateException if the game is not over.
   */
  Token getWinner() throws IllegalStateException;

  /**
   * Loads a game from a file.
   *
   * @param file - the file to load the game from.
   * @throws IllegalArgumentException if the file is invalid.
   */
  void loadGame(File file) throws IllegalArgumentException;

  /**
   * Saves the game to a file.
   *
   * @param file - the file to save the game to.
   */
  void saveGame(File file);

  /**
   * Loads the move history from a string.
   *
   * @param moveHistory - the move history to load.
   */
  void loadMoveHistory(String moveHistory);

  /**
   * Extracts the move history as a string.
   *
   * @return the move history as a string.
   */
  String extractMoveHistory();
}
