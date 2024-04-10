package connect4.models;

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
   * Extracts the move history as a string.
   *
   * @return the move history as a string.
   */
  String extractMoveHistory();
}
