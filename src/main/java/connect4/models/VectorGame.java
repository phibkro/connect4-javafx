package connect4.models;

import java.io.File;

public interface VectorGame {
  void makeMove(int column) throws IllegalArgumentException, IllegalStateException;
  // Should throw IllegalArgumentException
  // if column is negative
  // Should throw IllegalStateException
  // if column is full

  boolean isLegalMove(int column);

  boolean isGameOver();

  Token getWinner() throws IllegalStateException;
  // Should throw if game is not over

  void loadMoveHistory(String moveHistory);

  String extractMoveHistory();
}