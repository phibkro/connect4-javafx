package connect4.models;

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

  void loadGame(File file) throws IllegalArgumentException;
  // Should throw if passed an incompatible file type

  String extractMoveHistory();

  void saveGame(File file);
}