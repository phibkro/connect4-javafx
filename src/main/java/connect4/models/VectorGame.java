package connect4.models;

public interface VectorGame {
  void makeMove(int column);

  boolean isValidMove(int column);

  boolean[] getValidMoves();

  Tile isWon();
}
